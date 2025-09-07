# Sistema de Polinomios en Java

> Proyecto académico — tres representaciones (vectores y lista enlazada) para polinomios y operaciones básicas (insertar, eliminar, mostrar, evaluar, sumar y multiplicar).

_Si piensas que "polinomio" suena a examen final, relájate: esto solo suma y multiplica, no te pide que le saques la raíz de la vida._ 😄

---

## Tabla de contenido

1. Resumen
2. Estructura del proyecto
3. Flujo general (input → vectorResultado → formas)
4. Formas de representación (Forma1, Forma2, Forma3)
5. Parser: cómo convertir `String` → `vectorResultado` (algoritmo + ejemplo)
6. Operaciones (insertar, eliminar, mostrar, evaluar, sumar, multiplicar) — por forma
7. Casos borde y validaciones
8. Sugerencias realistas de mejora
9. Cómo compilar / ejecutar
10. Contribuir y licencia

---

## 1. Resumen

Este repositorio contiene un proyecto en Java pensado para una asignatura (POO/ED): un sistema para representar y operar polinomios en tres formas distintas:

- **Forma1**: arreglo indexado por grado del polinomio (encabezado = grado).
- **Forma2**: arreglo de pares `(coef, expo)` con contador prefijo (nº términos).
- **Forma3**: lista enlazada simple (Nodo: coef, expo, liga).

El objetivo es comprender estructuras, conversiones entre representaciones y algoritmos clásicos (suma, multiplicación, evaluación), manteniendo un estilo académico y claro.

---

## 2. Estructura del proyecto

```
/ (raíz)
├─ src/
│  ├─ Principal.java    // Menú, parser (String -> vectorResultado) y lógica de flujo
│  ├─ Forma1.java       // Implementación con vector [grado, coef_x^grado, ..., coef_x^0]
│  ├─ Forma2.java       // Implementación con vector [n_terms, coef, expo, coef, expo, ...]
│  ├─ Forma3.java       // Implementación con lista enlazada (usa Nodo.java)
│  └─ Nodo.java         // Nodo { int coef, int expo; Nodo liga; }
├─ README.md
└─ (otros recursos y pruebas)
```

**Nota:** El parser está deliberadamente en `Principal.java` según la especificación del curso/profesor.

---

## 3. Flujo general

1. El usuario ingresa un polinomio como `String`. Ejemplo: `2x^4+3x-6+5x^2-4x^3`.
2. Se transforma en un vector de caracteres (conceptual): `|2|x|^|4|+|3|x|-|6|+|5|x|^|2|-|4|x|^|3|`.
3. Se parsea en `vectorResultado` con pares numéricos: `|2|4|3|1|-6|0|5|2|-4|3|` → pares `(coef, expo)`.
4. `vectorResultado` alimenta la forma elegida (1, 2 o 3).

---

## 4. Formas de representación (con ejemplos)

**Ejemplo base**

`vectorResultado` = `(2,4), (3,1), (-6,0), (5,2), (-4,3)` (internamente: `|2|4|3|1|-6|0|5|2|-4|3|`).

### Forma1 (grado-index)

Formato: `|grado|coef_x^grado|coef_x^(grado-1)|...|coef_x^0|`

Ejemplo (para el polinomio anterior):

```
|4|2|-4|5|3|-6|
// [0]=4 (grado)
// [1]=coef x^4 = 2
// [2]=coef x^3 = -4
// [3]=coef x^2 = 5
// [4]=coef x^1 = 3
// [5]=coef x^0 = -6
```

**Fórmula de posicionamiento:**

Si `grado = G` y tienes un término con `expo = e`, entonces su índice en el vector (1-based después del header) es `pos = (G - e) + 1`.

### Forma2 (lista de pares con contador)

Formato: `|nTérminos|coef|expo|coef|expo|...|`

Ejemplo:

```
|5|2|4|3|1|-6|0|5|2|-4|3|
// [0]=5 (número de términos)
// luego pares (coef, expo)
```

### Forma3 (lista enlazada)

Cada `Nodo`:
```java
class Nodo {
    int coef;
    int expo;
    Nodo liga; // siguiente
}
```

Lista ordenada (recomendado) por `expo` descendente: cabeza → mayor expo.

---

## 5. Parser: `String` → `vectorResultado`

### Principios a cubrir

- Soporta espacios (se ignoran).
- Coeficiente implícito `x` → `1` o `-x` → `-1`.
- Exponente implícito `x` → `^1` si no aparece `^`.
- Constantes (no contienen `x`) tienen expo `0`.
- Manejar signos `+` y `-` correctamente.
- Normalizar: combinar términos con mismo exponente.

### Algoritmo (paso a paso)

1. Eliminar espacios: `s = s.replaceAll("\\s+", "")`.
2. Recorrer `s` con un índice `i`:
   - Detectar `sign` (+1 o -1) si existe `+` o `-` al inicio del término.
   - Leer dígitos para coeficiente (si hay). Si no hay dígitos y siguiente es `x`, coef = 1.
   - Si aparece `x`: leer opcional `^` y exponente (si no, expo = 1).
   - Si no aparece `x`: es constante → expo = 0.
   - Añadir `(coef * sign, expo)` a la lista temporal.
3. Al terminar, **normalizar** la lista: sumar coeficientes de exponentes iguales y eliminar coeficientes 0.

### Ejemplo de código (esqueleto en Java)

```java
// Devuelve un ArrayList<Integer> con pares [coef, expo, coef, expo, ...]
public static ArrayList<Integer> parsePolynomial(String s) {
    s = s.replaceAll("\\s+", "");
    ArrayList<Integer> pairs = new ArrayList<>();
    int i = 0, n = s.length();

    while (i < n) {
        int sign = 1;
        if (s.charAt(i) == '+') { sign = 1; i++; }
        else if (s.charAt(i) == '-') { sign = -1; i++; }

        // Parse coefficient (if any)
        int coef = 0; boolean coefParsed = false;
        while (i < n && Character.isDigit(s.charAt(i))) {
            coef = coef * 10 + (s.charAt(i) - '0');
            i++; coefParsed = true;
        }

        if (i < n && s.charAt(i) == 'x') {
            if (!coefParsed) coef = 1; // "x" o "-x"
            coef *= sign;
            i++; // salto la 'x'

            int expo = 1; // por defecto
            if (i < n && s.charAt(i) == '^') {
                i++; // salto '^'
                int expVal = 0; boolean expParsed = false;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    expVal = expVal * 10 + (s.charAt(i) - '0');
                    i++; expParsed = true;
                }
                if (expParsed) expo = expVal; // si no, se queda 1
            }
            pairs.add(coef); pairs.add(expo);
        } else {
            // constante
            int constant = coefParsed ? coef * sign : 0;
            pairs.add(constant); pairs.add(0);
        }
    }

    // Normalizar: combinar exponentes iguales (implementación sugerida aparte)
    return normalizePairs(pairs);
}
```

> `normalizePairs` debe convertir la lista de pares a un `Map<expo, coef>` sumando coeficientes y luego devolver pares sin coef=0.

### Ejemplo concreto (texto → pasos)

Input: `2x^4+3x-6+5x^2-4x^3` → pares crudos: `(2,4),(3,1),(-6,0),(5,2),(-4,3)` → ya normalizados.

---

## 6. Operaciones (explicadas por forma)

> **Nota general:** Siempre que se combinen términos con el mismo exponente (resultado de suma o multiplicación), hay que normalizar (sumar coeficientes iguales y eliminar ceros).

### Inserción

- **Forma1** (vector grado-index):
  1. Si `expo > grado` → crear nuevo vector con `grado = expo` y copiar coeficientes al offset correcto.
  2. Calcular índice `pos = (grado - expo) + 1`; `vector[pos] += coef`.
  3. Si `vector[1] == 0` después de operaciones, recalcular `grado` bajando hasta el primer coeficiente distinto de 0.

- **Forma2** (lista pares):
  1. Buscar si existe el par con `expo`. Si existe -> sumar `coef`.
  2. Si no existe → append al final y `nTérminos++`.
  3. (Opcional) Mantener orden por expo para facilitar operaciones posteriores.

- **Forma3** (lista enlazada):
  1. Insertar en la posición ordenada por `expo` (descendente).
  2. Si el `expo` ya existe → sumar coef y si queda 0 → eliminar nodo.

Complejidad: Forma1 O(1) amortizado (salvo cuando hay que redimensionar), Forma2 O(n) buscar/merge, Forma3 O(n) insertar en lista ordenada.

### Eliminación

- **Forma1**: Poner coeficiente a 0; si se elimina el coef de mayor grado, recalcular grado.
- **Forma2**: Borrar par (compactar el array) y decrementar contador.
- **Forma3**: Desenlazar el nodo.

### Mostrar polinomio (reconstruir String)

Recorrer la estructura y para cada término con coef != 0 aplicar formato:
- coef = 1 o -1: mostrar `x` en vez de `1x` (salvo que expo==0)
- expo == 0: mostrar solo coef
- expo == 1: mostrar `x` en vez de `x^1`
- manejar signos: primer término sin `+` innecesario

Helper (pseudocódigo):
```java
String formatTerm(int coef, int expo) {
    if (coef == 0) return "";
    String s = (coef < 0 ? "-" : "+");
    int absC = Math.abs(coef);
    if (expo == 0) s += absC;
    else if (absC == 1) s += "x" + (expo==1?"":"^"+expo);
    else s += absC + "x" + (expo==1?"":"^"+expo);
    return s;
}
```
Después ensamblas cuidando el primer signo.

### Evaluación en `x` (computar P(x))

- **Forma1:** usar **método de Horner** para eficiencia (O(n) en grado):

```java
// suponiendo vector forma1 con header grado
int G = vector[0];
int result = 0;
for (int i = 1; i <= G+1-1; i++) {
    // mapear coeficientes con Horner requiere recorrer desde coef de mayor grado a menor
}
// mejor: crear arreglo coef[grado..0] y aplicar Horner
```

Implementación concreta: extraer coeficientes en orden decreciente de expo y aplicar Horner.

- **Forma2/3:** sumar `coef * Math.pow(x, expo)` para cada par/nodo. (Ojo: `Math.pow` devuelve `double`; si quieres enteros grandes, usar potencias enteras propias.)

### Suma de dos polinomios

- **Forma1:** alineas por grado y sumas coeficientes posición a posición. Resultado grado = max(gradoA, gradoB), luego normalizas.
- **Forma2:** recorrer ambos arreglos de pares y fusionar por exponente (similar a merge de dos listas ordenadas si estaban ordenadas).
- **Forma3:** recorrer ambas listas enlazadas (como merge) y crear nueva lista sumando coeficientes para exponentes iguales.

### Multiplicación de dos polinomios

- **Forma1 (más directo):** convolución de coeficientes.
  - Si gradoA = GA, gradoB = GB → gradoR = GA + GB.
  - Crear `res[gradoR+1]` (o estructura con header), luego doble bucle:
    - Para cada expo i en A, cada expo j en B → res[expo_i + expo_j] += coefA * coefB.
  - Normalizar (eliminar ceros y ajustar grado).

- **Forma2 / Forma3:** doble bucle sobre pares/nodos, para cada combinación añadir (coefA * coefB, expoA + expoB) en una estructura temporal, luego normalizar combinando exponentes iguales.

Complejidad: O(n*m) con n,m = número de términos.

---

## 7. Casos borde y validaciones

- Entradas mal formadas: caracteres no válidos → mostrar mensaje de error y pedir reingreso.
- Exponentes negativos: generalmente no se contemplan en este proyecto (si aparecen, documentarlo y decidir si aceptar o no).
- Términos con expo grande: cuidado con tamaño de arreglo en Forma1 (puede requerir reubicación costosa). Usar Forma2/3 si exponente muy grande pero pocos términos.
- Coeficientes que suman a 0 → eliminar término.
- Entrada `x` o `-x` → coef ±1.

---

## 8. Sugerencias realistas de mejora (sin complicar el curso)

- Extraer el parser a una clase `Parser` para pruebas y mantenimiento.
- Crear una interfaz/abstract class `Polinomio` con métodos `insertar`, `eliminar`, `evaluar`, `sumar`, `multiplicar`, `toString` y hacer que `Forma1/2/3` la implementen. (Mantener simple: interfaz con firma básica.)
- Añadir pruebas unitarias (JUnit) para parser y operaciones principales.
- Manejar potencias de `x` con `long` o `BigInteger` si se espera overflow; documentar limitaciones.
- Documentar cada método con JavaDoc y seguir **naming conventions** (camelCase para métodos y variables, PascalCase para clases).

---

## 9. Cómo compilar / ejecutar (ejemplo)

Desde la raíz del proyecto (suponiendo `src/`):

```bash
javac src/*.java -d out
java -cp out Principal
```

**Ejemplo de ejecución** (entrada del usuario):
```
Ingrese polinomio: 2x^4+3x-6+5x^2-4x^3
Elija forma (1/2/3): 1
// Submenú para insertar/eliminar/mostrar/evaluar/sumar/multiplicar...
```

---

## 10. Contribuir y licencia

- Pull requests bienvenidos. Mantén estilo académico y tests mínimos para cada cambio.
- Licencia: MIT (sugerido) — añade `LICENSE` si estás de acuerdo.

---

## Apéndice: errores comunes y "trucos de examen"

- No te olvides de **normalizar** después de suma/multiplicación. Es la fuente #1 de bugs (términos duplicados, coef = 0).
- Para evaluar mucho más rápido en Forma1, siempre que tengas el vector por grado, usa **Horner**.
- Si recibes exponentes muy grandes pero pocos términos, evita Forma1 (arreglos gigantes); usa Forma2/3.

---

Si quieres, genero ahora:
- Implementaciones completas en Java (Forma1.java, Forma2.java, Forma3.java, Nodo.java) siguiendo este README, o
- El parser completo listo para pegar en `Principal.java`.

Dime cuál prefieres y lo desarrollo (no prometo café, pero sí código claro). ☕😉


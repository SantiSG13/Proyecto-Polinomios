// -----------------------------------------------------
// Clase Forma3: Representación de polinomio como lista enlazada simple
// -----------------------------------------------------
package Polinomios;

public class Forma3 {
    Nodo cabeza;  // referencia al primer nodo de la lista

    // -----------------------------------------------------
    // Constructor: inicia la lista vacía
    // -----------------------------------------------------
    public Forma3() {
        cabeza = null;
    }

    // -----------------------------------------------------
    // Construir: convierte vectorResultado en Forma3 (lista enlazada)
    // -----------------------------------------------------
    public void Construir(String[] vectorResultado) {
        // Recorremos de a pares: i = coef, i+1 = expo
        for (int i = 0; i < vectorResultado.length; i += 2) {
            if (vectorResultado[i] != null && !vectorResultado[i].isEmpty()) {
                int coe= Integer.parseInt(vectorResultado[i].trim());
                // Verificamos que i+1 exista para evitar otro IndexOutOfBounds
                if (i + 1 < vectorResultado.length && vectorResultado[i + 1] != null && !vectorResultado[i + 1].isEmpty()) {
                    int exp= Integer.parseInt(vectorResultado[i + 1].trim());
                    if (coe!= 0) {
                        InsertarTermino(coe, exp);
                    }
                }
            }
        }
    }

    // -----------------------------------------------------
    // InsertarTermino: agrega o actualiza un término en la lista
    // -----------------------------------------------------
    public void InsertarTermino(int coef, int expo) {
        if (coef == 0) return; // no insertamos términos nulos

        // Si la lista está vacía
        if (cabeza == null) {
            cabeza = new Nodo(coef, expo);
            return;
        }

        // Buscar si ya existe un término con el mismo exponente
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.expo == expo) {
                actual.coef += coef; // sumamos coeficientes
                if (actual.coef == 0) {
                    EliminarTermino(expo);
                }
                return;
            }
            if (actual.liga == null) break;
            actual = actual.liga;
        }

        // Si no existe, lo agregamos al final
        actual.liga = new Nodo(coef, expo);
    }

    // -----------------------------------------------------
    // 2. Eliminar término por exponente
    // -----------------------------------------------------
    public void EliminarTermino(int expo) {
        if (cabeza == null) return;

        // Caso especial: el primero
        if (cabeza.expo == expo) {
            cabeza = cabeza.liga;
            return;
        }

        // Buscar en el resto
        Nodo actual = cabeza;
        while (actual.liga != null) {
            if (actual.liga.expo == expo) {
                actual.liga = actual.liga.liga; // saltamos el nodo
                return;
            }
            actual = actual.liga;
        }
    }

    // -----------------------------------------------------
    // 3. Mostrar lista (modo técnico)
    // -----------------------------------------------------
    public String MostrarLista() {
        String resultado = "";
        Nodo actual = cabeza;
        while (actual != null) {
            resultado += "[" + actual.coef + ", " + actual.expo + "]";
            if (actual.liga != null) {
                resultado += " → ";
            }
            actual = actual.liga;
        }
        return resultado;
    }



    // -----------------------------------------------------
    // 4. Mostrar polinomio algebraico
    // -----------------------------------------------------
    public String MostrarPolinomio() {
        Nodo actual = cabeza;
        if (actual == null) {
            return "0";
        }

        String polinomio = "";
        boolean primerTermino = true;

        while (actual != null) {
            int coef = actual.coef;
            int exp = actual.expo;

            if (coef == 0) {
                actual = actual.liga;
                continue; // Omitir términos con coeficiente cero
            }

            if (!primerTermino && coef > 0) {
                polinomio += "+"; // Añadir signo más para términos positivos (excepto el primero)
            }

            if (exp == 0) {
                polinomio += coef; // Término independiente
            } else if (exp == 1) {
                if (coef == 1) {
                    polinomio += "x";
                } else if (coef == -1) {
                    polinomio += "-x";
                } else {
                    polinomio += coef + "x";
                }
            } else {
                if (coef == 1) {
                    polinomio += "x^" + exp;
                } else if (coef == -1) {
                    polinomio += "-x^" + exp;
                } else {
                    polinomio += coef + "x^" + exp;
                }
            }

            primerTermino = false;
            actual = actual.liga;
        }

        if (polinomio.isEmpty()) {
            return "0";
        } else {
            return polinomio;
        }
    }

    // -----------------------------------------------------
    // 5. EvaluarPolinomio polinomio en un valor de x
    // -----------------------------------------------------
    public int EvaluarPolinomio(int x) {
        int resultado = 0;
        Nodo actual = cabeza;

        while (actual != null) {
            int pot = 1;
            for (int i = 0; i < actual.expo; i++) {
                pot *= x; // calculamos x^expo con bucle
            }
            resultado += actual.coef * pot;
            actual = actual.liga;
        }

        return resultado;
    }

    // -----------------------------------------------------
    // 6. Sumar dos polinomios (devuelve una nueva lista)
    // -----------------------------------------------------
    public static Forma3 SumarPolinomios(Forma3 A, Forma3 B) {
        Forma3 resultado = new Forma3();

        // Copiar A
        Nodo actual = A.cabeza;
        while (actual != null) {
            resultado.InsertarTermino(actual.coef, actual.expo);
            actual = actual.liga;
        }

        // Copiar B
        actual = B.cabeza;
        while (actual != null) {
            resultado.InsertarTermino(actual.coef, actual.expo);
            actual = actual.liga;
        }

        return resultado;
    }

    // -----------------------------------------------------
    // 7. Multiplicar dos polinomios (devuelve una nueva lista)
    // -----------------------------------------------------
    public static Forma3 MultiplicarPolinomios(Forma3 A, Forma3 B) {
        Forma3 resultado = new Forma3();

        Nodo a = A.cabeza;
        while (a != null) {
            Nodo b = B.cabeza;
            while (b != null) {
                int nuevoCoef = a.coef * b.coef;
                int nuevoExpo = a.expo + b.expo;
                resultado.InsertarTermino(nuevoCoef, nuevoExpo);
                b = b.liga;
            }
            a = a.liga;
        }
        return resultado;
    }

    // -----------------------------------------------------
// Sumar polinomios de Forma1 y Forma2, devuelve nuevo Forma3
// -----------------------------------------------------
    public static Forma3 SumarF1_F2(Forma1 f1, Forma2 f2) {
        Forma3 resultado = new Forma3();

        // Extraer términos de Forma1 y insertar en resultado
        int grado1 = f1.getVPF1()[0];
        for (int i = 1; i < f1.getVPF1().length; i++) {
            int coef = f1.getVPF1()[i];
            int exp = grado1 - (i - 1);
            if (coef != 0) { // Ignorar ceros
                resultado.InsertarTermino(coef, exp); // Combina si exp existe
            }
        }

        // Extraer términos de Forma2 y insertar en resultado
        int numTerminos2 = f2.getVPF2()[0];
        int ultimoIndice2 = 1 + (numTerminos2 - 1) * 2;
        for (int i = 1; i <= ultimoIndice2; i += 2) {
            int coef = f2.getVPF2()[i];
            int exp = f2.getVPF2()[i + 1];
            if (coef != 0) { // Ignorar ceros
                resultado.InsertarTermino(coef, exp); // Combina si exp existe
            }
        }

        // Ordenar descendente (mayor exp primero)
        resultado.Ordenar();

        return resultado;
    }

    // -----------------------------------------------------
    // Ordenar la lista descendente por exponente (burbuja simple)
    // -----------------------------------------------------
    private void Ordenar() {
        if (cabeza == null) {
            return;
        }
        boolean cambioDetectado;
        do {
            cambioDetectado = false;
            Nodo actual = cabeza;
            while (actual.liga != null) {
                if (actual.expo < actual.liga.expo) { // Orden descendente
                    // Intercambiar datos
                    int tempCoef = actual.coef;
                    int tempExpo = actual.expo;
                    actual.coef = actual.liga.coef;
                    actual.expo = actual.liga.expo;
                    actual.liga.coef = tempCoef;
                    actual.liga.expo = tempExpo;
                    cambioDetectado = true;
                }
                actual = actual.liga;
            }
        } while (cambioDetectado);
    }
}

# Proyecto de Polinomios en Java

Este es mi proyecto para la materia algoritmos y programación 3 donde el objetivo principal es trabajar con polinomios usando 3 formas diferentes de representarlos en Java.

## 📌 Estructura del proyecto

* **Principal.java** → Controla el menú principal, lectura del polinomio en `String`, conversión a `vectorResultado` y selección de la forma.
* **Forma1.java** → Representa el polinomio en un vector indexado por grado.
* **Forma2.java** → Representa el polinomio en un vector de pares `(coef, expo)` con un contador de términos.
* **Forma3.java** → Representa el polinomio en una lista enlazada simple.
* **Nodo.java** → Define la estructura de cada nodo de la lista enlazada (coeficiente, exponente y liga).

## 🔹 Flujo general

1. El usuario ingresa un polinomio como `String`. Ejemplo: `2x^4+3x-6+5x^2-4x^3`.
2. Ese `String` se convierte a un `vectorResultado` con pares `(coef, expo)`. Ejemplo: `|2|4|3|1|-6|0|5|2|-4|3|`.
3. Según la elección del usuario, el `vectorResultado` alimenta una de las formas de representación.

## 🔹 Formas de representación

* **Forma1** → Vector: `[grado, coef_x^grado, ..., coef_x^0]`
* **Forma2** → Vector: `[número de términos, coef, expo, coef, expo, ...]`
* **Forma3** → Lista enlazada con nodos `{coef, expo, liga}`


## 🔹 Operaciones implementadas

* Insertar término
* Eliminar término
* Mostrar la representación interna (vector o lista)
* Mostrar el polinomio en notación algebraica
* Evaluar el polinomio en un valor de `x`
* Sumar dos polinomios
* Multiplicar dos polinomios

## 🔹 Extras

También hay operaciones especiales para combinar formas diferentes:
- Sumar Forma1 + Forma2 = resultado en Forma3
- Multiplicar Forma3 * Forma2 = resultado en Forma1







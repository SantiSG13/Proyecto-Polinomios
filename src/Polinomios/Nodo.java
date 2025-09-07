// -----------------------------------------------------
// Clase Nodo: Nodo para lista enlazada de polinomios (Forma3)
// -----------------------------------------------------
package Polinomios;

public class Nodo {
    int coef;     // coeficiente
    int expo;     // exponente
    Nodo liga;    // enlace al siguiente nodo

    // -----------------------------------------------------
    // Constructor para crear un nuevo nodo
    // -----------------------------------------------------
    public Nodo(int coef, int expo) {
        this.coef = coef;
        this.expo = expo;
        this.liga = null;
    }
}

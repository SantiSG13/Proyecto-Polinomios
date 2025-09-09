package Polinomios;

import javax.swing.*;

public class Principal {

    // -----------------------------------------------------
    // Metodo principal: punto de entrada del prpgrama
    // -----------------------------------------------------
    public static void main(String[] args) {
        int opc1;
        int opc2;
        // vectorResultado: almacena pares coeficiente, exponente
        String[] vectorResultado = Ordenar(Ajustar(IngresoPolinomio()));
        mostrarVectorDialog("Vector Resultado", vectorResultado);

        // Instancias de las tres formas
        Forma1 F1 = new Forma1(Integer.parseInt(vectorResultado[1]));
        Forma2 F2 = new Forma2(vectorResultado.length / 2);
        Forma3 F3 = new Forma3();

        // -----------------------------------------------------
        // Menú principal: selección de forma y operaciones
        // -----------------------------------------------------
        do {
            opc2 = MenuFormas();
            switch (opc2) {
                case 1:
                    F1.Construir(vectorResultado);
                    // Submenú de operaciones para Forma1
                    do {
                        opc1 = Manu();
                        switch (opc1) {
                            case 1:
                                //Ingresar término
                                int coef = Integer.parseInt(JOptionPane.showInputDialog("Digite el coeficiente del polinomio que quiere agregar: \n"));
                                int exp = Integer.parseInt(JOptionPane.showInputDialog("Digite el exponente del polinomio que quiere agregar: \n"));
                                F1.InsertarTermino(coef, exp);
                                JOptionPane.showMessageDialog(null, "Término insertado.");
                                break;
                            case 2:
                                //Eliminar término
                                int Exp = Integer.parseInt(JOptionPane.showInputDialog("Digite el exponente del polinomio que quiere eliminar: \n"));
                                F1.EliminarTermino(Exp);
                                JOptionPane.showMessageDialog(null, "Término eliminado.");
                                break;
                            case 3:
                                //Mostrar forma (vector interno)
                                mostrarVectorDialog("Forma 1", F1.getVPF1());
                                break;
                            case 4:
                                //Mostrar polinomio algebraico
                                JOptionPane.showMessageDialog(null, F1.MostrarPolinomio(), "Polinomio", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 5:
                                //Evaluar polinomio en x
                                int valorX = Integer.parseInt(JOptionPane.showInputDialog("Digite el valor de x para evaluar el polinomio: \n"));
                                int resultado = F1.EvaluarPolinomio(valorX);
                                JOptionPane.showMessageDialog(null, "El resultado de evaluar el polinomio con x = " + valorX + " es: " + resultado);
                                break;
                            case 6:
                                //Sumar polinomios
                                JOptionPane.showMessageDialog(null, "Ingrese el segundo polinomio para sumarlo al primero que ingreso:");
                                String[] vectorResultado1_2 = Ordenar(Ajustar(IngresoPolinomio()));
                                Forma1 F1_2 = new Forma1(Integer.parseInt(vectorResultado1_2[1]));
                                F1_2.Construir(vectorResultado1_2);
                                F1 = F1.SumarPolinomios(F1, F1_2);
                                mostrarVectorDialog("Suma de polinomios en Forma 1", F1.getVPF1());
                                break;
                            case 7:
                                //Multiplicar polinomios
                                JOptionPane.showMessageDialog(null, "Ingrese el segundo polinomio para multiplicarlo por el primero que ingreso:");
                                String[] vectorResultado1_3 = Ordenar(Ajustar(IngresoPolinomio()));
                                Forma1 F1_3 = new Forma1(Integer.parseInt(vectorResultado1_3[1]));
                                F1_3.Construir(vectorResultado1_3);
                                F1 = F1.MultiplicarPolinomios(F1, F1_3);
                                mostrarVectorDialog("Multiplicación de polinomios en Forma 1", F1.getVPF1());
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(null, "Saliendo...");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opcion no valida");
                        }
                    } while (opc1 != 0);
                    break;
                case 2:
                    F2.Construir(vectorResultado);
                    // Submenú de operaciones para Forma2
                    do {
                        opc1 = Manu();
                        switch (opc1) {
                            case 1:
                                //Ingresar término
                                int coef2 = Integer.parseInt(JOptionPane.showInputDialog("Digite el coeficiente del polinomio que quiere agregar: \n"));
                                int exp2 = Integer.parseInt(JOptionPane.showInputDialog("Digite el exponente del polinomio que quiere agregar: \n"));
                                F2.InsertarTermino(coef2, exp2);
                                JOptionPane.showMessageDialog(null, "Término insertado.");
                                break;
                            case 2:
                                //Eliminar término
                                int Exp2 = Integer.parseInt(JOptionPane.showInputDialog("Digite el exponente del polinomio que quiere eliminar: \n"));
                                F2.EliminarTermino(Exp2);
                                JOptionPane.showMessageDialog(null, "Término eliminado.");
                                break;
                            case 3:
                                //Mostrar forma (vector interno)
                                mostrarVectorDialog("Forma 2", F2.getVPF2());
                                break;
                            case 4:
                                //Mostrar polinomio algebraico
                                JOptionPane.showMessageDialog(null, F2.MostrarPolinomio(), "Polinomio", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 5:
                                //Evaluar polinomio en x
                                int x = Integer.parseInt(JOptionPane.showInputDialog("Digite el valor de x para evaluar el polinomio: \n"));
                                int resultado2 = F2.EvaluarPolinomio(x);
                                JOptionPane.showMessageDialog(null, "El resultado de evaluar el polinomio con x = " + x + " es: " + resultado2);
                                break;
                            case 6:
                                //Sumar polinomios
                                JOptionPane.showMessageDialog(null, "Ingrese el segundo polinomio para sumarlo al primero que ingreso:");
                                String[] vectorResultado2_2 = Ordenar(Ajustar(IngresoPolinomio()));
                                Forma2 F2_2 = new Forma2(vectorResultado2_2.length / 2);
                                F2_2.Construir(vectorResultado2_2);
                                F2 = F2.SumarPolinomios(F2, F2_2);
                                mostrarVectorDialog("Suma de polinomios en Forma 2", F2.getVPF2());
                                break;
                            case 7:
                                //Multiplicar polinomios
                                JOptionPane.showMessageDialog(null, "Ingrese el segundo polinomio para multiplicarlo por el primero que ingreso:");
                                String[] vectorResultado2_3 = Ordenar(Ajustar(IngresoPolinomio()));
                                Forma2 F2_3 = new Forma2(vectorResultado2_3.length / 2);
                                F2_3.Construir(vectorResultado2_3);
                                F2 = F2.MultiplicarPolinomios(F2, F2_3);
                                mostrarVectorDialog("Multiplicación de polinomios en Forma 2", F2.getVPF2());
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(null, "Saliendo...");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opcion no valida");
                        }
                    } while (opc1 != 0);
                    break;
                case 3:
                    F3.Construir(vectorResultado);
                    // Submenú de operaciones para Forma3
                    do {
                        opc1 = Manu();
                        switch (opc1) {
                            case 1:
                                //Ingresar término
                                int coef3 = Integer.parseInt(JOptionPane.showInputDialog("Digite el coeficiente del polinomio que quiere agregar: \n"));
                                int exp3 = Integer.parseInt(JOptionPane.showInputDialog("Digite el exponente del polinomio que quiere agregar: \n"));
                                F3.InsertarTermino(coef3, exp3);
                                JOptionPane.showMessageDialog(null, "Término insertado.");
                                break;
                            case 2:
                                //Eliminar término
                                int Exp3 = Integer.parseInt(JOptionPane.showInputDialog("Digite el exponente del polinomio que quiere eliminar: \n"));
                                F3.EliminarTermino(Exp3);
                                JOptionPane.showMessageDialog(null, "Término eliminado.");
                                break;
                            case 3:
                                //Mostrar forma (lista interna)
                                JOptionPane.showMessageDialog(null, F3.MostrarLista(), "Forma 3 (lista)", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 4:
                                // Mostrar polinomio algebraico
                                JOptionPane.showMessageDialog(null, F3.MostrarPolinomio(), "Polinomio", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 5:
                                //Evaluar polinomio en x
                                int x3 = Integer.parseInt(JOptionPane.showInputDialog("Digite el valor de x para evaluar el polinomio: \n"));
                                int resultado3 = F3.EvaluarPolinomio(x3);
                                JOptionPane.showMessageDialog(null, "\"El resultado de evaluar el polinomio con x = \"" + x3 + " es: " + resultado3);
                                break;
                            case 6:
                                // Sumar polinomios
                                JOptionPane.showMessageDialog(null, "Ingrese el segundo polinomio para sumarlo al primero que ingreso:");
                                String[] vectorResultado3_2 = Ordenar(Ajustar(IngresoPolinomio()));
                                Forma3 F3_2 = new Forma3();
                                F3_2.Construir(vectorResultado3_2);
                                F3 = Forma3.SumarPolinomios(F3, F3_2);
                                JOptionPane.showMessageDialog(null, F3.MostrarLista(), "Resultado de la suma en Forma 3", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 7:
                                //Multiplicar polinomios
                                JOptionPane.showMessageDialog(null, "Ingrese el segundo polinomio para multiplicarlo por el primero que ingreso:");
                                String[] vectorResultado3_3 = Ordenar(Ajustar(IngresoPolinomio()));
                                Forma3 F3_3 = new Forma3();
                                F3_3.Construir(vectorResultado3_3);
                                F3 = Forma3.MultiplicarPolinomios(F3, F3_3);
                                JOptionPane.showMessageDialog(null, F3.MostrarLista(), "Resultado de la multiplicación en Forma 3", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(null, "Saliendo...");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opcion no valida");
                        }
                    } while (opc1 != 0);
                    break;
                case 4:
                    // Sumar Forma1 + Forma2 = Forma3
                    F1.Construir(vectorResultado);
                    JOptionPane.showMessageDialog(null, "Ingrese el segundo polinomio para sumar formas (Forma1 + Forma2 = Forma3):");
                    String[] vectorResultado4 = Ordenar(Ajustar(IngresoPolinomio()));
                    Forma2 F2_4 = new Forma2(vectorResultado4.length / 2);
                    F2_4.Construir(vectorResultado4);
                    Forma3 resultado4 = Forma3.SumarF1_F2(F1, F2_4);
                    JOptionPane.showMessageDialog(null, resultado4.MostrarLista(), "Resultado de Forma1 + Forma2 = Forma3 (lista)", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 5:
                    // Multiplicar Forma3 * Forma2 = Forma1
                    F3.Construir(vectorResultado);
                    JOptionPane.showMessageDialog(null, "Ingrese el segundo polinomio para multiplicar formas (Forma3 * Forma2 = Forma1):");
                    String[] vectorResultado5 = Ordenar(Ajustar(IngresoPolinomio()));
                    Forma2 F2_5 = new Forma2(vectorResultado5.length / 2);
                    F2_5.Construir(vectorResultado5);
                    Forma1 resultado5 = Forma1.MultiplicarF3_F2(F3, F2_5);
                    mostrarVectorDialog("Multiplicación de Forma3 * Forma2 = Forma1", resultado5.getVPF1());
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida");
            }
        } while (opc2 != 0);
    }

    // -----------------------------------------------------
    // Menú para elegir la forma de representación
    // -----------------------------------------------------
    public static int MenuFormas() {
        return Integer.parseInt(JOptionPane.showInputDialog("Menu de formas\n"
                + "1. Forma 1\n"
                + "2. Forma 2\n"
                + "3. Forma 3\n"
                + "4. Forma 1 + Forma 2 = Forma 3\n"
                + "5. Forma 3 * Forma 2 = Forma 1\n"
                + "0. Salir\n"));
    }

    // -----------------------------------------------------
    // Menú de operaciones para cada forma
    // -----------------------------------------------------
    public static int Manu() {
        return Integer.parseInt(JOptionPane.showInputDialog("Menu de opciones\n"
                + "1. Ingresar un termino\n"
                + "2. Eliminar un termino\n"
                + "3. Mostrar la forma\n"
                + "4. Mostrar el polinomio \n"
                + "5. Evaluar el polinomio\n"
                + "6. Sumar dos polinomios\n"
                + "7. Multiplicar dos polinomios\n"
                + "0. Salir\n"));
    }

    // -----------------------------------------------------
    // Parser: convierte String polinomio a vectorResultado
    // -----------------------------------------------------
    public static String[] IngresoPolinomio() {
        String polinomioInput = JOptionPane.showInputDialog("Ingrese un polinomio (ejemplo: 2x^3+3x^4+7-8x): ");
        if (polinomioInput == null) {
            return new String[0];
        }
        polinomioInput = polinomioInput.replace(" ", "");

        char[] vectorCaracter = polinomioInput.toCharArray();

        String[] vectorResultado = new String[vectorCaracter.length * 2]; // espacio suficiente
        String numAcumulado = "";
        int j = 0;

        for (int i = 0; i < vectorCaracter.length; i++) {
            char caracterActual = vectorCaracter[i];

            if (caracterActual == '+' || caracterActual == '-') {
                if (numAcumulado.isEmpty()) {
                    if (caracterActual == '-') {
                        numAcumulado = "-";
                    }
                } else {
                    vectorResultado[j] = numAcumulado;
                    j++;
                    vectorResultado[j] = "0"; // exponente 0 para constante
                    j++;
                    if (caracterActual == '-') {
                        numAcumulado = "-";
                    } else {
                        numAcumulado = "";
                    }
                }

            } else if (Character.isDigit(caracterActual)) {
                numAcumulado += caracterActual;

            } else if (caracterActual == 'x' || caracterActual == 'X') {
                if (numAcumulado.isEmpty() || numAcumulado.equals("+")) {
                    vectorResultado[j++] = "1";
                } else if (numAcumulado.equals("-")) {
                    vectorResultado[j++] = "-1";
                } else {
                    vectorResultado[j++] = numAcumulado;
                }
                numAcumulado = "";

                // Revisar exponente explícito
                if (i + 1 < vectorCaracter.length && vectorCaracter[i + 1] == '^') {
                    String exponente = "";
                    i += 2; // saltar '^'
                    while (i < vectorCaracter.length && Character.isDigit(vectorCaracter[i])) {
                        exponente += vectorCaracter[i];
                        i++;
                    }
                    i--; // retroceder porque el for hará i++
                    vectorResultado[j++] = exponente;
                } else {
                    vectorResultado[j++] = "1"; // exponente implícito
                }
            }
        }

        // Si quedó un número suelto al final → constante
        if (!numAcumulado.isEmpty()) {
            vectorResultado[j++] = numAcumulado;
            vectorResultado[j++] = "0";
        }
        return vectorResultado;
    }

    // -----------------------------------------------------
    // Ajusta el vectorResultado eliminando nulls
    // -----------------------------------------------------
    public static String[] Ajustar(String[] vectorResultado) {
        int contador = 0;
        for (int i = 0; i < vectorResultado.length; i++) {
            if (vectorResultado[i] != null) {
                contador++;
            }
        }

        String[] ajustado = new String[contador];
        int j = 0;
        for (int i = 0; i < vectorResultado.length; i++) {
            if (vectorResultado[i] != null) {
                ajustado[j] = vectorResultado[i];
                j++;
            }
        }
        return ajustado;
    }

    // -----------------------------------------------------
    // Ordena el vectorResultado de mayor a menor exponente
    // -----------------------------------------------------
    public static String[] Ordenar(String[] polinomio) {
        //Ordenar el vector de mayor a menor
        String[] Vs = polinomio;
        int numParejas = Vs.length / 2;
        String tempExp, tempCoe;

        for (int i = 0; i < numParejas - 1; i++) {
            for (int j = 1; j < Vs.length - 1 - (2 * i); j += 2) { //Reduce el rango de comparacion con cada ciclo de i
                if (Integer.parseInt(Vs[j]) < Integer.parseInt(Vs[j + 2])) {

                    //Intercambio de exponentes
                    tempExp = Vs[j];
                    Vs[j] = Vs[j + 2];
                    Vs[j + 2] = tempExp;

                    //Intercambio de coeficientes
                    tempCoe = Vs[j - 1];
                    Vs[j - 1] = Vs[j + 1];
                    Vs[j + 1] = tempCoe;
                }
            }
        }
        return Vs;
    }

    // -----------------------------------------------------
    // Muestra el vectorResultado en un diálogo
    // -----------------------------------------------------
    public static void mostrarVectorDialog(String titulo, String[] vector) {
        String resultado = "[";
        for (int i = 0; i < vector.length; i++) {
            resultado += vector[i];
            if (i < vector.length - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";

        JOptionPane.showMessageDialog(null, resultado, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------
    // Muestra el vector de enteros en un diálogo
    // -----------------------------------------------------
    public static void mostrarVectorDialog(String titulo, int[] vector) {
        String resultado = "[";
        for (int i = 0; i < vector.length; i++) {
            resultado += vector[i];
            if (i < vector.length - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";

        JOptionPane.showMessageDialog(null, resultado, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
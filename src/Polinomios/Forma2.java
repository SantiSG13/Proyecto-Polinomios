// -----------------------------------------------------
// Clase Forma2: Representación de polinomio como vector [número de términos, coef, expo, ...]
// -----------------------------------------------------
package Polinomios;

public class Forma2 {
    // Du: Datos Útiles (número de términos)
    private int Du, VPF2[];

    // -----------------------------------------------------
    // Constructor: inicializa el vector según el número de términos
    // -----------------------------------------------------
    public Forma2(int Terminos) {
        Du = Terminos * 2;
        VPF2 = new int [Du + 1];
        VPF2[0] = Terminos;
    }

    // -----------------------------------------------------
    // Métodos getter y setter
    // -----------------------------------------------------
    public int getDu() {
        return Du;
    }

    public void setDu(int Du) {
        this.Du = Du;
    }

    public int[] getVPF2() {
        return VPF2;
    }

    public void setVPF2(int[] VPF2) {
        this.VPF2 = VPF2;
    }

    public int getVPF2(int i) {
        return VPF2[i];
    }

    public void setVPF2 (int i, int d) {
        VPF2[i] = d;
    }

    // -----------------------------------------------------
    // Construir: convierte vectorResultado en Forma2
    // -----------------------------------------------------
    public void Construir(String[] vectorResultado) {
        int numTerminos = vectorResultado.length / 2;
        VPF2 = new int[numTerminos * 2 + 1];
        VPF2[0] = numTerminos;
        for (int i = 0; i < vectorResultado.length; i++) {
            VPF2[i + 1] = Integer.parseInt(vectorResultado[i]);
        }
    }

    // -----------------------------------------------------
    // InsertarTermino: agrega o actualiza coeficiente en exponente dado
    // -----------------------------------------------------
    public void InsertarTermino(int coef, int exp) {
        if (coef == 0) {
            return; // No insertamos términos con coeficiente cero
        }
        // Verificar si el exponente ya existe
        int numTerminos = VPF2[0];
        for (int i = 1; i <= 1 + (numTerminos - 1) * 2; i += 2) {
            if (VPF2[i + 1] == exp) {
                VPF2[i] += coef; // Si el exponente ya existe, sumamos los coeficientes
                if (VPF2[i] == 0) {
                    EliminarTerminoPorPosicion(i); // Eliminar el término si el coeficiente es cero
                }
                return;
            }
        }
        AgregarTermino(coef, exp);
    }

    // -----------------------------------------------------
    // EliminarTerminoPorPosicion: elimina el término en la posición dada
    // -----------------------------------------------------
    private void EliminarTerminoPorPosicion(int pos) {
        int numTerminos = VPF2[0];
        if (numTerminos == 0) return;
        int ultimoIndiceCoef = 1 + (numTerminos - 1) * 2;
        if (pos < 1 || pos > ultimoIndiceCoef) return; // Posición inválida
        int[] nuevoVPF2 = new int[(numTerminos - 1) * 2 + 1];
        nuevoVPF2[0] = numTerminos - 1; // Actualizamos el número de términos
        int j = 1;
        for (int i = 1; i <= ultimoIndiceCoef; i += 2) {
            if (i != pos) { // Copiamos todos menos el que queremos eliminar
                nuevoVPF2[j] = VPF2[i];
                nuevoVPF2[j + 1] = VPF2[i + 1];
                j += 2;
            }
        }
        VPF2 = nuevoVPF2;
    }

    // -----------------------------------------------------
    // AgregarTermino: agrega un nuevo término al final del vector
    // -----------------------------------------------------
    private void AgregarTermino(int coef, int exp) {
        int numTerminos = VPF2[0];
        int newNumTerminos = numTerminos + 1;
        int[] nuevoVPF2 = new int[newNumTerminos * 2 + 1];
        nuevoVPF2[0] = newNumTerminos; // Actualizamos el número de términos
        // Copiar términos existentes (si los hay)
        for (int i = 1; i <= numTerminos * 2; i++) {
            nuevoVPF2[i] = VPF2[i];
        }
        // Agregar nuevo término al final (posición correcta)
        int insertPos = 1 + numTerminos * 2;
        nuevoVPF2[insertPos] = coef;
        nuevoVPF2[insertPos + 1] = exp;
        VPF2 = nuevoVPF2;
        OrdenarTerminos();
    }

    // -----------------------------------------------------
    // OrdenarTerminos: ordena los términos de mayor a menor exponente
    // -----------------------------------------------------
    private void OrdenarTerminos() {
        if (!VerificarIntegridad()) {
            return;
        }
        int numTerminos = VPF2[0];
        // Metodo burbuja para ordenar de mayor a menor exponente
        for (int i = 1; i <= 1 + (numTerminos - 1) * 2; i += 2) {
            for (int j = 1; j <= 1 + (numTerminos - 1) * 2 - 2; j += 2) {
                if (VPF2[j + 1] < VPF2[j + 3]) {
                    // Intercambiar coeficientes
                    int tempCoe = VPF2[j];
                    VPF2[j] = VPF2[j + 2];
                    VPF2[j + 2] = tempCoe;
                    // Intercambiar exponentes
                    int tempExp = VPF2[j + 1];
                    VPF2[j + 1] = VPF2[j + 3];
                    VPF2[j + 3] = tempExp;
                }
            }
        }
    }

    // -----------------------------------------------------
    // VerificarIntegridad: verifica que el vector tenga la estructura correcta
    // -----------------------------------------------------
    private boolean VerificarIntegridad() {
        if (VPF2 == null || VPF2.length < 1) return false;
        int numTerminos = VPF2[0];
        int tamanosEsperado = numTerminos * 2 + 1;
        return VPF2.length == tamanosEsperado;
    }

    // -----------------------------------------------------
    // EliminarTermino: elimina el término con exponente dado
    // -----------------------------------------------------
    public void EliminarTermino(int exp) {
        int numTerminos = VPF2[0];
        if( numTerminos == 0) {
            return; // No hay términos que eliminar
        }
        int ultimoIndiceCoef = 1 + (numTerminos - 1) * 2;
        for (int i = 1; i <= ultimoIndiceCoef; i += 2) {
            if (VPF2[i + 1] == exp) {
                EliminarTerminoPorPosicion(i);
                return; // Salir después de eliminar
            }
        }
    }

    // -----------------------------------------------------
    // MostrarPolinomio: reconstruye el polinomio en notación algebraica
    // -----------------------------------------------------
    public String MostrarPolinomio() {
        int numTerminos = VPF2[0];
        if (numTerminos == 0) {
            return "0";
        }
        String polinomio = "";
        boolean primerTermino = true; // Para controlar el primer término
        int ultimoIndiceCoef = 1 + (numTerminos - 1) * 2;
        for (int i = 1; i <= ultimoIndiceCoef; i += 2) {
            int coef = VPF2[i];
            int exp = VPF2[i + 1];
            if (coef == 0) {
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
        }
        if (polinomio.isEmpty()) {
            return "0";
        } else {
            return polinomio;
        }
    }

    // -----------------------------------------------------
    // EvaluarPolinomio: evalúa el polinomio en un valor de x
    // -----------------------------------------------------
    public int EvaluarPolinomio(int x) {
        int resultado = 0;
        int numTerminos = VPF2[0];
        int ultimoIndiceCoef = 1 + (numTerminos - 1) * 2;
        for (int i = 1; i <= ultimoIndiceCoef; i += 2) {
            int coef = VPF2[i];
            int exp = VPF2[i + 1];
            int potencia = 1;
            for (int j = 0; j < exp; j++) {
                potencia *= x;
            }
            resultado += coef * potencia;
        }
        return resultado;
    }

    // -----------------------------------------------------
    // SumarPolinomios: suma dos polinomios en Forma2
    // -----------------------------------------------------
    public Forma2 SumarPolinomios(Forma2 F2, Forma2 F2_2) {
        int maxTerminos = F2.getVPF2()[0] + F2_2.getVPF2()[0];
        Forma2 resultado = new Forma2(maxTerminos);
        resultado.getVPF2()[0] = 0;  // Inicializar en 0 términos
        // Agregar términos del primer polinomio
        int last1 = 1 + (F2.getVPF2()[0] - 1) * 2;
        for (int i = 1; i <= last1; i += 2) {
            int coef = F2.getVPF2()[i];
            int exp = F2.getVPF2()[i + 1];
            if (coef != 0) {
                resultado.InsertarTermino(coef, exp);
            }
        }
        // Agregar términos del segundo polinomio
        int last2 = 1 + (F2_2.getVPF2()[0] - 1) * 2;
        for (int i = 1; i <= last2; i += 2) {
            int coef = F2_2.getVPF2()[i];
            int exp = F2_2.getVPF2()[i + 1];
            if (coef != 0) {
                resultado.InsertarTermino(coef, exp);
            }
        }
        resultado.OrdenarTerminos(); // Asegura orden
        return resultado;
    }

    // -----------------------------------------------------
    // MultiplicarPolinomios: multiplica dos polinomios en Forma2
    // -----------------------------------------------------
    public Forma2 MultiplicarPolinomios(Forma2 F2, Forma2 F2_2) {
        int maxTerminos = F2.getVPF2()[0] * F2_2.getVPF2()[0];
        Forma2 resultado = new Forma2(maxTerminos);
        resultado.getVPF2()[0] = 0;
        int last1 = 1 + (F2.getVPF2()[0] - 1) * 2;
        int last2 = 1 + (F2_2.getVPF2()[0] - 1) * 2;
        for (int i = 1; i <= last1; i += 2) {
            for (int j = 1; j <= last2; j += 2) {
                int coefResultado = F2.getVPF2()[i] * F2_2.getVPF2()[j];
                int expResultado = F2.getVPF2()[i + 1] + F2_2.getVPF2()[j + 1];
                resultado.InsertarTermino(coefResultado, expResultado);
            }
        }
        resultado.OrdenarTerminos(); // Asegura orden
        return resultado;
    }
}


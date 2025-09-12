// -----------------------------------------------------
// Clase Forma1: Representación de polinomio como vector [grado, coef...]
// -----------------------------------------------------
package Polinomios;

public class Forma1 {
    private int Du, VPF1[]; // Du: Datos Útiles

    // -----------------------------------------------------
    // Constructor: inicializa el vector según el grado
    // -----------------------------------------------------
    public Forma1(int grado) {
        Du = grado + 1;
        VPF1 = new int [Du + 1];
        VPF1[0] = grado;
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

    public int[] getVPF1() {
        return VPF1;
    }

    public void setVPF1(int[] VPF1) {
        this.VPF1 = VPF1;
    }

    public int getVPF1(int i) {
        return VPF1[i];
    }

    public void setVPF1 (int i, int dato) {
        VPF1[i] = dato;
    }

    // -----------------------------------------------------
    // Construir: convierte vectorResultado en Forma1
    // -----------------------------------------------------
    public void Construir(String[] vectorResultado) {
        // El grado ya viene en la posición 1 del vectorResultado
        int grado = Integer.parseInt(vectorResultado[1]);
        VPF1 = new int[grado + 2];
        VPF1[0] = grado;
        // Recorremos de 2 en 2: coef, exp
        for (int i = 0; i < vectorResultado.length; i += 2) {
            int coef = Integer.parseInt(vectorResultado[i]);
            int exp = Integer.parseInt(vectorResultado[i + 1]);
            // Ajustamos la posición en el vector Forma1
            int pos = grado - exp + 1;
            VPF1[pos] = coef;
        }
    }

    // -----------------------------------------------------
    // Insertar término: agrega o actualiza coeficiente en exponente dado
    // -----------------------------------------------------
    public void InsertarTermino(int coe, int exp) {
        //Si el exponente es mayor que el grado actual, redimensionar
        if (exp > VPF1[0]) {
            AumentarGrado(exp);
        }
        int grado = VPF1[0];
        int pos = grado - exp + 1;
        if (pos >= 1 && pos < VPF1.length) {
            VPF1[pos] += coe;
        }
        if (VPF1[pos] == 0 && exp == grado) {
            ReducirGrado();
        }
    }

    // -----------------------------------------------------
    // AumentarGrado: redimensiona el vector si el exponente es mayor
    // -----------------------------------------------------
    public void AumentarGrado(int nuevoGrado) {
        int gradoActual = VPF1[0];
        int[] nuevoVPF1 = new int[nuevoGrado + 2];
        nuevoVPF1[0] = nuevoGrado;
        //Copia los coeficientes existentes y los coloca en el orden que corresponde
        for (int i = 1; i < VPF1.length; i++) {
            int exp = gradoActual - (i - 1);
            int pos = nuevoGrado - exp + 1;
            if (pos >= 1 && pos < nuevoVPF1.length) {
                nuevoVPF1[pos] = VPF1[i];
            }
        }
        VPF1 = nuevoVPF1;
    }

    // -----------------------------------------------------
    // EliminarTermino: elimina el término con exponente dado
    // -----------------------------------------------------
    public void EliminarTermino(int exp) {
        int grado = VPF1[0];
        int pos = grado - exp + 1;
        if (pos >= 1 && pos < VPF1.length) {
            VPF1[pos] = 0; // Elimina el término estableciendo el coeficiente a 0
            if (exp == grado) {
                ReducirGrado();
            }
        }
    }

    // -----------------------------------------------------
    // ReducirGrado: ajusta el grado si el mayor exponente se elimina
    // -----------------------------------------------------
    public void ReducirGrado() {
        int gradoActual = VPF1[0];
        int nuevoGrado = gradoActual;
        //Busca el mayor exponente con coeficiente diferente de cero
        for (int i = 1; i < VPF1.length; i++) {
            int coef = VPF1[i];
            int exp = gradoActual - (i - 1);
            if (coef != 0) {
                nuevoGrado = exp;
                break;
            }
        }
        if (nuevoGrado < gradoActual) {
            int[] nuevoVPF1 = new int[nuevoGrado + 2];
            nuevoVPF1[0] = nuevoGrado;
            for (int i = 1; i < nuevoVPF1.length; i++) {
                int exp = nuevoGrado - (i - 1);
                int posAntigua = gradoActual - exp + 1;
                if (posAntigua >= 1 && posAntigua < VPF1.length) {
                    nuevoVPF1[i] = VPF1[posAntigua];
                }
            }
            VPF1 = nuevoVPF1;
        }
    }

    // -----------------------------------------------------
    // MostrarPolinomio: reconstruye el polinomio en notación algebraica
    // -----------------------------------------------------
    public String MostrarPolinomio() {
        int grado = VPF1[0];
        String polinomio = "";
        boolean primerTermino = true; // Para controlar el primer término
        for (int i = 1; i < VPF1.length; i++) {
            int coef = VPF1[i];
            int exp = grado - (i - 1);
            // Si el coeficiente es 0, omitir este término
            if (coef == 0) {
                continue;
            }
            // Agregar signo solo si no es el primer término y el coeficiente es positivo
            if (!primerTermino && coef > 0) {
                polinomio += "+";
            }
            // Procesar según el exponente
            if (exp == 0) {
                polinomio += coef;
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
        // Si el polinomio quedó vacío (todos los coeficientes eran 0)
        if (polinomio.isEmpty()) {
            polinomio = "0";
        }
        return polinomio;
    }

    // -----------------------------------------------------
    // EvaluarPolinomio: evalúa el polinomio en un valor de x
    // -----------------------------------------------------
    public int EvaluarPolinomio(int x) {
        int grado = VPF1[0];
        int resultado = 0;
        for (int i = 1; i < VPF1.length; i++) {
            int coef = VPF1[i];
            int exp = grado - (i - 1);
            // calcular x^exp sin usar Math.pow
            int potencia = 1;
            for (int j = 0; j < exp; j++) {
                potencia *= x;
            }
            resultado += coef * potencia;
        }
        return resultado;
    }

    // -----------------------------------------------------
    // SumarPolinomios: suma dos polinomios en Forma1
    // -----------------------------------------------------
    public Forma1 SumarPolinomios(Forma1 F1, Forma1 F2) {
        int grado1 = F1.getVPF1()[0];
        int grado2 = F2.getVPF1()[0];
        int gradoMax;
        if (grado1 >= grado2) {
            gradoMax = grado1;
        } else {
            gradoMax = grado2;
        }
        Forma1 resultado = new Forma1(gradoMax);
        for (int i = 1; i <= gradoMax + 1; i++) {
            int coef1;
            if (i <= F1.getVPF1().length) {
                coef1 = F1.getVPF1()[i];
            } else {
                coef1 = 0;
            }

            int coef2;
            if (i <= F2.getVPF1().length) {
                coef2 = F2.getVPF1()[i];
            } else {
                coef2 = 0;
            }

            resultado.getVPF1()[i] = coef1 + coef2;
        }
        return resultado;
    }

    // -----------------------------------------------------
    // MultiplicarPolinomios: multiplica dos polinomios en Forma1
    // -----------------------------------------------------
    public Forma1 MultiplicarPolinomios(Forma1 F1, Forma1 F2) {
        int grado1 = F1.getVPF1()[0];
        int grado2 = F2.getVPF1()[0];
        int gradoResultado = grado1 + grado2;
        Forma1 resultado = new Forma1(gradoResultado);
        // Inicializamos los coeficientes en 0
        for (int i = 1; i < resultado.getVPF1().length; i++) {
            resultado.getVPF1()[i] = 0;
        }
        // Multiplicar todos los términos
        for (int i = 1; i < F1.getVPF1().length; i++) {
            for (int j = 1; j < F2.getVPF1().length; j++) {
                int coef1 = F1.getVPF1()[i];
                int coef2 = F2.getVPF1()[j];
                int exp1 = grado1 - (i - 1);
                int exp2 = grado2 - (j - 1);
                int expRes = exp1 + exp2;
                int pos = (gradoResultado - expRes) + 1;
                resultado.getVPF1()[pos] += coef1 * coef2;
            }
        }
        return resultado;
    }

    // -----------------------------------------------------
    // Multiplicar polinomios de Forma3 y Forma2, devuelve nuevo Forma1
    // -----------------------------------------------------
    public static Forma1 MultiplicarF3_F2(Forma3 f3, Forma2 f2) {
        // Determinar grado máximo posible
        int gradoMax = 0;
        Nodo actual3 = f3.cabeza;
        while (actual3 != null) {
            if (actual3.expo > gradoMax) {
                gradoMax = actual3.expo;
            }
            actual3 = actual3.liga;
        }
        int numTerminos2 = f2.getVPF2()[0];
        int ultimoIndice = 1 + (numTerminos2 - 1) * 2;
        for (int i = 1; i <= ultimoIndice; i += 2) {
            int exp2 = f2.getVPF2()[i + 1];
            gradoMax += exp2; // Grado max de multi
        }
        Forma1 resultado = new Forma1(gradoMax);

        // Multiplicar cada término de Forma3 por cada de Forma2
        actual3 = f3.cabeza;
        while (actual3 != null) {
            for (int i = 1; i <= ultimoIndice; i += 2) {
                int coef2 = f2.getVPF2()[i];
                int exp2 = f2.getVPF2()[i + 1];
                if (coef2 != 0) {
                    int coefRes = actual3.coef * coef2;
                    int expRes = actual3.expo + exp2;
                    // Calcular posición en Forma1
                    int pos = resultado.getVPF1()[0] - expRes + 1;
                    if (pos >= 1 && pos < resultado.getVPF1().length) {
                        resultado.setVPF1(pos, resultado.getVPF1(pos) + coefRes); // Acumular
                    }
                }
            }
            actual3 = actual3.liga;
        }

        // Reducir grado si ceros iniciales
        resultado.ReducirGrado();

        return resultado;
    }
}
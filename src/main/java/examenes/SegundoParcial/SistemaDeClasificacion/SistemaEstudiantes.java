class NodoEstudiante {
    int matricula;
    String nombre;
    double promedio;
    NodoEstudiante siguiente;

    public NodoEstudiante(int matricula, String nombre, double promedio) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.promedio = promedio;
        this.siguiente = null;
    }
}

public class SistemaEstudiantes {
    private NodoEstudiante cabeza;
    private boolean ordenDescendente;

    public SistemaEstudiantes() {
        this.cabeza = null;
        this.ordenDescendente = true;
    }

    public NodoEstudiante buscar(int matricula) {
        NodoEstudiante actual = cabeza;
        while (actual != null) {
            if (actual.matricula == matricula) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public void insertar(int matricula, String nombre, double promedio) {
        if (buscar(matricula) != null) {
            return;
        }

        NodoEstudiante nuevo = new NodoEstudiante(matricula, nombre, promedio);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        boolean insertarInicio = false;
        if (ordenDescendente) {
            if (nuevo.promedio > cabeza.promedio) {
                insertarInicio = true;
            }
        } else {
            if (nuevo.promedio < cabeza.promedio) {
                insertarInicio = true;
            }
        }

        if (insertarInicio) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            return;
        }

        NodoEstudiante actual = cabeza;
        while (actual.siguiente != null) {
            boolean avanzar = false;
            if (ordenDescendente) {
                if (actual.siguiente.promedio >= nuevo.promedio) {
                    avanzar = true;
                }
            } else {
                if (actual.siguiente.promedio <= nuevo.promedio) {
                    avanzar = true;
                }
            }

            if (avanzar) {
                actual = actual.siguiente;
            } else {
                break;
            }
        }

        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("No existen estudiantes.");
            return;
        }
        System.out.print("MOSTRAR: ");
        NodoEstudiante actual = cabeza;
        while (actual != null) {
            System.out.print(actual.matricula + " " + actual.nombre + " " + actual.promedio);
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public void invertir() {
        if (cabeza == null) {
            System.out.println("No existen estudiantes.");
            return;
        }
        
        NodoEstudiante previo = null;
        NodoEstudiante actual = cabeza;
        NodoEstudiante siguiente = null;
        
        while (actual != null) {
            siguiente = actual.siguiente;
            actual.siguiente = previo;
            previo = actual;
            actual = siguiente;
        }
        
        cabeza = previo;
        ordenDescendente = !ordenDescendente;
    }

    public static void main(String[] args) {
        SistemaEstudiantes se = new SistemaEstudiantes();
        
        se.insertar(101, "Ana", 9.5);
        se.insertar(102, "Pedro", 8.7);
        se.insertar(103, "Luis", 7.9);
        
        se.mostrar();
        se.invertir();
        se.mostrar();
    }
}
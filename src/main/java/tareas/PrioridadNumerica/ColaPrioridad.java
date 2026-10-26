class Nodo {
    int dato;
    int prioridad;
    Nodo siguiente;

    public Nodo(int dato, int prioridad) {
        this.dato = dato;
        this.prioridad = prioridad;
        this.siguiente = null;
    }
}

public class ColaPrioridad {
    private Nodo frente;

    public ColaPrioridad() {
        this.frente = null;
    }

    public void enqueue(int dato, int prioridad) {
        if (prioridad < 1 || prioridad > 3) {
            System.out.println("Error: Prioridad " + prioridad + " no valida. Solo 1 (Alta), 2 (Media) o 3 (Baja).");
            return;
        }

        Nodo nuevo = new Nodo(dato, prioridad);

        if (frente == null || prioridad < frente.prioridad) {
            nuevo.siguiente = frente;
            frente = nuevo;
        } else {
            Nodo actual = frente;

            while (actual.siguiente != null && actual.siguiente.prioridad <= prioridad) {
                actual = actual.siguiente;
            }

            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        System.out.println("Ingreso -> Dato: " + dato + " | Prioridad: " + prioridad);
    }

    public void dequeue() {
        if (frente == null) {
            System.out.println("La cola esta vacia.");
            return;
        }
        System.out.println("Atendiendo -> Dato: " + frente.dato + " | Prioridad: " + frente.prioridad);
        frente = frente.siguiente;
    }

    public void mostrar() {
        if (frente == null) {
            System.out.println("Cola vacia.");
            return;
        }
        System.out.print("Estado de la fila: ");
        Nodo actual = frente;
        while (actual != null) {
            System.out.print("[" + actual.dato + " (P" + actual.prioridad + ")] -> ");
            actual = actual.siguiente;
        }
        System.out.println("null\n");
    }

    public static void main(String[] args) {
        ColaPrioridad cp = new ColaPrioridad();

        System.out.println("--- REGISTRANDO DATOS ---");
        cp.enqueue(10, 2);
        cp.enqueue(20, 1);
        cp.enqueue(30, 3);
        cp.enqueue(40, 2);
        cp.enqueue(50, 1);

        System.out.println();
        cp.mostrar();

        System.out.println("--- ATENDIENDO ---");
        cp.dequeue();
        cp.dequeue();

        System.out.println();
        cp.mostrar();
    }
}
package tareas.SistemaDePedidosEnCocina;

public class ColaPedidos {
    private Nodo<Pedido> frente;
    private Nodo<Pedido> finalCola;

    public ColaPedidos() {
        this.frente = null;
        this.finalCola = null;
    }

    public void enqueue(Pedido pedido) {
        Nodo<Pedido> nuevo = new Nodo<>(pedido);

        if (isEmpty()) {
            frente = nuevo;
            finalCola = nuevo;
        } else {
            finalCola.siguiente = nuevo;
            finalCola = nuevo;
        }
    }

    public Pedido dequeue() {
        if (isEmpty()) return null;

        Pedido despachado = frente.dato;
        frente = frente.siguiente;

        if (frente == null) {
            finalCola = null;
        }

        return despachado;
    }

    public Pedido peek() {
        if (isEmpty()) return null;
        return frente.dato;
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public void mostrar() {
        if (isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
            return;
        }

        Nodo<Pedido> actual = frente;
        while (actual != null) {
            System.out.println(actual.dato.toString());
            actual = actual.siguiente;
        }
    }

    public void buscar(int numeroPedido) {
        Nodo<Pedido> actual = frente;
        boolean encontrado = false;

        while (actual != null) {
            if (actual.dato.getNumero() == numeroPedido) {
                System.out.println("¡Pedido encontrado!\n" + actual.dato.toString());
                encontrado = true;
                break;
            }
            actual = actual.siguiente;
        }

        if (!encontrado) {
            System.out.println("No se encontró el pedido con número: " + numeroPedido);
        }
    }

    public int tiempoTotal() {
        int total = 0;
        Nodo<Pedido> actual = frente;

        while (actual != null) {
            total += actual.dato.getTiempoEstimado();
            actual = actual.siguiente;
        }
        return total;
    }
}
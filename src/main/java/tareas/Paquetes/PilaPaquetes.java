public class PilaPaquetes {

    Nodo tope;

    public PilaPaquetes() {
        this.tope = null;
    }

    public boolean isEmpty() {
        return tope == null;
    }

    public void push(Paquete NuevoPaquete) {
        Nodo NuevoNodo = new Nodo(NuevoPaquete);
        NuevoNodo.siguiente = tope;
        tope = NuevoNodo;

        System.out.println("Paquete agregado a la pila.");
    }
    
    public Paquete pop() {

        if (isEmpty()) {
            System.out.println("El almacén está vacío. No hay paquetes para retirar.");
            return null;
        }

        Paquete paqueteSacado = tope.paquete;

        tope = tope.siguiente;

        return paqueteSacado;
    }

    public Paquete peek() {
        if (isEmpty()) {
            System.out.println("El almacén está vacío.");
            return null;
        }
        
        return tope.paquete;
    }

    public void mostrar() {
        if (isEmpty()) {
            System.out.println("El almacén está vacío.");
            return;
        }
        
        System.out.println("\n--- PAQUETES EN EL ALMACÉN ---");
        
        Nodo actual = tope; 
        
        while (actual != null) {
            actual.paquete.mostrarInfo();
            System.out.println("------------------------------");
            actual = actual.siguiente; 
        }
    }

    public void buscarPaquete(int idBuscado) {
        if (isEmpty()) {
            System.out.println("El almacén está vacío. No se puede buscar.");
            return;
        }

        Nodo actual = tope;
        boolean encontrado = false;

        while (actual != null) {
            if (actual.paquete.id == idBuscado) {
                System.out.println("\nPaquete encontrado:");
                actual.paquete.mostrarInfo();
                encontrado = true;
                break; 
            }
            actual = actual.siguiente;
        }

        if (!encontrado) {
            System.out.println("\nNo existe un paquete con el ID " + idBuscado + ".");
        }
    }
}

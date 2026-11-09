class NodoPedido {
    int numeroPedido;
    String cliente;
    int cantidadProductos;
    String estado;
    NodoPedido siguiente;

    public NodoPedido(int numeroPedido, String cliente, int cantidadProductos, String estado) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.cantidadProductos = cantidadProductos;
        this.estado = estado;
        this.siguiente = null;
    }
}

public class SistemaPedidos {
    private NodoPedido frentePendientes, finalPendientes;
    private NodoPedido frentePreparacion, finalPreparacion;
    private NodoPedido frenteHistorial, finalHistorial;
    private int cantPreparacion;

    public SistemaPedidos() {
        this.frentePendientes = null;
        this.finalPendientes = null;
        this.frentePreparacion = null;
        this.finalPreparacion = null;
        this.frenteHistorial = null;
        this.finalHistorial = null;
        this.cantPreparacion = 0;
    }

    private boolean existeEn(NodoPedido frente, int numero) {
        NodoPedido actual = frente;
        while (actual != null) {
            if (actual.numeroPedido == numero) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    private boolean existePedido(int numero) {
        return existeEn(frentePendientes, numero) ||
               existeEn(frentePreparacion, numero) ||
               existeEn(frenteHistorial, numero);
    }

    public void registrarPedido(int numero, String cliente, int cantidad) {
        if (existePedido(numero)) return;

        NodoPedido nuevo = new NodoPedido(numero, cliente, cantidad, "PENDIENTE");

        if (frentePendientes == null) {
            frentePendientes = nuevo;
            finalPendientes = nuevo;
        } else {
            finalPendientes.siguiente = nuevo;
            finalPendientes = nuevo;
        }
    }

    public void prepararSiguiente() {
        if (frentePendientes == null) return;
        
        if (cantPreparacion >= 3) {
            System.out.println("PREPARAR_SIGUIENTE: capacidad maxima alcanzada");
            return;
        }

        NodoPedido aPreparar = frentePendientes;
        frentePendientes = frentePendientes.siguiente;
        if (frentePendientes == null) {
            finalPendientes = null;
        }

        aPreparar.estado = "EN_PREPARACION";
        aPreparar.siguiente = null;

        if (frentePreparacion == null) {
            frentePreparacion = aPreparar;
            finalPreparacion = aPreparar;
        } else {
            finalPreparacion.siguiente = aPreparar;
            finalPreparacion = aPreparar;
        }
        
        cantPreparacion++;
        
        System.out.print("PREPARAR_SIGUIENTE: en preparacion ");
        imprimirLista(frentePreparacion);
    }

    public void entregar(int numero) {
        if (frentePreparacion == null) return;

        NodoPedido actual = frentePreparacion;
        NodoPedido previo = null;

        while (actual != null && actual.numeroPedido != numero) {
            previo = actual;
            actual = actual.siguiente;
        }

        if (actual == null) return;

        if (previo == null) {
            frentePreparacion = frentePreparacion.siguiente;
            if (frentePreparacion == null) {
                finalPreparacion = null;
            }
        } else {
            previo.siguiente = actual.siguiente;
            if (previo.siguiente == null) {
                finalPreparacion = previo;
            }
        }

        cantPreparacion--;

        actual.estado = "ENTREGADO";
        actual.siguiente = null;

        if (frenteHistorial == null) {
            frenteHistorial = actual;
            finalHistorial = actual;
        } else {
            finalHistorial.siguiente = actual;
            finalHistorial = actual;
        }
        
        System.out.println("ENTREGAR " + numero + ": historial " + numero);
    }

    public void consultar(int numero) {
        NodoPedido[] frentes = {frentePendientes, frentePreparacion, frenteHistorial};
        
        for (NodoPedido f : frentes) {
            NodoPedido actual = f;
            while (actual != null) {
                if (actual.numeroPedido == numero) {
                    System.out.println("Pedido " + numero + " - Cliente: " + actual.cliente + " - Estado: " + actual.estado);
                    return;
                }
                actual = actual.siguiente;
            }
        }
        System.out.println("Pedido " + numero + " no encontrado.");
    }

    public void mostrarHistorial() {
        System.out.print("MOSTRAR_HISTORIAL: ");
        imprimirLista(frenteHistorial);
    }
    
    private void imprimirLista(NodoPedido frente) {
        NodoPedido actual = frente;
        while (actual != null) {
            System.out.print(actual.numeroPedido);
            if (actual.siguiente != null) {
                System.out.print(", ");
            }
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SistemaPedidos sp = new SistemaPedidos();
        
        sp.registrarPedido(101, "Cliente 1", 2);
        sp.registrarPedido(102, "Cliente 2", 1);
        sp.registrarPedido(103, "Cliente 3", 4);
        sp.registrarPedido(104, "Cliente 4", 2);
        sp.registrarPedido(105, "Cliente 5", 3);
        
        sp.prepararSiguiente();
        sp.prepararSiguiente();
        sp.prepararSiguiente();
        
        sp.prepararSiguiente();
        
        sp.entregar(102);
        
        sp.prepararSiguiente();
    }
}
class NodoPaquete {
    String codigo;
    String destino;
    int peso;
    int prioridad;
    int ordenLlegada;
    NodoPaquete siguiente;

    public NodoPaquete(String codigo, String destino, int peso, int prioridad, int ordenLlegada) {
        this.codigo = codigo;
        this.destino = destino;
        this.peso = peso;
        this.prioridad = prioridad;
        this.ordenLlegada = ordenLlegada;
        this.siguiente = null;
    }
}

public class CentroDistribucion {
    private NodoPaquete frente;
    private int contadorLlegada;

    public CentroDistribucion() {
        this.frente = null;
        this.contadorLlegada = 0;
    }

    private boolean existeCodigo(String codigo) {
        NodoPaquete actual = frente;
        while (actual != null) {
            if (actual.codigo.equals(codigo)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void registrar(String codigo, String destino, int peso, int prioridad) {
        if (prioridad < 1 || prioridad > 5) {
            System.out.println("Prioridad fuera de rango.");
            return;
        }
        if (existeCodigo(codigo)) {
            System.out.println("Codigo duplicado.");
            return;
        }

        contadorLlegada++;
        NodoPaquete nuevo = new NodoPaquete(codigo, destino, peso, prioridad, contadorLlegada);

        if (frente == null || nuevo.prioridad < frente.prioridad || (nuevo.prioridad == frente.prioridad && nuevo.ordenLlegada < frente.ordenLlegada)) {
            nuevo.siguiente = frente;
            frente = nuevo;
            return;
        }

        NodoPaquete actual = frente;
        while (actual.siguiente != null && 
              (actual.siguiente.prioridad < nuevo.prioridad || 
              (actual.siguiente.prioridad == nuevo.prioridad && actual.siguiente.ordenLlegada < nuevo.ordenLlegada))) {
            actual = actual.siguiente;
        }
        
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }

    public void despachar() {
        if (frente == null) {
            System.out.println("No hay paquetes.");
            return;
        }
        System.out.println("DESPACHAR: " + frente.codigo);
        frente = frente.siguiente;
    }

    public void mostrar() {
        if (frente == null) {
            System.out.println("No hay paquetes.");
            return;
        }
        System.out.print("MOSTRAR: ");
        NodoPaquete actual = frente;
        while (actual != null) {
            System.out.print(actual.codigo);
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CentroDistribucion cd = new CentroDistribucion();
        
        cd.registrar("P01", "Destino A", 10, 3);
        cd.registrar("P02", "Destino B", 5, 1);
        cd.registrar("P03", "Destino C", 15, 2);
        cd.registrar("P04", "Destino D", 8, 1);
        cd.registrar("P05", "Destino E", 12, 3);
        
        cd.mostrar();
        cd.despachar();
        cd.despachar();
        cd.mostrar();
    }
}
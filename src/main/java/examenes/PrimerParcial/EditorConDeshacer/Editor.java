class Operacion {
    int tipo;
    String texto;

    public Operacion(int tipo, String texto) {
        this.tipo = tipo;
        this.texto = texto;
    }
}

class Nodo {
    Operacion dato;
    Nodo siguiente;

    public Nodo(Operacion dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class Pila {
    private Nodo cima;

    public Pila() {
        this.cima = null;
    }

    public void push(Operacion dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public Operacion pop() {
        if (isEmpty()) return null;
        Operacion dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    public boolean isEmpty() {
        return cima == null;
    }
}

public class Editor {
    private String textoActual;
    private Pila historial;

    public Editor() {
        this.textoActual = "";
        this.historial = new Pila();
    }

    public void agregar(String texto) {
        if (texto == null || texto.isEmpty()) return;
        textoActual += texto;
        historial.push(new Operacion(1, texto));
        System.out.println("AGREGAR \"" + texto + "\" -> \"" + textoActual + "\"");
    }

    public void eliminar(int cantidad) {
        if (cantidad <= 0 || textoActual.isEmpty()) return;

        if (cantidad > textoActual.length()) {
            cantidad = textoActual.length();
        }

        int indiceCorte = textoActual.length() - cantidad;
        String textoEliminado = textoActual.substring(indiceCorte);
        textoActual = textoActual.substring(0, indiceCorte);

        historial.push(new Operacion(2, textoEliminado));
        System.out.println("ELIMINAR " + cantidad + " -> \"" + textoActual + "\"");
    }

    public void deshacer() {
        if (historial.isEmpty()) {
            System.out.println("DESHACER -> No existen operaciones para deshacer");
            return;
        }

        Operacion ultimaOp = historial.pop();

        if (ultimaOp.tipo == 1) {
            int indiceCorte = textoActual.length() - ultimaOp.texto.length();
            textoActual = textoActual.substring(0, indiceCorte);
        } else if (ultimaOp.tipo == 2) {
            textoActual += ultimaOp.texto;
        }

        System.out.println("DESHACER -> \"" + textoActual + "\"");
    }

    public void mostrar() {
        System.out.println("Texto actual: \"" + textoActual + "\"");
    }

    public static void main(String[] args) {
        Editor editor1 = new Editor();

        System.out.println("Texto inicial: \"\"");
        editor1.agregar("Hola");
        editor1.agregar(" mundo");
        editor1.eliminar(6);
        editor1.deshacer();
        editor1.deshacer();
        editor1.deshacer();

        System.out.println("\nOtro ejemplo:");
        Editor editor2 = new Editor();
        editor2.agregar("Estructuras");
        editor2.agregar(" de Datos");
        editor2.eliminar(5);
        editor2.deshacer();
    }
}
// Molde para guardar qué acción hicimos y con qué texto
class Operacion {
    int tipo; // 1 = Agregar texto, 2 = Eliminar texto
    String texto; // El pedacito de texto exacto que agregamos o borramos

    public Operacion(int tipo, String texto) {
        this.tipo = tipo;
        this.texto = texto;
    }
}

// Nodo adaptado para guardar un objeto Operacion
class Nodo {
    Operacion dato; // La información de la caja
    Nodo siguiente; // La cuerda hacia el nodo de abajo

    public Nodo(Operacion dato) {
        this.dato = dato;
        this.siguiente = null; // Al nacer, no cuelga de nadie
    }
}

// Estructura LIFO (Último en entrar, Primero en salir)
class Pila {
    private Nodo cima; // El único puntero que importa, vigila la caja de hasta arriba

    public Pila() {
        this.cima = null; // Inicia vacía
    }

    // PUSH: Mete una operación nueva al historial
    public void push(Operacion dato) {
        Nodo nuevo = new Nodo(dato); // Crea la caja
        nuevo.siguiente = cima; // Conecta la nueva caja a la que estaba antes en la cima
        cima = nuevo; // Corona a la nueva caja como la cima oficial
    }

    // POP: Saca y devuelve la última operación que hicimos
    public Operacion pop() {
        if (isEmpty()) return null; // Si no hay historial, no hace nada
        Operacion dato = cima.dato; // Respalda la info de la caja de arriba
        cima = cima.siguiente; // Baja la cima a la caja de abajo (la de arriba se borra)
        return dato; // Te devuelve la info respaldada
    }

    // Checa si la pila está vacía
    public boolean isEmpty() {
        return cima == null;
    }
}

// Clase principal del ejercicio
public class Editor {
    private String textoActual; // Donde vive el texto que ve el usuario
    private Pila historial; // Nuestra pila para ir amontonando las acciones

    public Editor() {
        this.textoActual = ""; // Arranca en blanco
        this.historial = new Pila(); // Arranca la pila vacía
    }

    // Método para escribir
    public void agregar(String texto) {
        if (texto == null || texto.isEmpty()) return; // Ignora textos vacíos
        textoActual += texto; // Le pega el nuevo texto al que ya tenías
        historial.push(new Operacion(1, texto)); // Apila que hicimos un "Agregar" (tipo 1)
        System.out.println("AGREGAR \"" + texto + "\" -> \"" + textoActual + "\"");
    }

    // Método para borrar con Backspace/Delete
    public void eliminar(int cantidad) {
        if (cantidad <= 0 || textoActual.isEmpty()) return; // Valida que se pueda borrar

        // Si te piden borrar más de lo que hay, ajusta para borrar solo lo que existe
        if (cantidad > textoActual.length()) {
            cantidad = textoActual.length();
        }

        // Calcula dónde meter la tijera basándose en la longitud total menos lo que vas a borrar
        int indiceCorte = textoActual.length() - cantidad;
        // Respalda solo las letritas que se van a morir
        String textoEliminado = textoActual.substring(indiceCorte);
        // Sobreescribe el texto actual quedándose solo con la parte que sobrevive al corte
        textoActual = textoActual.substring(0, indiceCorte);

        historial.push(new Operacion(2, textoEliminado)); // Apila que hicimos un "Borrar" (tipo 2)
        System.out.println("ELIMINAR " + cantidad + " -> \"" + textoActual + "\"");
    }

    // Método de CTRL+Z
    public void deshacer() {
        if (historial.isEmpty()) { // Si no hay nada en la pila
            System.out.println("DESHACER -> No existen operaciones para deshacer");
            return;
        }

        Operacion ultimaOp = historial.pop(); // Saca la última acción que hicimos

        if (ultimaOp.tipo == 1) {
            // Si la última acción fue "Agregar", lo contrario es "Borrar" eso que agregamos
            int indiceCorte = textoActual.length() - ultimaOp.texto.length();
            textoActual = textoActual.substring(0, indiceCorte); // Le cortamos el pedazo
        } else if (ultimaOp.tipo == 2) {
            // Si la última acción fue "Borrar", lo contrario es "Pegarlo de vuelta"
            textoActual += ultimaOp.texto;
        }

        System.out.println("DESHACER -> \"" + textoActual + "\"");
    }

    public static void main(String[] args) {
        // Datos quemados para que el profe corra el test directo
        Editor editor1 = new Editor();
        System.out.println("Texto inicial: \"\"");
        editor1.agregar("Hola");
        editor1.agregar(" mundo");
        editor1.eliminar(6);
        editor1.deshacer();
        editor1.deshacer();
        editor1.deshacer();
    }
}
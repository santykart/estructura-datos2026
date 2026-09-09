public class Pila<T> {
    private Nodo<T> cima;

    public Pila() {
        this.cima = null;
    }

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public T pop() {
        if (isEmpty()) return null;
        T dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    public T peek() {
        if (isEmpty()) return null;
        return cima.dato;
    }

    public boolean isEmpty() {
        return cima == null;
    }
}

package stack;

public class ArrayStack {
    // ATRIBUTOS
    private int[] dato;
    private int pos = -1;

    // CONSTRUCTORES
    // Definir el tamaño del 'ArrayStack'
    public ArrayStack(int size){
        dato = new int[size];
    }

    // Meter dato a la pila
    public void push(int dato){
        this.dato[++pos] = dato;
    }

    // Sacar dato el dato de la pila
    public int pop(){
        return dato[pos--];
    }

    // Mostrar el dato actual del apuntador
    public int peek(){
        return dato[pos];
    }

    // Determinar si la pila está vacía o no
    public boolean isEmpty(){
        return pos == -1;
    }

    // Determinar si la pila está llena o no
    public boolean isFull(){
        return pos == dato.length - 1;
    }

    // Determinar el tamaño
    public int size(){
        return pos;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for(int i=0; i<=pos; i++){
            builder.append(dato[i]).append(" ");
        }
        builder.append("]");

        return builder.toString();

    }
}

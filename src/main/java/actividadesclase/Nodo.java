package actividadesclase;

public class Nodo {
    // ATRIBUTOS
    private int dato;
    private Nodo siguiente;

    // CONSTRUCTOR
    public Nodo(int dato){
        this.dato = dato;
        this.siguiente = null;
    }

    // 
    public int getNodo(){
        return dato;
    }

    public Nodo getSiguiente(){
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente){
        this.siguiente = siguiente;
    }
}

package nodo;

public class Nodo {
    // ATRIBUTOS
    private int dato;
    private Nodo enlace;

    // CONSTRUCTOR
    public Nodo(int dato){
        this.dato = dato;
    }

    // MODIFICADOR (no sé si se llama así)
    public int getDato(){
        return dato;
    }

    public void setDato(int dato){
        this.dato = dato;
    }

    public Nodo getEnalce(){
        return enlace;
    }

    public void setEnlace(Nodo enlace){
        this.enlace = enlace;
    }

    public String toString(){
        return dato + " - " + enlace.toString();
    }
}
package main.java;

public class Main {
    public static void main(String[] args){
        Nodo nodoUno = new Nodo(10);
        Nodo nodoDos = new Nodo(20);
        Nodo nodoTres = new Nodo(30);

        System.out.println(".getNodo():");
        System.out.println(nodoUno.getNodo());
        System.out.println(nodoDos.getNodo());
        System.out.println(nodoTres.getNodo());

        System.out.println(".getSiguiente():");
        System.out.println(nodoUno.getSiguiente());
        System.out.println(nodoDos.getSiguiente());
        System.out.println(nodoTres.getSiguiente());
        
        System.out.println(".setSiguiente():");
        nodoUno.setSiguiente(nodoDos);
        nodoDos.setSiguiente(nodoTres);
        
        System.out.println(".getSiguiente():");
        System.out.println(nodoUno.getSiguiente());
        System.out.println(nodoDos.getSiguiente());
        System.out.println(nodoTres.getSiguiente());
    }
}

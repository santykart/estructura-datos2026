package actividadesclase;

import nodo.Nodo;
import stack.ArrayStack;

public class Main {
    public static void main(String[] args){
        // Declarar e inicializar variables
        Nodo nodo = new Nodo(10);
        Nodo nodo2 = new Nodo(20);
        Nodo nodo3 = new Nodo(30);

        // Enlazar nodos (setEnlace)
        nodo.setEnlace(nodo2);
        nodo2.setEnlace(nodo3);

        // Apuntador
        Nodo actual = nodo;

        // 
        while(actual.getEnalce() != null){
            System.out.println(actual.getDato());
            actual = actual.getEnalce();
        }

        // CLASE ARRAYSATCK
        IO.println("\n<< ARRAYSTACK >>");
        ArrayStack arrayStack = new ArrayStack(10);

        arrayStack.push(10);
        IO.println(arrayStack.toString());
        arrayStack.push(20);
        IO.println(arrayStack.toString());
        arrayStack.push(30);
        IO.println(arrayStack.toString() + "\n");

        IO.println("cima " + arrayStack.peek());
        IO.println("pop " + arrayStack.pop());
        IO.println("cima " + arrayStack.peek());
        IO.println(arrayStack.toString());

    }
}

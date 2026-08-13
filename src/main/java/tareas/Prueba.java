package main.java.tareas;

import java.util.Scanner;

public class Prueba {
    public static Scanner sc = new Scanner(System.in);  // Declarar 'Scanner sc' de forma global

    /* ==============================================
        MAIN DEL PROGRAMA
    ============================================== */

    public static void main(String[] args){
        // Declarar variables
        int[] prueba;
        int numero;
        int dato;
        int temp;

        // Registro de información con números enteros (únicamente int)
        IO.print(">> PRUEBA DE CÓDIGO: ORDENAMIENTO BURBUJA\nIngresa un número entero para comenzar a registrar la información: ");
        numero = sc.nextInt(); sc.nextLine();

        // Verificar que el número ingresado sea entero positivo mayor a 0
        while(numero <= 0){
            System.err.println("\nERROR... EL NÚMERO INGRESADO DEBE SER MAYOR QUE CERO... POR FAVOR INGRESA UN NUEVO NÚMERO PARA REGISTRAR LA INFORMACIÓN...");
            IO.print("Número: ");
            numero = sc.nextInt();sc.nextLine();
        }
        prueba = new int[numero];

        // Ciclo 'for' para iniciar el registro de los datos
        IO.println();
        for(int i=0; i<numero; i++){
            IO.print("Ingresa el número " + (i+1) + " (cualquier número entero): ");
            dato = sc.nextInt(); sc.nextLine();
            prueba[i] = dato;
        }

        // Mostrar el orden del arreglo antes del 'Ordenamiento Burbuja' con el Método 'Imprimir'
        IO.println("\n- - ORDEN ALEATORIO - -");
        imprimir(prueba, numero);

        // Comenzar con el 'Ordenamiento Burbuja' una vez que se tenga el arreglo listo
        for(int i=0; i<numero; i++){
            for(int j=0; j<numero; j++){
                if((prueba[i]) > (prueba[j])){
                    temp = prueba[j];
                    prueba[j] = prueba[i];
                    prueba[i] = temp;
                }
            }
        }

        // Mostrar el ordel del arreglo después del 'Ordenamiento Burbuja' con el método 'Imprimir'
        IO.println("\n- - ORDENAMIENTO BURBUJA - -");
        imprimir(prueba, numero);
    }

    /* ==============================================
        MÉTODO(S) DEL PROGRAMA
    ============================================== */

    // Método 1. 'Imprimir' >> imprime el valor de cada índice del arreglo
    public static void imprimir(int[] prueba, int numero){
        for(int i=0; i<numero; i++){
            IO.println("Dato " + (i+1) + ": " + prueba[i]);
        }
    }
}

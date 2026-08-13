package tareas;

import java.util.Scanner;

public class BubbleSort {
    public static Scanner sc = new Scanner(System.in);    // Declarar 'Scanner sc' de forma global

    /* =========================================
    MAIN DEL PROGRAMA
    ========================================= */
    public static void main(String[] args){
        // Declarar variables
        int cantidad = 10;
        int[] ventas = new int[cantidad];

        IO.println("\n<< Ordenamiento Burbuja (Bubble Sort) >>");
        ventas = capturarVentas(ventas, cantidad); // 1. Solicitar datos al usuario

        IO.println("\n<< VALORES INGRESADOS >>");
        mostrarArreglo(ventas); // 2. Mostrar los datos que fueron ingresados por el usuario

        ordenarBurbuja(ventas, cantidad); // 3. Realizar el ordenado de los datos de menor a mayor
        mostrarArreglo(ventas);

        promedio(ventas); // 4. Mostrar el promedio de ventas
    }
    
    /* =========================================
    MÉTODOS DEL PROGRAMA
    ========================================= */

    // Método 1. Capturar ventas >> permite al usuario ingresar los valores de las 10 ventas realizadas
    public static int[] capturarVentas(int[] ventas, int numero){
        ventas = new int[numero];

        IO.println("\nPor favor ingresa las 10 ventas realizadas por los 10 trabajadores durante la jornada laboral:");

        // Utilizar ciclo 'for' para el ingreso de datos
        for(int i=0; i<ventas.length; i++){
            IO.print("Ingresa el total de ventas del trabajador " + (i+1) + ": ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            // Utilizar el Método 5 'Comprobar Datos' y asignarle ese valor al índice 'i' del Array 'ventas'
            ventas[i] = comprobarDatos(cantidad, i);
        }

        return ventas;
    }

    // Método 2. Mostrar arreglo >> permite al usuario visualizar los valores de venta que ingresó en el 'Método 1'
    public static void mostrarArreglo(int[] ventas){
        for(int i=0; i<ventas.length; i++){
            IO.println("Ventas del trabajador " + (i+1) + " = " + ventas[i]);
        }
    }

    // Método 3. Ordenar Burbuja >> permite al usuario reordenar los valores de menor a mayor
    public static void ordenarBurbuja(int[] ventas, int cantidad){
        int temp;

        IO.println("\n<< ORDENAMIENTO BURBUJA >>");
        for(int i=0; i<cantidad; i++){
            for(int j=0; j<cantidad; j++){
                if(ventas[i] > ventas[j]){
                    temp = ventas[j];
                    ventas[j] = ventas[i];
                    ventas[i] = temp;
                }
            }
        }
    }

    // Método 4. Calcular Promedio >> muestra al usuario el promedio de las ventas realizadas
    public static void promedio(int[] ventas){
        double suma = 0;
        double promedio = 0;

        IO.println("\n<< PROMEDIO DE VENTAS >>");
        for(int i=0; i<ventas.length; i++){
            suma += ventas[i];
        }

        // Calcular y mostrar el resultado
        promedio = (suma/ventas.length);
        IO.println("El promedio de ventas de los 10 trabajadores fue de " + promedio);
    }

    // Método 5. Comprobar Datos >> permite verificar que las ventas realizadas sean únicamente cantidades enteras (int)
    public static int comprobarDatos(int cantidad, int i){
        while(cantidad < 0){
            System.err.println("\nERROR... LAS VENTAS DEBEN SER MAYORES O IGUALES A CERO... POR FAVOR INGRESA OTRA CANTIDAD...");
            IO.print("Ingresa el total de ventas del trabajador " + (i+1) + ": ");
            cantidad = Integer.parseInt(sc.nextLine().trim());
        }
        return cantidad;
    }
}

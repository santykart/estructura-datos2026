package main.java.tareas;

import java.util.Scanner;

public class BubbleSort {
    public static Scanner sc = new Scanner(System.in);    // Declarar 'Scanner sc' de forma global

    /* =========================================
    MAIN DEL PROGRAMA
    ========================================= */
    public static void main(String[] args){
        // Declarar variables
        int[] ventas = new int[10];
        int[] ordenBurbuja = new int[ventas.length];

        IO.println("<< Ordenamiento Burbuja (Bubble Sort) >>");
        ventas = capturarVentas(ventas); // Solicitar datos al usuario
        mostrarArreglo(ventas); // Mostrar los datos que fueron ingresados por el usuario
        ordenBurbuja = ordenarBurbuja(ventas); // Realizar el ordenado de los datos de menor a mayor
        promedio(ventas); // Mostrar el promedio de ventas
    }
    
    /* =========================================
    MÉTODOS DEL PROGRAMA
    ========================================= */

    // Método 1. Capturar ventas >> permite al usuario ingresar los valores de las 10 ventas realizadas
    public static int[] capturarVentas(int[] ventas){
        ventas = new int[10];

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
        IO.println("\n<< VALORES INGRESADOS >>");
        for(int i=0; i<ventas.length; i++){
            IO.println("Ventas del trabajador " + (i+1) + " = " + ventas[i]);
        }
    }

    // Método 3. Ordenar Burbuja >> permite al usuario reordenar los valores de menor a mayor
    public static int[] ordenarBurbuja(int[] ventas){
        
    }

    // Método 4. Calcular Promedio >> muestra al usuario el promedio de las ventas realizadas
    public static void promedio(int[] ventas){
        int suma = 0;
        double promedio = 0;
        for(int i=0; i<ventas.length; i++){
            suma += ventas[i];
        }

        // Calcular y mostrar el resultado
        promedio = suma/ventas.length;
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

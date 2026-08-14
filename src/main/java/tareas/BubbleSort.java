package tareas;

import java.util.Scanner;

public class BubbleSort {
    public static Scanner sc = new Scanner(System.in);  //  Se declara de forma global Scanner 'sc'

    /* ======================================
    - - - - MAIN DEL PROGRAMA - - - -
    ====================================== */
    public static void main(String[] args){
        // DECLARAR VARIABLES
        int[] ventas;
        int[] vendedores;
        int numero;

        // SOLICITAR INGRESO DE DATOS AL USUARIO
        IO.print("\nIngresa el número de ventas que deseas registrar: ");
        numero = sc.nextInt(); sc.nextLine();
        ventas = new int[numero];
        vendedores = new int[numero];

        // INGRESAR LOS DATOS DE CADA VENTA
        ventas = capturarDatos(ventas, numero);
        vendedores = asignarVendedor(vendedores, numero);

        // IMPRIMIR DATOS INGRESADOS
        IO.println("\nDATOS INGRESADOS");
        imprimirDatos(ventas, vendedores, numero);

        // ORDENAMIENTO BURBUJA
        ventas = ordenamientoBurbuja(ventas, vendedores, numero);

        // IMPRIMIR DATOS ORDENADOS
        IO.println("\nDATOS ORDENADOS");
        imprimirDatos(ventas, vendedores, numero);

        // DATOS DE LAS VENTAS: PROMEDIO, VENTA ALTA, VENTA BAJA
        ventaBajaAlta(ventas, numero);
    }

    /* ======================================
    - - - - MÉTODOS DEL PROGRAMA - - - -
    ====================================== */

    // MÉTODO 1. Permite al usuario ingresar los datos de cada venta
    public static int[] capturarDatos(int[] ventas, int numero){
        ventas = new int[numero];
        int num;

        IO.println("\n - - - INGRESO DE DATOS - - -");
        
        for(int i=0; i<numero; i++){
            IO.print("Ingresa la venta " + (i+1) + " del vendedor " + (i+1) + ": ");
            num = sc.nextInt(); sc.nextLine();

            // VERIFICAR QUE LAS VENTAS SEAN MAYORES O IGUALES A CERO (ENTEROS POSITIVOS)
            ventas[i] = verificarDatos(num, i);
        }
        return ventas;
    }

    // MÉTODO 2. Se le asigna un valor a cada vendedor (tiene el mismo tamaño que 'ventas')
    public static int[] asignarVendedor(int[] vendedores, int numero){
        for(int i=0; i<numero; i++){
            vendedores[i] = i+1;
        }
        return vendedores;
    }

    // MÉTODO 3. Permite verificar que los datos de ventas sean números enteros positivos (incluyendo el cero)
    public static int verificarDatos(int num, int i){
        while(num < 0){
            System.err.println("\nERROR DE INGRESO...\nLAS VENTAS DEBEN SER MAYORES O IGUALES A CERO...\nPOR FAVOR INGRESA UNA NUEVA CANTIDAD...");
            IO.print("\nIngresa la venta " + (i+1) + " del vendedor " + (i+1) + ": ");
            num = sc.nextInt(); sc.nextLine();
        }
        return num;
    }

    // MÉTODO 4. Permite ordenar de menor a mayor los datos de las 'ventas'. De igual forma, ordena los trabajadores con su respectiva venta
    public static int[] ordenamientoBurbuja(int[] ventas, int[] vendedores, int numero){
        int temp;

        for(int i=0; i<numero; i++){
            for(int j=0; j<numero; j++){
                if(ventas[j] < ventas[i]){
                    temp = ventas[i];
                    ventas[i] = ventas[j];
                    ventas[j] = temp;

                    temp = vendedores[i];
                    vendedores[i] = vendedores[j];
                    vendedores[j] = temp;
                }
            }
        }
        return ventas;
    }

    //  MÉTODO 5. Imprime el trabajador con la cantidad de ventas realizadas
    public static void imprimirDatos(int[] ventas, int[] vendedores, int numero){
        for(int i=0; i<numero; i++){
            IO.println("Venta del trabajador " + vendedores[i] + ": " + ventas[i]);
        }
    }   

    //  MÉTODO 6. Imprime el promedio de ventas, la venta más alta y la venta más baja
    public static void ventaBajaAlta(int[] ventas, int numero){
        int suma=0;

        for(int i=0; i<numero; i++){
            suma += ventas[i];
        }
        double promedio = suma / numero;
        
        IO.println("\nDATOS DE LAS VENTAS");
        IO.println("Promedio de ventas: " + promedio);
        IO.println("Venta más alta: " + ventas[0]);
        IO.println("Venta más baja: " + ventas[numero-1]);
    }

}

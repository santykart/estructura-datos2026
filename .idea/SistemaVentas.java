import java.util.Scanner;

public class SistemaVentas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA DE VENTAS ===");

        int[] ventas = capturarVentas(scanner);

        System.out.println("Ventas originales:");
        mostrarArreglo(ventas);

        System.out.println("Ordenando...");
        ordenarBurbuja(ventas);

        System.out.println("Ventas ordenadas:");
        mostrarArreglo(ventas);

        int ventaMasBaja = ventas[0];
        int ventaMasAlta = ventas[9];
        double promedio = calcularPromedio(ventas);

        System.out.println("=== ESTADISTICAS ===");
        System.out.println("Venta mas baja: " + ventaMasBaja);
        System.out.println("Venta mas alta: " + ventaMasAlta);
        System.out.println("Promedio de ventas: " + promedio);

        scanner.close();
    }

    public static int[] capturarVentas(Scanner scanner) {
        int[] arregloVentas = new int[10];
        System.out.println("Por favor, ingrese las ventas de los 10 vendedores:");

        for (int i = 0; i < 10; i++) {
            System.out.print("Vendedor " + (i + 1) + ": ");
            arregloVentas[i] = scanner.nextInt();
        }

        return arregloVentas;
    }

    public static void mostrarArreglo(int[] arr) {
        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void ordenarBurbuja(int[] arr) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static double calcularPromedio(int[] arr) {
        double suma = 0;

        for (int i = 0; i < 10; i++) {
            suma = suma + arr[i];
        }

        return suma / 10;
    }
}

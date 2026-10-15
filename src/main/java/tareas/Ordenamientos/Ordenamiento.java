public class Ordenamiento {

    public static void insercion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void seleccion(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = particion(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int particion(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public static void imprimirArreglo(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] original = {64, 34, 25, 12, 22, 11, 90, 88, 76, 45, 1, 9, 100, 3, 50, 14, 2};
        
        System.out.print("Arreglo original: ");
        imprimirArreglo(original);
        System.out.println("--------------------------------------------------");

        int[] arrInsercion = original.clone();
        insercion(arrInsercion);
        System.out.println("Ordenamiento por Insercion:");
        imprimirArreglo(arrInsercion);
        System.out.println();

        int[] arrSeleccion = original.clone();
        seleccion(arrSeleccion);
        System.out.println("Ordenamiento por Seleccion:");
        imprimirArreglo(arrSeleccion);
        System.out.println();

        int[] arrQuick = original.clone();
        quickSort(arrQuick, 0, arrQuick.length - 1);
        System.out.println("Ordenamiento por QuickSort:");
        imprimirArreglo(arrQuick);
        System.out.println();

        int[] arrShell = original.clone();
        shellSort(arrShell);
        System.out.println("Ordenamiento por ShellSort:");
        imprimirArreglo(arrShell);
    }
}
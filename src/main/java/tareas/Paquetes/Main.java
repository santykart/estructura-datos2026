import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PilaPaquetes almacen = new PilaPaquetes();
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n========= ALMACÉN =========");
            System.out.println("1. Registrar paquete");
            System.out.println("2. Retirar paquete");
            System.out.println("3. Consultar siguiente paquete");
            System.out.println("4. Mostrar paquetes");
            System.out.println("5. Buscar paquete");
            System.out.println("6. Salir");
            System.out.println("Selecciona una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("-- REGISTRAR PAQUETE --");
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Peso: ");
                    double peso = scanner.nextDouble();
                    
                    Paquete nuevo = new Paquete(id, descripcion, peso);
                    almacen.push(nuevo);
                    break;
                    
                case 2:
                    System.out.println("-- RETIRAR PAQUETE --");
                    Paquete retirado = almacen.pop();
                    if (retirado != null) {
                        System.out.println("Paquete retirado exitosamente:");
                        retirado.mostrarInfo();
                    }
                    break;
                    
                case 3:
                    System.out.println("-- CONSULTAR SIGUIENTE --");
                    Paquete siguiente = almacen.peek();
                    if (siguiente != null) {
                        System.out.println("Siguiente paquete a retirar:");
                        siguiente.mostrarInfo();
                    }
                    break;
                    
                case 4:
                    almacen.mostrar();
                    break;
                    
                case 5:
                    System.out.println("-- BUSCAR PAQUETE --");
                    System.out.print("ID a buscar: ");
                    int idBuscado = scanner.nextInt();
                    almacen.buscarPaquete(idBuscado);
                    break;
                    
                case 6:
                    System.out.println("Saliendo del sistema");
                    break;
                    
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        
        scanner.close();
    }
}
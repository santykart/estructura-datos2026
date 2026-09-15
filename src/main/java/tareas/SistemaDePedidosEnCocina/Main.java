package tareas.SistemaDePedidosEnCocina;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ColaPedidos cocina = new ColaPedidos();
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n===== PEDIDOS DE COCINA =====");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Preparar siguiente pedido");
            System.out.println("3. Consultar siguiente pedido");
            System.out.println("4. Mostrar pedidos pendientes");
            System.out.println("5. Buscar pedido por número");
            System.out.println("6. Mostrar tiempo total pendiente");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.nextLine();
                continue;
            }

            System.out.println("--------------------------------");

            switch (opcion) {
                case 1:
                    System.out.print("Número de pedido: ");
                    int num = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nombre del cliente: ");
                    String cliente = scanner.nextLine();

                    System.out.print("Platillo: ");
                    String platillo = scanner.nextLine();

                    System.out.print("Cantidad: ");
                    int cant = scanner.nextInt();

                    System.out.print("Tiempo estimado (minutos): ");
                    int tiempo = scanner.nextInt();

                    cocina.enqueue(new Pedido(num, cliente, platillo, cant, tiempo));
                    System.out.println("¡Pedido " + num + " registrado con éxito!");
                    break;

                case 2:
                    Pedido despachado = cocina.dequeue();
                    if (despachado != null) {
                        System.out.println("Preparando pedido " + despachado.getNumero());
                        System.out.println("Cliente: " + despachado.toString().split("\\|")[1].trim());
                        System.out.println("Platillo: " + despachado.toString().split("\\|")[2].trim());
                    } else {
                        System.out.println("La cocina está libre, no hay pedidos por preparar.");
                    }
                    break;

                case 3:
                    Pedido siguiente = cocina.peek();
                    if (siguiente != null) {
                        System.out.println("El siguiente en la fila es:\n" + siguiente.toString());
                    } else {
                        System.out.println("No hay pedidos pendientes en la fila.");
                    }
                    break;

                case 4:
                    System.out.println("Fila actual de pedidos:");
                    cocina.mostrar();
                    break;

                case 5:
                    System.out.print("Ingresa el número de pedido a buscar: ");
                    int buscarNum = scanner.nextInt();
                    cocina.buscar(buscarNum);
                    break;

                case 6:
                    System.out.println("Tiempo total pendiente en cocina: " + cocina.tiempoTotal() + " minutos.");
                    break;

                case 7:
                    System.out.println("Cerrando sistema de cocina.");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        scanner.close();
    }
}
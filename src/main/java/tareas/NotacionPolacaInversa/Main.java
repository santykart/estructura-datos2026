import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== CALCULADORA RPN =====");

        while (true) {
            System.out.println("\nIngresa una expresión (o presiona Enter para salir):");
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("Saliendo de la calculadora...");
                break;
            }

            try {
                System.out.println("\nExpresión infija:");
                System.out.println(entrada);

                String postfija = ConversorRPN.infijaAPostfija(entrada);
                System.out.println("\nExpresión postfija:");
                System.out.println(postfija);

                double resultado = ConversorRPN.evaluarPostfija(postfija);

                if (resultado == (long) resultado) {
                    System.out.println("\nResultado:\n" + (long) resultado);
                } else {
                    System.out.println("\nResultado:\n" + resultado);
                }

            } catch (Exception e) {
                System.out.println("\n¡Error en la sintaxis!: " + e.getMessage());
            }
            System.out.println("-------------------------------------");
        }

        scanner.close();
    }
}

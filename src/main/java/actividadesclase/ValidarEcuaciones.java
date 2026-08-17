package actividadesclase;

import stack.ArrayStack;
import java.util.Scanner;

public class ValidarEcuaciones {
    public void main(String[] args){
        // Declarar variables
        String ecuacion;
        Scanner sc = new Scanner(System.in);
        ArrayStack validar;

        IO.print("\nIngresa una ecuación lineal para validar si los paréntesis son correctos: ");
        ecuacion = sc.nextLine().trim();

        validar = new ArrayStack(ecuacion.length());

        // Método de validación
        for(int i=0; i<ecuacion.length(); i++){
            char c = ecuacion.charAt(i); // Recorrer la cadena carácter por carácter

            // Validar que la ecuación utilice de forma correcta los paréntesis
            if(c == '('){
                validar.push(c);
            } else if (c == ')' && validar.isEmpty()){
                System.err.println("\n[!] ERROR EN LA ECUACIÓN");
            }
        }


    }
}

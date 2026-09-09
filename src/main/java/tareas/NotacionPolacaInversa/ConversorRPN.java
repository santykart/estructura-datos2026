public class ConversorRPN {

    private static int precedencia(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return -1;
    }

    private static boolean esNumero(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String infijaAPostfija(String expresion) {
        Pila<Character> pila = new Pila<>();
        StringBuilder postfija = new StringBuilder();
        boolean ultimoFueNumero = false;
        int balanceParentesis = 0;

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (c == ' ') continue;

            if (Character.isDigit(c)) {
                postfija.append(c);
                ultimoFueNumero = true;
            } else {
                if (ultimoFueNumero) {
                    postfija.append(" ");
                    ultimoFueNumero = false;
                }

                if (c == '(') {
                    pila.push(c);
                    balanceParentesis++;
                } else if (c == ')') {
                    balanceParentesis--;
                    if (balanceParentesis < 0) {
                        throw new IllegalArgumentException("Paréntesis incorrectos.");
                    }
                    while (!pila.isEmpty() && pila.peek() != '(') {
                        postfija.append(pila.pop()).append(" ");
                    }
                    pila.pop();
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    while (!pila.isEmpty() && precedencia(c) <= precedencia(pila.peek())) {
                        postfija.append(pila.pop()).append(" ");
                    }
                    pila.push(c);
                } else {
                    throw new IllegalArgumentException("Caracter no reconocido: " + c);
                }
            }
        }

        if (ultimoFueNumero) postfija.append(" ");
        if (balanceParentesis != 0) throw new IllegalArgumentException("Paréntesis incorrectos (sin cerrar).");

        while (!pila.isEmpty()) {
            char tope = pila.pop();
            if (tope == '(') throw new IllegalArgumentException("Paréntesis incorrectos.");
            postfija.append(tope).append(" ");
        }

        return postfija.toString().trim();
    }

    public static double evaluarPostfija(String postfija) {
        if (postfija == null || postfija.isEmpty()) {
            throw new IllegalArgumentException("Expresión incompleta.");
        }

        Pila<Double> pila = new Pila<>();
        String[] tokens = postfija.split(" ");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (esNumero(token)) {
                pila.push(Double.parseDouble(token));
            } else {

                if (pila.isEmpty()) throw new IllegalArgumentException("Operadores sin suficientes operandos.");
                double b = pila.pop();

                if (pila.isEmpty()) throw new IllegalArgumentException("Operadores sin suficientes operandos.");
                double a = pila.pop();

                switch (token) {
                    case "+": pila.push(a + b); break;
                    case "-": pila.push(a - b); break;
                    case "*": pila.push(a * b); break;
                    case "/":
                        if (b == 0) throw new ArithmeticException("División entre cero detectada.");
                        pila.push(a / b);
                        break;
                    default: throw new IllegalArgumentException("Operador desconocido: " + token);
                }
            }
        }

        double resultado = pila.pop();
        if (!pila.isEmpty()) {
            throw new IllegalArgumentException("Expresión incompleta o mal formada.");
        }

        return resultado;
    }
}

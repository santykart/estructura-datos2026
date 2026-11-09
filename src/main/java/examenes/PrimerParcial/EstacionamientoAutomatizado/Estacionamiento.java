// Molde básico para guardar los datos del auto
class Automovil {
    String placa;
    String propietario;
    String horaEntrada;

    public Automovil(String placa, String propietario, String horaEntrada) {
        this.placa = placa;
        this.propietario = propietario;
        this.horaEntrada = horaEntrada;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + " | Propietario: " + propietario + " | Hora: " + horaEntrada;
    }
}

public class Estacionamiento {
    private Automovil[] espacios; // Memoria física limitada (Arreglo)
    private int frente; // Índice (0 a 5) donde está el primer auto
    private int finalCola; // Índice (0 a 5) donde está el último auto
    private int tamano; // Cuántos autos hay adentro actualmente
    private final int CAPACIDAD = 6; // Tamaño máximo que pidió el profe

    public Estacionamiento() {
        this.espacios = new Automovil[CAPACIDAD]; // Crea el arreglo de 6 huecos
        this.frente = 0; // El frente empieza en la posición 0
        this.finalCola = CAPACIDAD - 1; // Truco matemático: Empieza en 5 para que al sumarle 1 en el primer ingreso dé 0.
        this.tamano = 0; // Arranca vacío
    }

    // ENQUEUE CIRCULAR: Mete un auto reciclando memoria
    public void ingresar(Automovil auto) {
        if (tamano == CAPACIDAD) { // Si ya hay 6
            System.out.println("Estacionamiento lleno.");
            return;
        }
        // FÓRMULA MÁGICA: Avanza el final 1 paso. Si estaba en 5, (5+1)%6 = 0 (da la vuelta al inicio).
        finalCola = (finalCola + 1) % CAPACIDAD;
        espacios[finalCola] = auto; // Lo estaciona en el índice que calculó la fórmula
        tamano++; // Sube el contador
        System.out.println("Ingreso: " + auto.placa);
    }

    // DEQUEUE NORMAL: Saca al primer auto (al más viejo)
    public void retirar() {
        if (tamano == 0) {
            System.out.println("Estacionamiento vacio.");
            return;
        }
        System.out.println("Retirando: " + espacios[frente].placa);
        // Avanza el frente 1 paso de forma circular. El lugar viejo queda listo para reciclarse.
        frente = (frente + 1) % CAPACIDAD;
        tamano--;
    }

    // DEQUEUE COMPLEJO: Saca un auto del medio sin romper la fila
    public void retirar(String placa) {
        if (tamano == 0) return; // Si está vacío corta todo

        int targetI = -1; // En qué turno lógico (0 al tamano) está el auto
        int indexToRemove = -1; // En qué posición física (0 al 5) está el auto

        // Busca el auto recorriendo lógicamente desde el frente
        for (int i = 0; i < tamano; i++) {
            int actual = (frente + i) % CAPACIDAD; // Convierte turno a posición física
            if (espacios[actual].placa.equals(placa)) {
                targetI = i; // Lo encontré en el turno 'i'
                indexToRemove = actual; // Está estacionado físicamente en 'actual'
                break;
            }
        }

        if (indexToRemove == -1) { // No lo encontró en el ciclo for
            System.out.println("Placa inexistente: " + placa);
            return;
        }

        System.out.println("Retirando placa especifica: " + espacios[indexToRemove].placa);

        // Si casualmente el auto era el primero en la fila, solo avanzamos el frente
        if (targetI == 0) {
            frente = (frente + 1) % CAPACIDAD;
        } else {
            // Si estaba en medio, hay que mover TODOS los de atrás un lugar hacia adelante
            for (int i = targetI; i < tamano - 1; i++) {
                int curr = (frente + i) % CAPACIDAD; // El lugar vacío
                int next = (frente + i + 1) % CAPACIDAD; // El lugar del que sigue
                espacios[curr] = espacios[next]; // Copia el de atrás hacia adelante tapando el hueco
            }
            // Retrocede el puntero final físicamente porque la fila se encogió.
            // Se suma CAPACIDAD para evitar que dé números negativos antes del módulo.
            finalCola = (finalCola - 1 + CAPACIDAD) % CAPACIDAD;
        }
        tamano--; // Baja el contador
    }

    // Muestra todos recorriendo en orden circular
    public void mostrarEstacionamiento() {
        if (tamano == 0) return;
        System.out.println("--- ESTADO DEL ESTACIONAMIENTO ---");
        for (int i = 0; i < tamano; i++) {
            // Recorre desde el 0 al número de autos actuales, calculando su posición real circular
            int actual = (frente + i) % CAPACIDAD;
            System.out.println((i + 1) + " -> " + espacios[actual].toString() + " (Posicion fisica: " + actual + ")");
        }
    }

    public static void main(String[] args) {
        Estacionamiento est = new Estacionamiento();
        est.ingresar(new Automovil("AAA111", "Juan", "08:00"));
        est.ingresar(new Automovil("BBB222", "Maria", "08:15"));
        est.ingresar(new Automovil("CCC333", "Pedro", "08:30"));
        est.ingresar(new Automovil("DDD444", "Luis", "08:45"));
        est.ingresar(new Automovil("EEE555", "Ana", "09:00"));

        est.mostrarEstacionamiento();
        est.retirar("CCC333");
        est.mostrarEstacionamiento();
    }
}
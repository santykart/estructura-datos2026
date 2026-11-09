// Molde para guardar la info del enfermo
class Paciente {
    String nombre;
    int edad;
    String motivo;
    String tipo; // "NORMAL" o "URGENTE"

    public Paciente(String nombre, int edad, String motivo, String tipo) {
        this.nombre = nombre;
        this.edad = edad;
        this.motivo = motivo;
        this.tipo = tipo;
    }
}

// Nodo adaptado para pacientes
class Nodo {
    Paciente dato;
    Nodo siguiente;

    public Nodo(Paciente dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

// Estructura FIFO (Primero en entrar, Primero en salir)
class Cola {
    Nodo frente; // El puntero del inicio de la fila (el que va a ser atendido)
    Nodo finalCola; // El puntero del fin de la fila (donde se forman los nuevos)
    int tamano; // Contador rápido de pacientes formados

    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamano = 0;
    }

    // ENQUEUE: Formarse en la fila
    public void enqueue(Paciente p) {
        Nodo nuevo = new Nodo(p); // Llega el paciente nuevo
        if (frente == null) {
            // Si la fila estaba vacía, él es el primero y el último
            frente = nuevo;
            finalCola = nuevo;
        } else {
            // Si ya hay fila, el último actual le agarra la mano al nuevo
            finalCola.siguiente = nuevo;
            // El nuevo se convierte oficialmente en el final de la cola
            finalCola = nuevo;
        }
        tamano++; // Sube el contador
    }

    // DEQUEUE: Atender al del inicio
    public Paciente dequeue() {
        if (frente == null) return null; // Si está vacía, no hace nada
        Paciente p = frente.dato; // Respalda al paciente de mero adelante
        frente = frente.siguiente; // Avanza el frente al paciente número 2 de la fila
        if (frente == null) finalCola = null; // Si el frente quedó nulo (ya no hay nadie), resetea el final también
        tamano--; // Baja el contador
        return p; // Devuelve al paciente despachado
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public int getTamano() {
        return tamano; // Regresa el contador para imprimirlo fácil
    }
}

// Controlador del hospital
public class Hospital {
    private Cola normales; // Fila exclusiva para normales
    private Cola urgentes; // Fila exclusiva para urgentes
    private int urgentesConsecutivos; // Contador para la regla del profe de "max 2 seguidos"

    public Hospital() {
        this.normales = new Cola();
        this.urgentes = new Cola();
        this.urgentesConsecutivos = 0;
    }

    // Recibe un paciente y lo manda a su fila
    public void registrar(String nombre, int edad, String motivo, String tipo) {
        if (nombre == null || nombre.isEmpty() || tipo == null || tipo.isEmpty()) return;

        Paciente p = new Paciente(nombre, edad, motivo, tipo.toUpperCase());
        if (p.tipo.equals("URGENTE")) {
            urgentes.enqueue(p); // Si es urgente se forma en la Cola urgente
        } else if (p.tipo.equals("NORMAL")) {
            normales.enqueue(p); // Si es normal se forma en la Cola normal
        }
    }

    // Saca al siguiente paciente aplicando la regla de prioridad
    public void atender() {
        if (normales.isEmpty() && urgentes.isEmpty()) { // Hospital vacío
            System.out.println("No hay pacientes por atender.\n");
            return;
        }

        Paciente atendido = null;

        // REGLA: Atiende urgente SI HAY urgentes Y (no hay normales formados O todavía no llevamos 2 urgentes al hilo)
        if (!urgentes.isEmpty() && (normales.isEmpty() || urgentesConsecutivos < 2)) {
            atendido = urgentes.dequeue(); // Saca de la fila urgente
            urgentesConsecutivos++; // Sube el contador de urgentes seguidos
        } else if (!normales.isEmpty()) {
            atendido = normales.dequeue(); // Saca de la fila normal
            urgentesConsecutivos = 0; // REINICIA el contador de urgentes a cero
        }

        if (atendido != null) {
            System.out.println("Paciente atendido: " + atendido.nombre);
            System.out.println("Normales pendientes: " + normales.getTamano());
            System.out.println("Urgentes pendientes: " + urgentes.getTamano() + "\n");
        }
    }

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        hospital.registrar("N1", 30, "Chequeo", "NORMAL");
        hospital.registrar("N2", 25, "Dolor leve", "NORMAL");
        hospital.registrar("N3", 40, "Gripe", "NORMAL");
        hospital.registrar("U1", 50, "Fractura", "URGENTE");
        hospital.registrar("U2", 60, "Infarto", "URGENTE");
        hospital.registrar("U3", 20, "Hemorragia", "URGENTE");
        hospital.registrar("U4", 35, "Quemadura", "URGENTE");

        for (int i = 0; i < 7; i++) hospital.atender(); // Llama a atender 7 veces
    }
}
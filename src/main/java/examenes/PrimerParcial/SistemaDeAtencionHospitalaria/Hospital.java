class Paciente {
    String nombre;
    int edad;
    String motivo;
    String tipo;

    public Paciente(String nombre, int edad, String motivo, String tipo) {
        this.nombre = nombre;
        this.edad = edad;
        this.motivo = motivo;
        this.tipo = tipo;
    }
}

class Nodo {
    Paciente dato;
    Nodo siguiente;

    public Nodo(Paciente dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class Cola {
    Nodo frente;
    Nodo finalCola;
    int tamano;

    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamano = 0;
    }

    public void enqueue(Paciente p) {
        Nodo nuevo = new Nodo(p);
        if (frente == null) {
            frente = nuevo;
            finalCola = nuevo;
        } else {
            finalCola.siguiente = nuevo;
            finalCola = nuevo;
        }
        tamano++;
    }

    public Paciente dequeue() {
        if (frente == null) return null;
        Paciente p = frente.dato;
        frente = frente.siguiente;
        if (frente == null) finalCola = null;
        tamano--;
        return p;
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public int getTamano() {
        return tamano;
    }
}

public class Hospital {
    private Cola normales;
    private Cola urgentes;
    private int urgentesConsecutivos;

    public Hospital() {
        this.normales = new Cola();
        this.urgentes = new Cola();
        this.urgentesConsecutivos = 0;
    }

    public void registrar(String nombre, int edad, String motivo, String tipo) {
        if (nombre == null || nombre.isEmpty() || tipo == null || tipo.isEmpty()) return;

        Paciente p = new Paciente(nombre, edad, motivo, tipo.toUpperCase());
        if (p.tipo.equals("URGENTE")) {
            urgentes.enqueue(p);
        } else if (p.tipo.equals("NORMAL")) {
            normales.enqueue(p);
        }
    }

    public void atender() {
        if (normales.isEmpty() && urgentes.isEmpty()) {
            System.out.println("No hay pacientes por atender.\n");
            return;
        }

        Paciente atendido = null;

        if (!urgentes.isEmpty() && (normales.isEmpty() || urgentesConsecutivos < 2)) {
            atendido = urgentes.dequeue();
            urgentesConsecutivos++;
        } else if (!normales.isEmpty()) {
            atendido = normales.dequeue();
            urgentesConsecutivos = 0;
        }

        if (atendido != null) {
            System.out.println("Paciente atendido: " + atendido.nombre);
            System.out.println("Normales pendientes: " + normales.getTamano());
            System.out.println("Urgentes pendientes: " + urgentes.getTamano());
            System.out.println();
        }
    }

    public void mostrarPendientes() {
        System.out.println("--- URGENTES PENDIENTES ---");
        Nodo actual = urgentes.frente;
        while (actual != null) {
            System.out.println(actual.dato.nombre + " - " + actual.dato.motivo);
            actual = actual.siguiente;
        }

        System.out.println("--- NORMALES PENDIENTES ---");
        actual = normales.frente;
        while (actual != null) {
            System.out.println(actual.dato.nombre + " - " + actual.dato.motivo);
            actual = actual.siguiente;
        }
        System.out.println();
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

        for (int i = 0; i < 7; i++) {
            hospital.atender();
        }

        hospital.atender();
    }
}
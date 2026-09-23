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
    private Automovil[] espacios;
    private int frente;
    private int finalCola;
    private int tamano;
    private final int CAPACIDAD = 6;

    public Estacionamiento() {
        this.espacios = new Automovil[CAPACIDAD];
        this.frente = 0;
        this.finalCola = CAPACIDAD - 1;
        this.tamano = 0;
    }

    public void ingresar(Automovil auto) {
        if (tamano == CAPACIDAD) {
            System.out.println("Estacionamiento lleno.");
            return;
        }
        finalCola = (finalCola + 1) % CAPACIDAD;
        espacios[finalCola] = auto;
        tamano++;
        System.out.println("Ingreso: " + auto.placa);
    }

    public void retirar() {
        if (tamano == 0) {
            System.out.println("Estacionamiento vacio.");
            return;
        }
        System.out.println("Retirando: " + espacios[frente].placa);
        frente = (frente + 1) % CAPACIDAD;
        tamano--;
    }

    public void retirar(String placa) {
        if (tamano == 0) {
            System.out.println("Estacionamiento vacio.");
            return;
        }

        int targetI = -1;
        int indexToRemove = -1;

        for (int i = 0; i < tamano; i++) {
            int actual = (frente + i) % CAPACIDAD;
            if (espacios[actual].placa.equals(placa)) {
                targetI = i;
                indexToRemove = actual;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("Placa inexistente: " + placa);
            return;
        }

        System.out.println("Retirando placa especifica: " + espacios[indexToRemove].placa);

        if (targetI == 0) {
            frente = (frente + 1) % CAPACIDAD;
        } else {
            for (int i = targetI; i < tamano - 1; i++) {
                int curr = (frente + i) % CAPACIDAD;
                int next = (frente + i + 1) % CAPACIDAD;
                espacios[curr] = espacios[next];
            }
            finalCola = (finalCola - 1 + CAPACIDAD) % CAPACIDAD;
        }
        tamano--;
    }

    public void mostrarSiguiente() {
        if (tamano == 0) {
            System.out.println("Estacionamiento vacio.");
            return;
        }
        System.out.println("Siguiente en salir: " + espacios[frente].placa);
    }

    public void mostrarEstacionamiento() {
        if (tamano == 0) {
            System.out.println("Estacionamiento vacio.");
            return;
        }
        System.out.println("--- ESTADO DEL ESTACIONAMIENTO ---");
        for (int i = 0; i < tamano; i++) {
            int actual = (frente + i) % CAPACIDAD;
            System.out.println((i + 1) + " -> " + espacios[actual].toString() + " (Posicion fisica: " + actual + ")");
        }
        System.out.println("----------------------------------");
    }

    public void buscar(String placa) {
        for (int i = 0; i < tamano; i++) {
            int actual = (frente + i) % CAPACIDAD;
            if (espacios[actual].placa.equals(placa)) {
                System.out.println("Auto encontrado: " + espacios[actual].toString() + " en posicion " + (i + 1));
                return;
            }
        }
        System.out.println("Placa inexistente: " + placa);
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

        est.ingresar(new Automovil("FFF666", "Carlos", "09:15"));
        est.ingresar(new Automovil("GGG777", "Sofia", "09:30"));

        est.mostrarEstacionamiento();

        est.ingresar(new Automovil("HHH888", "Error", "10:00"));

        est.retirar();
        est.mostrarSiguiente();

        est.buscar("DDD444");
        est.buscar("ZZZ999");

        est.retirar("ZZZ999");
    }
}
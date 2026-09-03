public class Paquete {
    int id;
    String descripcion;
    double peso;

    public Paquete(int id, String descripcion, double peso) {
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
    }

    public void mostrarInfo() {
        System.out.println("ID: " + id);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Peso: " + peso + " kg");
    }
}
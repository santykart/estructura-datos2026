package tareas.SistemaDePedidosEnCocina;

public class Pedido {
    private int numero;
    private String cliente;
    private String platillo;
    private int cantidad;
    private int tiempoEstimado;

    public Pedido(int numero, String cliente, String platillo, int cantidad, int tiempoEstimado) {
        this.numero = numero;
        this.cliente = cliente;
        this.platillo = platillo;
        this.cantidad = cantidad;
        this.tiempoEstimado = tiempoEstimado;
    }

    public int getNumero() { return numero; }
    public int getTiempoEstimado() { return tiempoEstimado; }

    @Override
    public String toString() {
        return numero + " | " + cliente + " | " + platillo + " (x" + cantidad + ") | " + tiempoEstimado + " min";
    }
}
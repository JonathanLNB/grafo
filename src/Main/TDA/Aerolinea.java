package Main.TDA;

public class Aerolinea {
    private int llave;
    private String nombre;
    private boolean estado;

    public Aerolinea(int llave, String nombre, boolean estado) {
        this.llave = llave;
        this.nombre = nombre;
        this.estado = estado;
    }

    public int getLlave() {
        return llave;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEstado() {
        return estado;
    }
}

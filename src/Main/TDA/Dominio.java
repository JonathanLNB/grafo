package Main.TDA;

public class Dominio {
    private String llave;
    private String elementos [];

    public Dominio(String llave, String[] elementos) {
        this.llave = llave;
        this.elementos = elementos;
    }

    public String getLlave() {
        return llave;
    }

    public String[] getElementos() {
        return elementos;
    }
}

package Main.TDA;

public class Premisa {
    private int llave;
    private String regla;

    public Premisa(int llave, String regla) {
        this.llave = llave;
        this.regla = regla;
    }

    public int getLlave() {
        return llave;
    }

    public String getRegla() {
        return regla;
    }
}
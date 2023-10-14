package anasintactico;

public class Bloques {

    private String btipo;
    private String bloque;
    private String berror;
    private int bfila;
    private int bcolumna;

    public Bloques(String btipo, String bloque, String berror, int bfila, int bcolumna) {
        this.btipo = btipo;
        this.bloque = bloque;
        this.berror = berror;
        this.bfila = bfila;
        this.bcolumna = bcolumna;
    }

    public String getBtipo() {
        return btipo;
    }

    public void setBtipo(String btipo) {
        this.btipo = btipo;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getBerror() {
        return berror;
    }

    public void setBerror(String berror) {
        this.berror = berror;
    }

    public int getBfila() {
        return bfila;
    }

    public void setBfila(int bfila) {
        this.bfila = bfila;
    }

    public int getBcolumna() {
        return bcolumna;
    }

    public void setBcolumna(int bcolumna) {
        this.bcolumna = bcolumna;
    }

    @Override
    public String toString() {
        return "Bloques{" + "btipo=" + btipo + ", bloque=" + bloque + ", berror=" + berror + ", bfila=" + bfila + ", bcolumna=" + bcolumna + '}';
    }

}

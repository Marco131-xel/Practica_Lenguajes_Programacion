package analisis;

public class Token {
    
    private String tipo;
    private String lexema;
    private String patron;
    private int fila;
    private int columna;

    public Token(String tipo, String lexema, String patron, int fila, int columna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.patron = patron;
        this.fila = fila;
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Token{" + "tipo=" + tipo + ", lexema=" + lexema + ", patron=" + patron + ", fila=" + fila + ", columna=" + columna + '}';
    }

    
}

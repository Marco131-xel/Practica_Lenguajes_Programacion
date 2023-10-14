package anasintactico;

public class Simbolos {

    private String respuesta;
    private String error;
    private String lexemas;
    private int filas;
    private int columnas;

    public Simbolos(String respuesta, String error, String lexemas, int filas, int columnas) {
        this.respuesta = respuesta;
        this.error = error;
        this.lexemas = lexemas;
        this.filas = filas;
        this.columnas = columnas;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getLexemas() {
        return lexemas;
    }

    public void setLexemas(String lexemas) {
        this.lexemas = lexemas;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    @Override
    public String toString() {
        return "Simbolos{" + "respuesta=" + respuesta + ", error=" + error +", lexemas=" + lexemas + 
                ", filas=" + filas + ", columnas=" + columnas + '}';
    }
    
    
}

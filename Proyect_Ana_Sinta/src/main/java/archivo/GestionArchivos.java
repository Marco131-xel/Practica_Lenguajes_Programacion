package archivo;

import java.io.*;
import javax.swing.table.DefaultTableModel;

public class GestionArchivos {

    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;

    public GestionArchivos() {
    }
// METODO PARA ABRIR LOS ARCHIVOS

    public String AbrirATexto(File archivo) {
        String contenido = "";
        try {
            entrada = new FileInputStream(archivo);
            int afk;
            while ((afk = entrada.read()) != -1) {
                char caracter = (char) afk;
                contenido += caracter;
            }
        } catch (Exception e) {

        }
        return contenido;
    }
}

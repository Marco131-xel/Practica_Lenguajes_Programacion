package archivo;

import java.io.*;
import javax.swing.table.DefaultTableModel;

public class GestionArchivos {
    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;

    public GestionArchivos(){
    }
// METODO PARA LOS ARCHIVOS
    public String AbrirATexto(File archivo){
        String contenido = "";
        try {
            entrada = new FileInputStream(archivo);
            int afk;
            while ((afk = entrada.read()) != -1){
                char caracter = (char)afk;
                contenido += caracter;
            }
        }catch (Exception e){

        }
        return contenido;
    }
// METODO PARA GUARDAR EL ARCHIVO (ES OPCIONAL)
    public String GuardarATexto(File archivo, String contenido){
        String respuesta = null;
        try {
            salida = new FileOutputStream(archivo);
            byte [] bytesTxt = contenido.getBytes();
            salida.write(bytesTxt);
            respuesta = "Se guardo con exito el archivo";
        }catch (Exception e){
        }
        return respuesta;
    }
}

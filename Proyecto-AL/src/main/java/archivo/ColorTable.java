package archivo;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ColorTable {

    java.awt.Color black = java.awt.Color.black;
    java.awt.Color cyan = java.awt.Color.cyan;
    java.awt.Color magenta = java.awt.Color.magenta;
    java.awt.Color red = java.awt.Color.red;
    java.awt.Color gray = java.awt.Color.gray;
    java.awt.Color green = java.awt.Color.green;
    java.awt.Color yellow = java.awt.Color.yellow;

    private List<String> palabrasclave = Arrays.asList("as", "assert", "break", "class", "continue", "def", "del", "elif",
            "else", "except", "False", "finally", "for", "from", "global", "if", "import", "in", "is", "lambda", "None",
            "nonlocal", "pass", "raise", "return", "True", "try", "while", "with", "yield");

    private List<String> logicos = Arrays.asList("and", "or", "not");
    private List<String> punto = Arrays.asList(".");
    private List<String> barra = Arrays.asList("_");
    private List<String> otros = Arrays.asList("(", ")", "{", "}", "[", "]", ",", ":", ";");
    private List<String> aritmetico = Arrays.asList("+", "-", "*", "/", "**", "//", "%", "=", "==",
            "!=", "<", ">", "<=", ">=");

    public void colorearEditorTexto(JTextPane textPane) {
        StyledDocument doc = textPane.getStyledDocument();
        String contenido = null;
        try {
            // Obtiene el texto completo del JTextPane
            contenido = doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < contenido.length(); i++) {
            char c = contenido.charAt(i);
            Color color;

            if (Character.isLetter(c)) {
                color = black; // Letras en verde
            } else if (Character.isDigit(c)) {
                color = red;   // Números en rojo
            } else {
                color = yellow;  // los que no tienen sentido
            }

            StyleContext sc = StyleContext.getDefaultStyleContext();
            AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

            doc.setCharacterAttributes(i, 1, aset, true);
        }

        for (String palabra : palabrasclave) {
            int inicio = contenido.indexOf(palabra);
            while (inicio >= 0) {
                int fin = inicio + palabra.length();
                AttributeSet aset = new SimpleAttributeSet();
                StyleConstants.setForeground((MutableAttributeSet) aset, magenta); // Palabras clave en morado
                doc.setCharacterAttributes(inicio, fin - inicio, aset, false);
                inicio = contenido.indexOf(palabra, fin);
            }
        }
        for (String palabra : barra) {
            int inicio = contenido.indexOf(palabra);
            while (inicio >= 0) {
                int fin = inicio + palabra.length();
                AttributeSet aset = new SimpleAttributeSet();
                StyleConstants.setForeground((MutableAttributeSet) aset, black); // barra negro
                doc.setCharacterAttributes(inicio, fin - inicio, aset, false);
                inicio = contenido.indexOf(palabra, fin);
            }
        }

        for (String palabra : aritmetico) {
            int inicio = contenido.indexOf(palabra);
            while (inicio >= 0) {
                int fin = inicio + palabra.length();
                AttributeSet aset = new SimpleAttributeSet();
                StyleConstants.setForeground((MutableAttributeSet) aset, cyan); // aritmetico cyan
                doc.setCharacterAttributes(inicio, fin - inicio, aset, false);
                inicio = contenido.indexOf(palabra, fin);
            }
        }

        for (String palabra : logicos) {
            int inicio = contenido.indexOf(palabra);
            while (inicio >= 0) {
                int fin = inicio + palabra.length();
                AttributeSet aset = new SimpleAttributeSet();
                StyleConstants.setForeground((MutableAttributeSet) aset, cyan); // logicos cyan
                doc.setCharacterAttributes(inicio, fin - inicio, aset, false);
                inicio = contenido.indexOf(palabra, fin);
            }
        }
        for (String palabra : punto) {
            int inicio = contenido.indexOf(palabra);
            while (inicio >= 0) {
                int fin = inicio + palabra.length();
                AttributeSet aset = new SimpleAttributeSet();
                StyleConstants.setForeground((MutableAttributeSet) aset, red); // punto red
                doc.setCharacterAttributes(inicio, fin - inicio, aset, false);
                inicio = contenido.indexOf(palabra, fin);
            }
        }

        for (String palabra : otros) {
            int inicio = contenido.indexOf(palabra);
            while (inicio >= 0) {
                int fin = inicio + palabra.length();
                AttributeSet aset = new SimpleAttributeSet();
                StyleConstants.setForeground((MutableAttributeSet) aset, green); // otros green
                doc.setCharacterAttributes(inicio, fin - inicio, aset, false);
                inicio = contenido.indexOf(palabra, fin);
            }
        }

        // comentario
        String[] lineas = contenido.split("\n");
        for (String linea : lineas) {
            String trimmedLine = linea.trim();
            if (trimmedLine.startsWith("#") && trimmedLine.length() > 1) {
                int startIndex = contenido.indexOf(linea);
                int endIndex = startIndex + linea.length();
                boolean soloLetrasDespuesDelHashtag = true;

                for (int i = trimmedLine.indexOf("#") + 1; i < trimmedLine.length(); i++) {
                    if (!Character.isLetterOrDigit(trimmedLine.charAt(i)) && !Character.isWhitespace(trimmedLine.charAt(i))) {
                        soloLetrasDespuesDelHashtag = false;
                        break;
                    }
                }

                if (soloLetrasDespuesDelHashtag) {
                    AttributeSet aset = new SimpleAttributeSet();
                    StyleConstants.setForeground((MutableAttributeSet) aset, gray); // Establece el color gris
                    doc.setCharacterAttributes(startIndex, endIndex - startIndex, aset, false);
                }
            }
        }

        //cadenas comillas dobles
        int startIndex = contenido.indexOf("\"");
        while (startIndex >= 0) {
            int endIndex = contenido.indexOf("\"", startIndex + 1);
            if (endIndex > startIndex) {
                String cadena = contenido.substring(startIndex + 1, endIndex);

                // Eliminar espacios al principio y al final de la cadena
                cadena = cadena.trim();

                if (esCadenaValida(cadena)) {
                    AttributeSet aset = new SimpleAttributeSet();
                    StyleConstants.setForeground((MutableAttributeSet) aset, red); // Cadenas entre comillas en rojo
                    doc.setCharacterAttributes(startIndex, endIndex - startIndex + 1, aset, false);
                }
            }
            startIndex = contenido.indexOf("\"", endIndex + 1);
        }

        //cadenas comillas simples
        startIndex = contenido.indexOf("\'");
        while (startIndex >= 0) {
            int endIndex = contenido.indexOf("\'", startIndex + 1);
            if (endIndex > startIndex) {
                String cadena = contenido.substring(startIndex + 1, endIndex);

                // Eliminar espacios al principio y al final de la cadena
                cadena = cadena.trim();

                if (esCadenaValida(cadena)) {
                    AttributeSet aset = new SimpleAttributeSet();
                    StyleConstants.setForeground((MutableAttributeSet) aset, red); // Cadenas entre comillas en rojo
                    doc.setCharacterAttributes(startIndex, endIndex - startIndex + 1, aset, false);
                }
            }
            startIndex = contenido.indexOf("\'", endIndex + 1);
        }

    }
    // Función para verificar si una cadena contiene solo letras y números

    private boolean esCadenaValida(String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

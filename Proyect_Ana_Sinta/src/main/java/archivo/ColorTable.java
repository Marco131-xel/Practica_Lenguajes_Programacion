package archivo;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

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
    private List<String> punto = Arrays.asList(".", "\"", "\'");
    private List<String> barra = Arrays.asList("_");
    private List<String> otros = Arrays.asList("(", ")", "{", "}", "[", "]", ",", ":", ";");
    private List<String> aritmetico = Arrays.asList("+", "-", "*", "/", "**", "//", "%", "=", "==",
            "!=", "<", ">", "<=", ">=");

    public void colorearEditorTexto(JTextPane textPane) {
        StyledDocument doc = textPane.getStyledDocument();
        String contenido = null;
        cambiarTamanioDeFuente(textPane, 15);

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
                color = black; // Letras en negro
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
            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(palabra) + "\\b");
            Matcher matcher = pattern.matcher(contenido);

            while (matcher.find()) {
                int inicio = matcher.start();
                int fin = matcher.end();

                AttributeSet aset = new SimpleAttributeSet();
                StyleConstants.setForeground((MutableAttributeSet) aset, magenta); // Palabras clave en morado
                doc.setCharacterAttributes(inicio, fin - inicio, aset, false);
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
            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(palabra) + "\\b");
            Matcher matcher = pattern.matcher(contenido);

            while (matcher.find()) {
                int inicio = matcher.start();
                int fin = matcher.end();

                AttributeSet aset = new SimpleAttributeSet();
                StyleConstants.setForeground((MutableAttributeSet) aset, cyan); // Logicos cyan 
                doc.setCharacterAttributes(inicio, fin - inicio, aset, false);
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
        // Comentario 
        Pattern pattern = Pattern.compile("#[^\n]*");
        Matcher matcher = pattern.matcher(contenido);
        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            AttributeSet aset = new SimpleAttributeSet();
            StyleConstants.setForeground((MutableAttributeSet) aset, gray); // Comentarios en gris
            doc.setCharacterAttributes(startIndex, endIndex - startIndex, aset, false);
        }

        // Comillas Dobles
        pattern = Pattern.compile("\"[^\"]*\"");
        matcher = pattern.matcher(contenido);

        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            AttributeSet aset = new SimpleAttributeSet();
            StyleConstants.setForeground((MutableAttributeSet) aset, red); // Comillas d en rojo 
            doc.setCharacterAttributes(startIndex, endIndex - startIndex, aset, false);
        }

        // Comillas Simples
        pattern = Pattern.compile("'[^']*'");
        matcher = pattern.matcher(contenido);

        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            AttributeSet aset = new SimpleAttributeSet();
            StyleConstants.setForeground((MutableAttributeSet) aset, red); // Comillas s en rojo
            doc.setCharacterAttributes(startIndex, endIndex - startIndex, aset, false);
        }

//        // Comillas opcion alternativa 
//        pattern = Pattern.compile("'[^']*'");
//        matcher = pattern.matcher(contenido);
//
//        while (matcher.find()) {
//            int startIndex = matcher.start();
//            int endIndex = matcher.end();
//
//            String matchText = contenido.substring(startIndex, endIndex);
//
//            // Verificar si las comillas están emparejadas
//            if (matchText.matches("'[^']*'")) {
//                AttributeSet aset = new SimpleAttributeSet();
//                StyleConstants.setForeground((MutableAttributeSet) aset, Color.RED); // Texto en rojo
//                doc.setCharacterAttributes(startIndex, endIndex - startIndex, aset, false);
//            }
//        }

    }

    private void cambiarTamanioDeFuente(JTextPane textPane, int size) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setFontSize(attr, size);
        doc.setParagraphAttributes(0, doc.getLength(), attr, false);
    }

}

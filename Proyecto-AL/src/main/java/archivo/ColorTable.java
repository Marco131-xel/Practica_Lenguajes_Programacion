package archivo;

import analisis.Palabras_reservadas;
import analisis.Token;
import java.util.ArrayList;

public class ColorTable {
    ArrayList<Token> lista_token = new ArrayList<>();
    Palabras_reservadas detector = new Palabras_reservadas();
    
    java.awt.Color black = java.awt.Color.black;
    java.awt.Color cyan = java.awt.Color.cyan;
    java.awt.Color magenta = java.awt.Color.magenta;
    java.awt.Color red = java.awt.Color.red;
    java.awt.Color gray = java.awt.Color.gray;
    java.awt.Color green = java.awt.Color.green;

    public ColorTable(ArrayList<Token> lista_token) {
        this.lista_token = lista_token;
    }

    public String[] space(String texto, char separar) {
        String linea = "";
        int contador = 0;

        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == separar) {
                contador++;
            }
        }

        String[] cadenas = new String[contador + 1];
        contador = 0;

        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == separar || i + 1 == texto.length()) {
                cadenas[contador] = linea;
                contador++;
                linea = "";
            } else {
                linea = linea + texto.charAt(i);
            }
        }
        return cadenas;
    }

    public void analizar(String cadena) {
        int estado = 0;
        int numero_token = 0;
        String lexema = "";
        String patron = "";
        String tipo = "";
        String[] lineas = space(cadena, '\n');

        for (int i = 0; i < lineas.length; i++) {
            for (int j = 0; j < lineas[i].length(); j++) {
                int linea_a, linea_s = -1;

                linea_a = lineas[i].codePointAt(j);
                if (estado == 0) {
                    estado = detector.Ntoken(linea_a);
                }

                try {
                    linea_s = lineas[i].codePointAt(j + 1);
                } catch (Exception e) {

                }
                switch (estado) {
                    //palabras
                    case 1:
                        lexema = lexema + lineas[i].charAt(j);
                        if (linea_s > 96 && linea_s < 123
                                || (linea_s > 64 && linea_s < 91) || linea_s == '_') {
                            estado = 1;
                        } else {
                            if (detector.palabras_R(lexema)) {
                                // ------------------------------ PALABRA CLAVE--------------------------------
                              
                                numero_token = 100;
                                tipo = "Palabra clave";
                                patron = "[palabras reservadas]";
                            } else if(detector.logicos_R(lexema)){
                                // ------------------------------- LOGICOS -----------------------------------
                               
                                numero_token = 12;
                                tipo = "Logicos";
                                patron = "[and|or|not]";
                            }else {
                                // -------------------------------- IDENTIFICADOR LETRAS --------------------------------
                                numero_token = 1;
                                tipo = "Identificador";
                                patron = "[a-z,A-Z][_]";
                            }
                            estado = 0;
                        }
                        break;
                    //numeros
                    case 2:
                        lexema = lexema + lineas[i].charAt(j);
                        linea_s = j + 1 < lineas[i].length() ? lineas[i].charAt(j + 1) : -1;

                        if ((linea_s >= '0' && linea_s <= '9') || linea_s == '.') {
                            estado = 2;
                        } else if (linea_s == -1 || (linea_s != '.' && (linea_s < '0' || linea_s > '9'))) {
                            if (lexema.contains(".")) {
                                // --------------------------- NUMEROS --------------------------------
                                numero_token = 8; // Token para números decimales
                                tipo = "Constante Decimal";
                                patron = "[[0-9]+([.][0-9]+)]";
                            } else {
                                numero_token = 2; // Token para números enteros
                                tipo = "Constante Entero";
                                patron = "[0-9]";
                            }
                            estado = 0;
                        } else {
                        }
                        break;
                    case 100:
                        estado = -2;
                        break;
                    case 999:
                        lexema = String.valueOf(lineas[i].charAt(j));
                        numero_token = 999;
                        tipo = "Error Lexico";
                        patron = "";
                        estado = 0;
                        break;
                    //operadores aritmeticos
                    case 3:
                        lexema = lexema + lineas[i].charAt(j);
                        if (linea_s == '+' || linea_s == '-' || linea_s == '*' || linea_s == '/' || linea_s == '%') {
                            estado = 3;
                        } else {
                            if (detector.aritmetico_R(lexema)) {
                                // ------------------------------------------- OPERADORES ARITMETICOS ----------------------------------
                                tipo = "Operadores Aritmeticos";
                                patron = "[[+|-|*|/|**|//|%]]";
                            } else {
                                numero_token = 3;
                                tipo = "Operadores Aritmeticos";
                                patron = "[+|-|*|/|**|//|%]";
                            }
                            estado = 0;
                        }
                        break;
                    //asignacion
                    case 4:
                        lexema = lexema + lineas[i].charAt(j);
                        if (linea_s == '=') {
                            estado = 4;
                        } else {
                            if (detector.comparacion_R(lexema)) {
                                // ----------------------------------- COMPARACION ---------------------------
                                tipo = "Comparacion";
                                patron = "[==|=!|>|<|>=|<=]";
                            } else {
                                numero_token = 4;
                                //----------------------------------- ASIGNACION ---------------------------
                                tipo = "Asignacion";
                                patron = "[=]";
                            }
                            estado = 0;
                        }
                        break;
                    //otros
                    case 5:
                        lexema = lexema + lineas[i].charAt(j);
                        if (linea_s == '(' || linea_s == ')' || linea_s == '{' || linea_s == '}' || linea_s == '['
                                || linea_s == ']' || linea_s == ',' || linea_s == ':' || linea_s == ';') {
                            estado = 5;
                        } else {
                            numero_token = 5;
                            // ------------------------------------------- OTROS -------------------------------------
                            tipo = "Otros";
                            patron = "[()|{}|[]|.|:|;]";
                            estado = 0;
                        }
                        break;
                        //comentario
                    case 6:
                        lexema = lexema + lineas[i].charAt(j);
                        if (linea_s == '#' || linea_s > 96 && linea_s < 123
                                || (linea_s > 64 && linea_s < 91) || (linea_s == 32 || linea_s == 13 || linea_s == 9)) {
                            estado = 6;
                        } else {
                            // --------------------------------------- COMENTARIO -----------------------------------
                            numero_token = 9;
                            tipo = "Comentario";
                            patron = "[^# [A-Za-z]+]";
                            estado = 0;
                        }
                        break;
                        // cadenas
                    case 7:
                        lexema = lexema + lineas[i].charAt(j);
                        if (linea_s == '"' || linea_s > 96 && linea_s < 123
                                || (linea_s > 64 && linea_s < 91) || (linea_s == 32 || linea_s == 13 || linea_s == 9)
                                ||(linea_s > 47 && linea_s < 58) && linea_s == '"') {
                            estado = 7;
                        }else {
                            // ------------------------------------------ CONSTANTES COMILLAS --------------------------
                            numero_token = 13;
                            tipo = "Constantes";
                            patron = "comillas";
                            estado = 0;
                        }
                        break;
                    default:
                        break;
                }
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
                if (estado == 0) {
                    if (!lexema.isEmpty()) {
                        lista_token.add(new Token(lexema, numero_token, patron, i + 1, j + 1, tipo));
                        lexema = "";
                        tipo = "";
                    }
                }
                if (estado == -2) {
                    estado = 0;
                }

            }
        }

    }
}
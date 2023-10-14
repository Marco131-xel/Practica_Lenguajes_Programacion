package analisis;

import java.util.ArrayList;

public class analizador_lexico {

    private ArrayList<Token> lista_token = new ArrayList<>();
    Palabras_reservadas detector = new Palabras_reservadas();

    public analizador_lexico(ArrayList<Token> lista_token) {
        this.lista_token = lista_token;
    }

    public ArrayList<Token> getListaToken() {
        return lista_token;
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
        String tipo = "";
        String lexema = "";
        String patron = "";
        String[] lineas = space(cadena, '\n');

        for (int i = 0; i < lineas.length; i++) {
            if (lineas[i] != null) { // Verificar si lineas[i] no es null
                for (int j = 0; j < lineas[i].length(); j++) {
                    int linea_a = lineas[i].codePointAt(j);
                    int linea_s = -1;
                    if (estado == 0) {
                        estado = detector.Ntoken(linea_a);
                    }
                    try {
                        linea_s = lineas[i].codePointAt(j + 1);
                    } catch (Exception e) {

                    }
                    switch (estado) {
                        // PALABRAS
                        case 1:
                            lexema = lexema + lineas[i].charAt(j);
                            if (linea_s > 96 && linea_s < 123
                                    || (linea_s > 64 && linea_s < 91) || linea_s == '_'
                                    || (linea_s > 47 && linea_s < 58)) {
                                estado = 1;
                            } else {
                                if (detector.palabras_R(lexema)) {
                                    tipo = "Palabra clave";
                                    patron = "[palabras reservadas]";
                                } else if (detector.logicos_R(lexema)) {
                                    tipo = "Logicos";
                                    patron = "[and|or|not]";
                                } else {
                                    tipo = "Identificador";
                                    patron = "[a-z,A-Z][_]";
                                }
                                estado = 0;
                            }
                            break;
                        // NUMEROS
                        case 2:
                            lexema = lexema + lineas[i].charAt(j);
                            linea_s = j + 1 < lineas[i].length() ? lineas[i].charAt(j + 1) : -1;

                            if ((linea_s >= '0' && linea_s <= '9') || linea_s == '.') {
                                estado = 2;
                            } else if (linea_s == -1 || (linea_s != '.' && (linea_s < '0' || linea_s > '9'))) {
                                if (lexema.contains(".")) {
                                    // Token para números decimales
                                    tipo = "Constante Decimal";
                                    patron = "[[0-9]+([.][0-9]+)]";
                                } else {
                                    // Token para números enteros
                                    tipo = "Constante Entero";
                                    patron = "[0-9]";
                                }
                                estado = 0;
                            } else {
                            }
                            break;
                        // OPERADORES ARITMETICOS
                        case 3:
                            lexema = lexema + lineas[i].charAt(j);
                            if (linea_s == '+' || linea_s == '-' || linea_s == '*' || linea_s == '/' || linea_s == '%') {
                                estado = 3;
                            } else {
                                if (detector.aritmetico_R(lexema)) {
                                    tipo = "Operadores Aritmeticos";
                                    patron = "[[+|-|*|/|**|//|%]]";
                                } else {
                                    tipo = "Operadores Aritmeticos";
                                    patron = "[+|-|*|/|**|//|%]";
                                }
                                estado = 0;
                            }
                            break;
                        // ASIGNACION Y COMPARACION
                        case 4:
                            lexema = lexema + lineas[i].charAt(j);
                            if (linea_s == '=') {
                                estado = 4;
                            } else {
                                if (detector.comparacion_R(lexema)) {
                                    tipo = "Comparacion";
                                    patron = "[==|=!|>|<|>=|<=]";
                                } else {
                                    tipo = "Asignacion";
                                    patron = "[=]";
                                }
                                estado = 0;
                            }
                            break;
                        // OTROS
                        case 5:
                            lexema = lexema + lineas[i].charAt(j);
                            if (linea_s == ',' || linea_s == ':' || linea_s == ';') {
                                estado = 5;
                            } else {
                                tipo = "Otros";
                                patron = "[()|{}|[]|,|:|;]";
                                estado = 0;
                            }
                            break;
                        // COMENTARIO
                        case 6:
                            lexema = lexema + lineas[i].charAt(j);
                            if (linea_s == '#' || linea_s > 96 && linea_s < 123 || (linea_s > 64 && linea_s < 91)
                                    || (linea_s > 47 && linea_s < 58)
                                    || (linea_s == '+' || linea_s == '-' || linea_s == '*' || linea_s == '/' || linea_s == '%')
                                    || (linea_s == ',' || linea_s == ':' || linea_s == ';' || linea_s == '.' || linea_s == '=')
                                    || (linea_s == 32 || linea_s == 13 || linea_s == 9)) {
                                estado = 6;
                            } else {
                                tipo = "Comentario";
                                patron = "[^# [A-Za-z]+]";
                                estado = 0;
                            }
                            break;
                        // CADENAS DOBLES
                        case 7:
                            lexema = lexema + lineas[i].charAt(j);
                            linea_s = j + 1 < lineas[i].length() ? lineas[i].charAt(j + 1) : -1;

                            if (linea_s == '"') {
                                // Empieza una cadena entre comillas
                                estado = 7;
                            } else if (linea_s == -1) {
                                // Fin de línea, y la cadena entre comillas no se ha cerrado
                                estado = 999;  // Error léxico
                            } else if (lexema.matches("\"[\\w\\s]+\"")) {
                                // Cadena entre comillas encontrada
                                tipo = "Constantes comillas D";
                                patron = "[\"][a-z,A-Z][0-9][\"]";
                                estado = 0;
                            } else {
                                estado = 7;
                            }
                            break;
                        // CADENAS SIMPLES
                        case 8:
                            lexema = lexema + lineas[i].charAt(j);
                            linea_s = j + 1 < lineas[i].length() ? lineas[i].charAt(j + 1) : -1;

                            if (linea_s == '\'') {
                                // Empieza una cadena entre comillas simples
                                estado = 8;
                            } else if (linea_s == -1) {
                                // Fin de línea, y la cadena entre comillas simples no se ha cerrado
                                estado = 999;  // Error léxico
                            } else if (lexema.matches("\'[\\w\\s]+\'")) {
                                // Cadena entre comillas simples encontrada
                                tipo = "Constantes comillas S";
                                patron = "[\'][a-z,A-Z][0-9][\']";
                                estado = 0;
                            } else {
                                estado = 8;
                            }
                            break;
                        // OTROS PARENTESIS
                        case 9:
                            lexema = lexema + lineas[i].charAt(j);
                            if (linea_s == '(' || linea_s == ')') {
                                estado = 9;
                            } else {
                                tipo = "Otros";
                                patron = "[()|{}|[]|.|:|;]";
                                estado = 0;
                            }
                            break;
                        // OTROS LLAVES
                        case 10:
                            lexema = lexema + lineas[i].charAt(j);
                            if (linea_s == '{' || linea_s == '}') {
                                estado = 10;
                            } else {
                                tipo = "Otros";
                                patron = "[()|{}|[]|.|:|;]";
                                estado = 0;
                            }
                            break;
                        // OTROS CORCHETES
                        case 11:
                            lexema = lexema + lineas[i].charAt(j);
                            if (linea_s == '[' || linea_s == ']') {
                                estado = 11;
                            } else {
                                tipo = "Otros";
                                patron = "[()|{}|[]|.|:|;]";
                                estado = 0;
                            }
                            break;
                        // ESPACIOS EN BLANCOS
                        case 100:
                            estado = -2;
                            break;
                        // ERRORES LEXICOS    
                        case 999:
                            lexema = String.valueOf(lineas[i].charAt(j));
                            tipo = "Error";
                            patron = "";
                            estado = 0;
                            break;
                        // CARACATERES INDIFERENTES    
                        default:
                            break;
                    }

                    if (estado == 0) {
                        if (!lexema.isEmpty()) {
                            lista_token.add(new Token(tipo, lexema, patron, i + 1, j + 1));
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

}

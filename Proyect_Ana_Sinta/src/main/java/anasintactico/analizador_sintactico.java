package anasintactico;

import analisis.*;
import java.util.ArrayList;

public class analizador_sintactico {

    private ArrayList<Simbolos> listaSimbolos;
    private ArrayList<Bloques> cajaBloque;

    public analizador_sintactico(ArrayList<Token> listaTokens) {
        this.listaSimbolos = new ArrayList<>();
        this.listaTokens = listaTokens;
        this.indiceToken = 0;
//        this.cajaBloque = new ArrayList<>();
    }

    public ArrayList<Simbolos> getListaSimbolos() {
        return listaSimbolos;
    }

    public ArrayList<Bloques> getCajaBloque() {
        return cajaBloque;
    }

    private ArrayList<Token> listaTokens;
    private int indiceToken;

    public void analizar() {
        while (indiceToken < listaTokens.size()) {
            Token token = listaTokens.get(indiceToken);
            String tipoToken = token.getTipo();
            String lexema = token.getLexema();

            switch (tipoToken) {

                case "Comentario":
                    // Procesa los comentarios por separado
                    Simbolos comentario = new Simbolos("Comentarios", "Ninguno", lexema, token.getFila(), token.getColumna());
                    listaSimbolos.add(comentario);
                    break;

                case "Identificador":
                    // ARREGLO segundo 

                    if (indiceToken + 3 < listaTokens.size()) {
                        Token asi3 = listaTokens.get(indiceToken + 1);
                        Token pori = listaTokens.get(indiceToken + 2);
                        int currentIndex = indiceToken + 3;

                        String tasi3 = asi3.getTipo();
                        String lasi3 = asi3.getLexema();
                        String tpori = pori.getTipo();
                        String lpori = pori.getLexema();

                        if (tasi3.equals("Asignacion")
                                && (tpori.equals("Otros") && (lpori.equals("(") || lpori.equals("[")))) {
                            // Inicializamos una cadena para almacenar los valores del arreglo
                            String larre = lexema + " " + lasi3 + " " + lpori;
                            boolean elementosValidos = true;

                            // Procesamos los elementos dentro de los paréntesis
                            while (currentIndex < listaTokens.size()) {
                                Token elemento = listaTokens.get(currentIndex);
                                String telemento = elemento.getTipo();
                                String lelemento = elemento.getLexema();

                                if (telemento.equals("Constante Entero")) {
                                    larre += " " + lelemento;

                                    // Avanzamos al siguiente token
                                    currentIndex++;

                                    // Comprobamos si hay una coma después del número (opcional)
                                    if (currentIndex < listaTokens.size()) {
                                        Token siguienteToken = listaTokens.get(currentIndex);
                                        String tsiguiente = siguienteToken.getTipo();
                                        String lsiguiente = siguienteToken.getLexema();

                                        if (tsiguiente.equals("Otros") && lsiguiente.equals(",")) {
                                            larre += ",";
                                            currentIndex++; // Avanzamos al siguiente token después de la coma
                                        }
                                    }
                                } else if (telemento.equals("Otros") && (lelemento.equals(")") || lelemento.equals("]"))) {
                                    // Se encontró el cierre del paréntesis, terminamos el bucle
                                    larre += " " + lelemento;
                                    break;
                                } else {
                                    // Elemento no válido dentro del paréntesis
                                    elementosValidos = false;
                                    break;
                                }
                            }

                            if (elementosValidos) {
                                // Si se cumple el patrón, lo etiquetamos como "Decla Arreglo 2"
                                Simbolos arre = new Simbolos("Decla Arreglo 2", "Ninguno", larre, token.getFila(), token.getColumna());
                                listaSimbolos.add(arre);
                                indiceToken = currentIndex; // Actualizamos el índice del token
                            }
                        }
                    }
                    //OPERADORES DE ENTRADA Y SALIDA
                    if (indiceToken + 5 < listaTokens.size()) {
                        Token pari2 = listaTokens.get(indiceToken + 1);
                        Token comi2 = listaTokens.get(indiceToken + 2);
                        Token copo2 = listaTokens.get(indiceToken + 3);
                        Token comci2 = listaTokens.get(indiceToken + 4);
                        Token parf2 = listaTokens.get(indiceToken + 5);

                        String tpari2 = pari2.getTipo();
                        String lpari2 = pari2.getLexema();
                        String tcomi2 = comi2.getTipo();
                        String lcomi2 = comi2.getLexema();
                        String tcopo2 = copo2.getTipo();
                        String lcopo2 = copo2.getLexema();
                        String tcomci2 = comci2.getTipo();
                        String lcomci2 = comci2.getLexema();
                        String tparf2 = parf2.getTipo();
                        String lparf2 = parf2.getLexema();

                        if (tpari2.equals("Otros") && lpari2.equals("(")
                                && (tcomi2.equals("Constantes comillas D") || tcomi2.equals("Constantes comillas S"))
                                && (tcopo2.equals("Operadores Aritmeticos") || tcopo2.equals("Comparacion") || tcopo2.equals("Otros") && lcopo2.equals(","))
                                && (tcomci2.equals("Constantes comillas D") || tcomci2.equals("Constantes comillas S"))
                                && tparf2.equals("Otros") && lparf2.equals(")")) {
                            // Si se cumple el patrón, lo etiquetamos operadores de entrada y salida
                            String o2 = lexema + " " + lpari2 + " " + lcomi2 + " " + lcopo2 + " " + lcomci2 + " " + lparf2;
                            Simbolos oes = new Simbolos("Decla ope", "Ninguno", o2, token.getFila(), token.getColumna());
                            listaSimbolos.add(oes);
                            indiceToken += 5; // Avanzamos el índice en función de la expresión completa
                        }
                    }
                    // operadores de entrada y salida
                    if (indiceToken + 3 < listaTokens.size()) {
                        Token pari = listaTokens.get(indiceToken + 1);
                        Token comi = listaTokens.get(indiceToken + 2);
                        Token parf = listaTokens.get(indiceToken + 3);

                        String tpari = pari.getTipo();
                        String lpari = pari.getLexema();
                        String tcomi = comi.getTipo();
                        String lcomi = comi.getLexema();
                        String tparf = parf.getTipo();
                        String lparf = parf.getLexema();

                        if ((tpari.equals("Otros") && lpari.equals("("))
                                && (tcomi.equals("Constantes comillas D") || tcomi.equals("Constantes comillas S"))
                                && (tparf.equals("Otros") && lparf.equals(")"))) {

                            String todo4 = lexema + " " + lpari + " " + lcomi + " " + lparf;

                            Simbolos siden4 = new Simbolos("Decla Bucles", "Ninguno", todo4, token.getFila(), token.getColumna());
                            listaSimbolos.add(siden4);
                            indiceToken += 3;
                        }
                    }

                    //DECLARACIONES DE VARIABLES
                    if (indiceToken + 3 < listaTokens.size()) {
                        Token ope = listaTokens.get(indiceToken + 1);
                        Token asig = listaTokens.get(indiceToken + 2);
                        Token num = listaTokens.get(indiceToken + 3);

                        String tope = ope.getTipo();
                        String lope = ope.getLexema();
                        String tasig = asig.getTipo();
                        String lasig = asig.getLexema();
                        String tnum = num.getTipo();
                        String lnum = num.getLexema();

                        if (tope.equals("Operadores Aritmeticos") && tasig.equals("Asignacion")
                                && tnum.equals("Constante Entero")) {

                            String todo3 = lexema + " " + lope + " " + lasig + " " + lnum;

                            Simbolos siden3 = new Simbolos("Declaracion de variables", "Ninguno", todo3, token.getFila(), token.getColumna());
                            listaSimbolos.add(siden3);
                            indiceToken += 3;
                        }
                    }

                    if (indiceToken + 4 < listaTokens.size()) {
                        Token eden1 = listaTokens.get(indiceToken + 1);
                        Token eden2 = listaTokens.get(indiceToken + 2);
                        Token eden3 = listaTokens.get(indiceToken + 3);
                        Token eden4 = listaTokens.get(indiceToken + 4);
                        String teden1 = eden1.getTipo();
                        String leden1 = eden1.getLexema();
                        String teden2 = eden2.getTipo();
                        String leden2 = eden2.getLexema();
                        String teden3 = eden3.getTipo();
                        String leden3 = eden3.getLexema();
                        String teden4 = eden4.getTipo();
                        String leden4 = eden4.getLexema();

                        if (teden1.equals("Asignacion") && teden2.equals("Constante Entero")
                                && (teden3.equals("Operadores Aritmeticos")
                                || teden3.equals("Comparacion"))
                                && teden4.equals("Constante Entero")) {

                            String todo2 = lexema + " " + leden1 + " " + leden2 + " " + leden3 + " " + leden4;

                            Simbolos siden2 = new Simbolos("Declaracion de variables", "Ninguno", todo2, token.getFila(), token.getColumna());
                            listaSimbolos.add(siden2);
                            indiceToken += 4;
                        }
                    }

                    // Expresiones
                    if (indiceToken + 2 < listaTokens.size()) {
                        Token idenasi = listaTokens.get(indiceToken + 1);
                        Token idenasi2 = listaTokens.get(indiceToken + 2);
                        String tiden = idenasi.getTipo();
                        String liden = idenasi.getLexema();
                        String tiden2 = idenasi2.getTipo();
                        String liden2 = idenasi2.getLexema();

                        if (tiden.equals("Asignacion") && (tiden2.equals("Constante Entero")
                                || tiden2.equals("Constante Decimal")
                                || tiden2.equals("Constantes comillas D")
                                || tiden2.equals("Constantes comillas S")
                                || tiden2.equals("Palabra clave"))) {

                            String todo = lexema + " " + liden + " " + liden2;

                            Simbolos siden = new Simbolos("Declaracion de variables", "Ninguno", todo, token.getFila(), token.getColumna());
                            listaSimbolos.add(siden);
                            indiceToken += 2;

                        }
                    }
                    // PRIMER EERROR
                    if (indiceToken + 1 < listaTokens.size()) {
                        Token error1 = listaTokens.get(indiceToken + 1);
                        String terror = error1.getTipo();
                        String lerror = error1.getLexema();

                        if (terror.equals("Asignacion")) {
                            String t0 = lexema + " " + lerror;
                            Simbolos s0 = new Simbolos("ERROR SINTACTICO", "No hay variable asignada", t0, token.getFila(), token.getColumna());
                            listaSimbolos.add(s0);
                            indiceToken += 1;
                        }
                    }
                    break;

                case "Palabra clave":
                    if (lexema.equals("if") && indiceToken + 4 < listaTokens.size()) {
                        Token constEnteroToken = listaTokens.get(indiceToken + 1);
                        Token operadorToken = listaTokens.get(indiceToken + 2);
                        Token constEnteroToken2 = listaTokens.get(indiceToken + 3);
                        Token doblepuntoToken = listaTokens.get(indiceToken + 4);

                        String tipoConstEnteroToken = constEnteroToken.getTipo();
                        String lexemaConstEntero = constEnteroToken.getLexema();
                        String tipoOperadorToken = operadorToken.getTipo();
                        String lexemaOperador = operadorToken.getLexema();
                        String tipoConstEnteroToken2 = constEnteroToken2.getTipo();
                        String lexemaConstEntero2 = constEnteroToken2.getLexema();
                        String tipodoblepuntotoken = doblepuntoToken.getTipo();
                        String lexemadoblepuntotoken = doblepuntoToken.getLexema();

                        if ((tipoConstEnteroToken.equals("Constante Entero") || tipoConstEnteroToken.equals("Identificador"))
                                && (tipoOperadorToken.equals("Operadores Aritmeticos") || tipoOperadorToken.equals("Comparacion"))
                                && (tipoConstEnteroToken2.equals("Constante Entero") || tipoConstEnteroToken2.equals("Identificador"))
                                && tipodoblepuntotoken.equals("Otros") && lexemadoblepuntotoken.equals(":")) {
                            // Si se cumple el patrón, lo etiquetamos como "Expresiones Condicionales"
                            String expresionCond = lexema + " " + lexemaConstEntero + " " + lexemaOperador + " " + lexemaConstEntero2 + lexemadoblepuntotoken;
                            Simbolos expresionCondSimbolo = new Simbolos("Condicionales", "Ninguno", expresionCond, token.getFila(), token.getColumna());
                            listaSimbolos.add(expresionCondSimbolo);
                            indiceToken += 4; // Avanzamos el índice en función de la expresión completa
                        }
                    }

                    if (lexema.equals("elif") && indiceToken + 4 < listaTokens.size()) {
                        Token conetoken = listaTokens.get(indiceToken + 1);
                        Token opetoken = listaTokens.get(indiceToken + 2);
                        Token conetoken2 = listaTokens.get(indiceToken + 3);
                        Token dopuntoken = listaTokens.get(indiceToken + 4);

                        String tconetoken = conetoken.getTipo();
                        String lconetoken = conetoken.getLexema();
                        String topetoken = opetoken.getTipo();
                        String lopetoken = opetoken.getLexema();
                        String tconetoken2 = conetoken2.getTipo();
                        String lconetoken2 = conetoken2.getLexema();
                        String tdopuntoken = dopuntoken.getTipo();
                        String ldopuntoken = dopuntoken.getLexema();

                        if ((tconetoken.equals("Constante Entero") || tconetoken.equals("Identificador"))
                                && (topetoken.equals("Operadores Aritmeticos") || topetoken.equals("Comparacion"))
                                && (tconetoken2.equals("Constante Entero") || tconetoken2.equals("Identificador"))
                                && tdopuntoken.equals("Otros") && ldopuntoken.equals(":")) {
                            // Si se cumple el patrón, lo etiquetamos como "Expresiones Condicionales"
                            String telif = lexema + " " + lconetoken + " " + lopetoken + " " + lconetoken2 + ldopuntoken;
                            Simbolos conelif = new Simbolos("Condicionales", "Ninguno", telif, token.getFila(), token.getColumna());
                            listaSimbolos.add(conelif);
                            indiceToken += 4; // Avanzamos el índice en función de la expresión completa
                        }
                    }

                    if (lexema.equals("else") && indiceToken + 1 < listaTokens.size()) {
                        Token eldpunto = listaTokens.get(indiceToken + 1);

                        String teldpunto = eldpunto.getTipo();
                        String leldpunto = eldpunto.getLexema();

                        if (teldpunto.equals("Otros") && leldpunto.equals(":")) {
                            // Si se cumple el patrón, lo etiquetamos como "Expresiones Condicionales" else
                            String lelse = lexema + " " + leldpunto;
                            Simbolos elpunto = new Simbolos("Condicionales", "Ninguno", lelse, token.getFila(), token.getColumna());
                            listaSimbolos.add(elpunto);
                            indiceToken += 1; // Avanzamos el índice en función de la expresión completa
                        }
                    }

                    if ((lexema.equals("while") || lexema.equals("if")) && indiceToken + 2 < listaTokens.size()) {
                        Token palabraClaveToken = listaTokens.get(indiceToken + 1);
                        Token dpuntoToken = listaTokens.get(indiceToken + 2);

                        String tipoPalabraClave = palabraClaveToken.getTipo();
                        String lexemaPalabraClave = palabraClaveToken.getLexema();
                        String tipoDpunto = dpuntoToken.getTipo();
                        String lexemaDpunto = dpuntoToken.getLexema();

                        if (tipoPalabraClave.equals("Palabra clave") && lexemaPalabraClave.equals("True")
                                && tipoDpunto.equals("Otros") && lexemaDpunto.equals(":")) {
                            // Si se cumple el patrón, lo etiquetamos como "Ciclos"
                            String ciwhile = lexema + " " + lexemaPalabraClave + " " + lexemaDpunto;
                            Simbolos ciclowhile = new Simbolos("Ciclos", "Ninguno", ciwhile, token.getFila(), token.getColumna());
                            listaSimbolos.add(ciclowhile);
                            indiceToken += 2; // Avanzamos el índice en función de la expresión completa
                        }
                    }
                    //bluces wdpu
                    if (lexema.equals("while") && indiceToken + 4 < listaTokens.size()) {
                        Token num1 = listaTokens.get(indiceToken + 1);
                        Token cope = listaTokens.get(indiceToken + 2);
                        Token num2 = listaTokens.get(indiceToken + 3);
                        Token wdpu = listaTokens.get(indiceToken + 4);

                        String tnum1 = num1.getTipo();
                        String lnum1 = num1.getLexema();
                        String tcope = cope.getTipo();
                        String lcope = cope.getLexema();
                        String tnum2 = num2.getTipo();
                        String lnum2 = num2.getLexema();
                        String twdpu = wdpu.getTipo();
                        String lwdpu = wdpu.getLexema();

                        if ((tnum1.equals("Constante Entero") || tnum1.equals("Identificador"))
                                && (tcope.equals("Operadores Aritmeticos") || tcope.equals("Comparacion"))
                                && (tnum2.equals("Constante Entero") || tnum2.equals("Identificador"))
                                && twdpu.equals("Otros") && lwdpu.equals(":")) {
                            // Si se cumple el patrón, lo etiquetamos como "Bucles"
                            String b2 = lexema + " " + lnum1 + " " + lcope + " " + lnum2 + " " + lwdpu;
                            Simbolos blu = new Simbolos("Ciclos", "Ninguno", b2, token.getFila(), token.getColumna());
                            listaSimbolos.add(blu);
                            indiceToken += 4; // Avanzamos el índice en función de la expresión completa
                        }
                    }

                    if (lexema.equals("if") && indiceToken + 2 < listaTokens.size()) {
                        Token identi = listaTokens.get(indiceToken + 1);
                        Token idedpunto = listaTokens.get(indiceToken + 2);

                        String tidenti = identi.getTipo();
                        String lidenti = identi.getLexema();
                        String tidepunto = idedpunto.getTipo();
                        String lidepunto = idedpunto.getLexema();

                        if (tidenti.equals("Identificador")
                                && tidepunto.equals("Otros") && lidepunto.equals(":")) {
                            // Si se cumple el patrón, lo etiquetamos como bucles
                            String ci1 = lexema + " " + lidenti + " " + lidepunto;
                            Simbolos b1 = new Simbolos("Condicionales", "Ninguno", ci1, token.getFila(), token.getColumna());
                            listaSimbolos.add(b1);
                            indiceToken += 2; // Avanzamos el índice en función de la expresión completa
                        }
                    }
                    // Funciones con def 5
                    if (lexema.equals("def") && indiceToken + 7 < listaTokens.size()) {
                        Token iden5 = listaTokens.get(indiceToken + 1);
                        Token pari5 = listaTokens.get(indiceToken + 2);
                        Token cont5 = listaTokens.get(indiceToken + 3);
                        Token coma5 = listaTokens.get(indiceToken + 4);
                        Token conte5 = listaTokens.get(indiceToken + 5);
                        Token parf5 = listaTokens.get(indiceToken + 6);
                        Token dpunto5 = listaTokens.get(indiceToken + 7);

                        String tiden5 = iden5.getTipo();
                        String liden5 = iden5.getLexema();
                        String tpari5 = pari5.getTipo();
                        String lpari5 = pari5.getLexema();
                        String tcont5 = cont5.getTipo();
                        String lcont5 = cont5.getLexema();
                        String tcoma5 = coma5.getTipo();
                        String lcoma5 = coma5.getLexema();
                        String tconte5 = conte5.getTipo();
                        String lconte5 = conte5.getLexema();
                        String tparf5 = parf5.getTipo();
                        String lparf5 = parf5.getLexema();
                        String tdpunto5 = dpunto5.getTipo();
                        String ldpunto5 = dpunto5.getLexema();

                        if (tiden5.equals("Identificador")
                                && (tpari5.equals("Otros") && lpari5.equals("("))
                                && (tcont5.equals("Constante Entero") || tcont5.equals("Identificador"))
                                && (tcoma5.equals("Otros") && lcoma5.equals(","))
                                && (tconte5.equals("Constante Entero") || tconte5.equals("Identificador"))
                                && (tparf5.equals("Otros") && lparf5.equals(")"))
                                && (tdpunto5.equals("Otros") && ldpunto5.equals(":"))) {
                            // Si se cumple el patrón, lo etiquetamos funciones 
                            String ldef5 = lexema + " " + liden5 + " " + lpari5 + " " + lcont5 + " " + lcoma5 + " " + lconte5 + " " + lparf5 + " " + ldpunto5;
                            Simbolos def5 = new Simbolos("Funciones 2", "Ninguno", ldef5, token.getFila(), token.getColumna());
                            listaSimbolos.add(def5);
                            indiceToken += 7; // Avanzamos el índice en función de la expresión completa
                        }
                    }
                    // Funciones con def
                    if (lexema.equals("def") && indiceToken + 5 < listaTokens.size()) {
                        Token iden4 = listaTokens.get(indiceToken + 1);
                        Token pari4 = listaTokens.get(indiceToken + 2);
                        Token contex = listaTokens.get(indiceToken + 3);
                        Token parf4 = listaTokens.get(indiceToken + 4);
                        Token dpunto4 = listaTokens.get(indiceToken + 5);

                        String tiden4 = iden4.getTipo();
                        String liden4 = iden4.getLexema();
                        String tpari4 = pari4.getTipo();
                        String lpari4 = pari4.getLexema();
                        String tcontex = contex.getTipo();
                        String lcontex = contex.getLexema();
                        String tparf4 = parf4.getTipo();
                        String lparf4 = parf4.getLexema();
                        String tdpunto4 = dpunto4.getTipo();
                        String ldpunto4 = dpunto4.getLexema();

                        if ((tiden4.equals("Identificador"))
                                && (tpari4.equals("Otros") && lpari4.equals("("))
                                && (tcontex.equals("Constante Entero") || tcontex.equals("Identificador"))
                                && tparf4.equals("Otros") && lparf4.equals(")")
                                && tdpunto4.equals("Otros") && ldpunto4.equals(":")) {
                            // Si se cumple el patrón, lo etiquetamos como "Funciones"
                            String def1 = lexema + " " + liden4 + " " + lpari4 + " " + lcontex + " " + lparf4 + " " + ldpunto4;
                            Simbolos de1 = new Simbolos("Funciones", "Ninguno", def1, token.getFila(), token.getColumna());
                            listaSimbolos.add(de1);
                            indiceToken += 5; // Avanzamos el índice en función de la expresión completa
                        }
                    }

                    // Return 
                    if (lexema.equals("return") && indiceToken + 4 < listaTokens.size()) {
                        Token iden8 = listaTokens.get(indiceToken + 1);
                        Token pari8 = listaTokens.get(indiceToken + 2);
                        Token ident8 = listaTokens.get(indiceToken + 3);
                        Token parf8 = listaTokens.get(indiceToken + 4);

                        String tiden8 = iden8.getTipo();
                        String liden8 = iden8.getLexema();
                        String tpari8 = pari8.getTipo();
                        String lpari8 = pari8.getLexema();
                        String tident8 = ident8.getTipo();
                        String lident8 = ident8.getLexema();
                        String tparf8 = parf8.getTipo();
                        String lparf8 = parf8.getLexema();

                        if (tiden8.equals("Identificador")
                                && (tpari8.equals("Otros") && lpari8.equals("("))
                                && (tident8.equals("Identificador"))
                                && (tparf8.equals("Otros") && lparf8.equals(")"))) {
                            // Si se cumple el patrón, lo etiquetamos funciones con identificadores 
                            String lretu3 = lexema + " " + liden8 + " " + lpari8 + " " + lident8 + " " + lparf8;
                            Simbolos retu3 = new Simbolos("Return 3", "Ninguno", lretu3, token.getFila(), token.getColumna());
                            listaSimbolos.add(retu3);
                            indiceToken += 4; // Avanzamos el índice en función de la expresión completa
                        }
                    }

                    // return 2
                    if (lexema.equals("return") && indiceToken + 3 < listaTokens.size()) {
                        Token cont6 = listaTokens.get(indiceToken + 1);
                        Token op6 = listaTokens.get(indiceToken + 2);
                        Token conte6 = listaTokens.get(indiceToken + 3);

                        String tcont6 = cont6.getTipo();
                        String lcont6 = cont6.getLexema();
                        String top6 = op6.getTipo();
                        String lop6 = op6.getLexema();
                        String tconte6 = conte6.getTipo();
                        String lconte6 = conte6.getLexema();

                        if ((tcont6.equals("Constante Entero") || tcont6.equals("Identificador"))
                                && top6.equals("Operadores Aritmeticos")
                                && (tconte6.equals("Constante Entero") || tconte6.equals("Identificador"))) {

                            // Si se cumple el patrón, lo etiquetamos funciones operadas
                            String lretu2 = lexema + " " + lcont6 + " " + lop6 + " " + lconte6;
                            Simbolos retu2 = new Simbolos("Return 2", "Ninguno", lretu2, token.getFila(), token.getColumna());
                            listaSimbolos.add(retu2);
                            indiceToken += 3; // Avanzamos el índice en función de la expresión completa
                        }
                    }

                    // return 3
                    if (lexema.equals("return") && indiceToken + 1 < listaTokens.size()) {
                        Token valor = listaTokens.get(indiceToken + 1);

                        String tvalor = valor.getTipo();
                        String lvalor = valor.getLexema();

                        if (tvalor.equals("Identificador")
                                || (tvalor.equals("Constante Entero"))
                                || (tvalor.equals("Constante Decimal"))
                                || (tvalor.equals("Constantes comillas D"))
                                || (tvalor.equals("Constantes comillas S"))) {
                            // Si se cumple el patrón, lo etiquetamos return
                            String lreturn = lexema + " " + lvalor;
                            Simbolos retu = new Simbolos("Return", "Ninguno", lreturn, token.getFila(), token.getColumna());
                            listaSimbolos.add(retu);
                            indiceToken += 1; // Avanzamos el índice en función de la expresión completa
                        }
                    }
                    break;

                case "Error":
                    // Procesa los comentarios por separado
                    Simbolos errorlexico = new Simbolos("ERROR LEXICO", "Variable No Encontrada", lexema, token.getFila(), token.getColumna());
                    listaSimbolos.add(errorlexico);
                    break;

                default:
                    // Manejo de otros tipos de tokens o errores
                    // Puedes personalizar esto según tus necesidades
                    Simbolos simboloError = new Simbolos("ERROR SINTACTICO", "Sin Expresion", lexema, token.getFila(), token.getColumna());
                    listaSimbolos.add(simboloError);
                    break;
            }
            indiceToken++;
        }
    }

}

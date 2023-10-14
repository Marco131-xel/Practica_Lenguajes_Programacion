package anasintactico;

import java.util.ArrayList;

public class Bucles {

    private ArrayList<Bloques> cajaBloque;
    private ArrayList<Simbolos> listaSimbolos;
    private int grupo;

    public Bucles(ArrayList<Simbolos> listaSimbolos) {
        this.cajaBloque = new ArrayList<>();
        this.listaSimbolos = listaSimbolos;
        this.grupo = 0;
    }

    public ArrayList<Bloques> getCajaBloque() {
        return cajaBloque;
    }

    public void crear() {
        while (grupo < listaSimbolos.size()) {
            Simbolos simb = listaSimbolos.get(grupo);
            String respuesta = simb.getRespuesta();
            String lexema = simb.getLexemas();

            switch (respuesta) {
                case "if":
                    if (grupo + 5 < listaSimbolos.size()) {
                        Simbolos ex = listaSimbolos.get(grupo + 1);
                        Simbolos eli = listaSimbolos.get(grupo + 2);
                        Simbolos ex2 = listaSimbolos.get(grupo + 3);
                        Simbolos els = listaSimbolos.get(grupo + 4);
                        Simbolos ex3 = listaSimbolos.get(grupo + 5);

                        String rex = ex.getRespuesta();
                        String lex = ex.getLexemas();
                        String reli = eli.getRespuesta();
                        String leli = eli.getLexemas();
                        String rex2 = ex2.getRespuesta();
                        String lex2 = ex2.getLexemas();
                        String rels = els.getRespuesta();
                        String lels = els.getLexemas();
                        String rex3 = ex3.getRespuesta();
                        String lex3 = ex3.getLexemas();

                        if ((rex.equals("Expresiones"))
                                && (reli.equals("elif"))
                                && (rex2.equals("Expresiones"))
                                && (rels.equals("else"))
                                && (rex3.equals("Expresiones"))) {
                            // Si se cumple el patrón, lo etiquetamos como "Funciones"
                            String lcondi = lexema + "\n " + lex + "\n " + leli + "\n" + lex2 + "\n" + lels + "\n" + lex3;
                            Bloques bcondi = new Bloques("Condicionales", lcondi, "Ninguno", simb.getFilas(), simb.getColumnas());
                            cajaBloque.add(bcondi);
                            grupo += 5; // siquiente bloque
                        }
                    }

                    if (grupo + 3 < listaSimbolos.size()) {
                        Simbolos ex = listaSimbolos.get(grupo + 1);
                        Simbolos eli = listaSimbolos.get(grupo + 2);
                        Simbolos ex2 = listaSimbolos.get(grupo + 3);

                        String rex = ex.getRespuesta();
                        String lex = ex.getLexemas();
                        String reli = eli.getRespuesta();
                        String leli = eli.getLexemas();
                        String rex2 = ex2.getRespuesta();
                        String lex2 = ex2.getLexemas();

                        if ((rex.equals("Expresiones"))
                                && (reli.equals("else"))
                                && (rex2.equals("Expresiones"))) {
                            // Si se cumple el patrón, lo etiquetamos como "Funciones"
                            String lcondi2 = lexema + "\n " + lex + "\n " + leli + "\n" + lex2;
                            Bloques bcondi2 = new Bloques("Condicionales", lcondi2, "Ninguno", simb.getFilas(), simb.getColumnas());
                            cajaBloque.add(bcondi2);
                            grupo += 3; // siquiente bloque
                        }
                    }

                    break;

                case "for":
                    //2
                    if (grupo + 5 < listaSimbolos.size()) {
                        Simbolos ex = listaSimbolos.get(grupo + 1);
                        Simbolos eli = listaSimbolos.get(grupo + 2);
                        Simbolos ex2 = listaSimbolos.get(grupo + 3);
                        Simbolos els = listaSimbolos.get(grupo + 4);
                        Simbolos ex3 = listaSimbolos.get(grupo + 5);

                        String rex = ex.getRespuesta();
                        String lex = ex.getLexemas();
                        String reli = eli.getRespuesta();
                        String leli = eli.getLexemas();
                        String rex2 = ex2.getRespuesta();
                        String lex2 = ex2.getLexemas();
                        String rels = els.getRespuesta();
                        String lels = els.getLexemas();
                        String rex3 = ex3.getRespuesta();
                        String lex3 = ex3.getLexemas();

                        if ((rex.equals("Expresiones"))
                                && (reli.equals("Expresiones"))
                                && (rex2.equals("while"))
                                && (rels.equals("Expresiones"))
                                && (rex3.equals("Expresiones"))) {
                            // Si se cumple el patrón, lo etiquetamos como "Funciones"
                            String lcondi = lexema + "\n " + lex + "\n " + leli + "\n" + lex2 + "\n" + lels + "\n" + lex3;
                            Bloques bcondi = new Bloques("Ciclos", lcondi, "Ninguno", simb.getFilas(), simb.getColumnas());
                            cajaBloque.add(bcondi);
                            grupo += 5; // siquiente bloque
                        }
                    }

                    if (grupo + 1 < listaSimbolos.size()) {
                        Simbolos ex = listaSimbolos.get(grupo + 1);

                        String rex = ex.getRespuesta();
                        String lex = ex.getLexemas();

                        if ((rex.equals("Expresiones"))) {
                            // Si se cumple el patrón, lo etiquetamos como for
                            String lfor = lexema + "\n" + lex;
                            Bloques bfor = new Bloques("Ciclos", lfor, "Ninguno", simb.getFilas(), simb.getColumnas());
                            cajaBloque.add(bfor);
                            grupo += 1; // siquiente bloque
                        }
                    }

                    break;

                case "def":
                    // 1
                    if (grupo + 8 < listaSimbolos.size()) {
                        Simbolos ex = listaSimbolos.get(grupo + 1);
                        Simbolos eli = listaSimbolos.get(grupo + 2);
                        Simbolos ex2 = listaSimbolos.get(grupo + 3);
                        Simbolos els = listaSimbolos.get(grupo + 4);
                        Simbolos ex3 = listaSimbolos.get(grupo + 5);
                        Simbolos ex4 = listaSimbolos.get(grupo + 6);
                        Simbolos ex5 = listaSimbolos.get(grupo + 7);
                        Simbolos ex6 = listaSimbolos.get(grupo + 8);

                        String rex = ex.getRespuesta();
                        String lex = ex.getLexemas();
                        String reli = eli.getRespuesta();
                        String leli = eli.getLexemas();
                        String rex2 = ex2.getRespuesta();
                        String lex2 = ex2.getLexemas();
                        String rels = els.getRespuesta();
                        String lels = els.getLexemas();
                        String rex3 = ex3.getRespuesta();
                        String lex3 = ex3.getLexemas();
                        String rex4 = ex4.getRespuesta();
                        String lex4 = ex4.getLexemas();
                        String rex5 = ex5.getRespuesta();
                        String lex5 = ex5.getLexemas();
                        String rex6 = ex6.getRespuesta();
                        String lex6 = ex6.getLexemas();

                        if ((rex.equals("if"))
                                && (reli.equals("for"))
                                && (rex2.equals("Expresiones"))
                                && (rels.equals("else"))
                                && (rex3.equals("while"))
                                && (rex4.equals("Expresiones"))
                                && (rex5.equals("Expresiones"))
                                && (rex6.equals("return"))) {
                            // Si se cumple el patrón, lo etiquetamos como "Funciones"
                            String ldef3 = lexema + "\n " + lex + "\n " + leli + "\n" + lex2 + "\n" + lels + "\n" + lex3 + "\n" + lex4 + "\n" + lex5 + "\n" + lex6;
                            Bloques bdef3 = new Bloques("Funciones", ldef3, "Ninguno", simb.getFilas(), simb.getColumnas());
                            cajaBloque.add(bdef3);
                            grupo += 8; // siquiente bloque
                        }
                    }

                    //5
                    if (grupo + 3 < listaSimbolos.size()) {
                        Simbolos ex = listaSimbolos.get(grupo + 1);
                        Simbolos eli = listaSimbolos.get(grupo + 2);
                        Simbolos ex2 = listaSimbolos.get(grupo + 3);

                        String rex = ex.getRespuesta();
                        String lex = ex.getLexemas();
                        String reli = eli.getRespuesta();
                        String leli = eli.getLexemas();
                        String rex2 = ex2.getRespuesta();
                        String lex2 = ex2.getLexemas();

                        if ((rex.equals("for"))
                                && (reli.equals("Expresiones"))
                                && (rex2.equals("return"))) {
                            // Si se cumple el patrón, lo etiquetamos como "Funciones"
                            String lcondi = lexema + "\n " + lex + "\n " + leli + "\n" + lex2;
                            Bloques bcondi = new Bloques("Funciones", lcondi, "Ninguno", simb.getFilas(), simb.getColumnas());
                            cajaBloque.add(bcondi);
                            grupo += 3; // siquiente bloque
                        }
                    }

                    // 3
                    if (grupo + 2 < listaSimbolos.size()) {
                        Simbolos ex = listaSimbolos.get(grupo + 1);
                        Simbolos eli = listaSimbolos.get(grupo + 2);

                        String rex = ex.getRespuesta();
                        String lex = ex.getLexemas();
                        String reli = eli.getRespuesta();
                        String leli = eli.getLexemas();

                        if ((rex.equals("Expresiones"))
                                && (reli.equals("return"))) {
                            // Si se cumple el patrón, lo etiquetamos como "Funciones"
                            String ldef4 = lexema + "\n " + lex + "\n " + leli;
                            Bloques bdef4 = new Bloques("Funciones", ldef4, "Ninguno", simb.getFilas(), simb.getColumnas());
                            cajaBloque.add(bdef4);
                            grupo += 2; // siquiente bloque
                        }
                    }

                    //4
                    if (grupo + 2 < listaSimbolos.size()) {
                        Simbolos ex = listaSimbolos.get(grupo + 1);
                        Simbolos eli = listaSimbolos.get(grupo + 2);

                        String rex = ex.getRespuesta();
                        String lex = ex.getLexemas();
                        String reli = eli.getRespuesta();
                        String leli = eli.getLexemas();

                        if ((rex.equals("for"))
                                && (reli.equals("Expresiones"))) {
                            // Si se cumple el patrón, lo etiquetamos como "Funciones"
                            String ldef4 = lexema + "\n " + lex + "\n " + leli;
                            Bloques bdef4 = new Bloques("Funciones", ldef4, "Ninguno", simb.getFilas(), simb.getColumnas());
                            cajaBloque.add(bdef4);
                            grupo += 2; // siquiente bloque
                        }
                    }

                    // 2
                    if (grupo + 1 < listaSimbolos.size()) {
                        Simbolos ex = listaSimbolos.get(grupo + 1);

                        String rex = ex.getRespuesta();
                        String lex = ex.getLexemas();

                        if ((rex.equals("return"))) {
                            // Si se cumple el patrón, lo etiquetamos como "Funciones"
                            String ldef = lexema + "\n " + lex;
                            Bloques bdef = new Bloques("Funciones", ldef, "Ninguno", simb.getFilas(), simb.getColumnas());
                            cajaBloque.add(bdef);
                            grupo += 1; // siquiente bloque
                        }
                    }
                    break;

                case "Expresiones":
                    Bloques expre = new Bloques("Declaracion de Variables", lexema, "Ninguno", simb.getFilas(), simb.getColumnas());
                    cajaBloque.add(expre);
                    break;

                default:

                    break;
            }
            grupo++;
        }
    }
}

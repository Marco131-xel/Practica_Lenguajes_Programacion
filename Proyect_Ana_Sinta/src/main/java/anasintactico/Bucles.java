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
                            //String lcondi = lexema + " " + lex + " " + leli + " " + lex2 + " " + lels + " " + lex3;
                            String lcondi = lexema + "\n " + lex + "\n " + leli + "\n" + lex2 + "\n" + lels + "\n" + lex3;
                            Bloques bcondi = new Bloques("Condicionales", lcondi, "Ninguno", simb.getFilas(), simb.getColumnas());
                            cajaBloque.add(bcondi);
                            grupo += 5; // siquiente bloque
                        }
                    }

                    break;

                default:

                    break;
            }
            grupo++;
        }
    }
}

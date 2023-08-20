package analisis;
import java.util.*;

public class Palabras_reservadas {
    // PALABRA CLAVE
    private List<String> palabrasclave = Arrays.asList("as","assert","break","class",
            "continue","def","del","elif","else","except","False","finally","for",
            "from","global","if","import","in","is","lambda","None","nonlocal"
            ,"pass","raise","return","True","try","while","with","yield");

    public boolean palabras_R(String word){
        return palabrasclave.contains(word);
    }
    // COMPARACION
    private List<String> comparacion = Arrays.asList("==","!=",">","<",">=","<=");

    public boolean comparacion_R(String word){
        return comparacion.contains(word);
    }
    // ARITMETICOS
    private List<String> aritmetico = Arrays.asList("**","//");

    public boolean aritmetico_R(String word){
        return aritmetico.contains(word);
    }
    // LOGICOS
    private List<String> logicos = Arrays.asList("and","or","not");

    public boolean logicos_R(String word){
        return logicos.contains(word);
    }

    public int Ntoken(int n) {
        if (n > 96 && n < 123 || (n > 64 && n < 91) || n == '_') {
            return 1;//letras
        } else if (n > 47 && n < 58) {
            return 2;//numeros
        } else if (n == 32 || n == 13 || n == 9) {
            return 100; // Espacios en blanco
        } else if(n == '+' || n == '-' || n == '*' || n == '/' || n == '%') {
            return 3;//aritmeticos
        }else if(n == '=' || n == '!' || n == '>' || n == '<') {
            return 4;//asignacion
        }else if (n == '(' || n == ')' || n == '{' || n == '}' || n == '['
                || n == ']' || n == ',' || n == ':' || n == ';'){
            return 5;//otros
        }else if(n == '#') {
            return 6;//comentario
        }else if(n == '"'){
            return 7;//cadenas '''
        } else {
            return 999;
        }
    }

}

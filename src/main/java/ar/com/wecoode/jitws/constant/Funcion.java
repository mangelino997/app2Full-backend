package ar.com.wecoode.jitws.constant;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.util.StringUtils;

/**
 * Funciones globales
 * @author blas
 */
public class Funcion {
    
    private static final String WORD_SEPARATOR = " ";

    //Convierte la primera letra de cada palabra a mayuscula
    public static String convertirATitulo(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return Arrays
                .stream(text.split(WORD_SEPARATOR))
                .map(word -> word.isEmpty()
                ? word
                : Character.toTitleCase(word.charAt(0)) + word
                .substring(1)
                .toLowerCase())
                .collect(Collectors.joining(WORD_SEPARATOR));
    }
    
    //Convierte la primera letra en mayuscula
    public static String primerLetraAMayuscula(String palabra) {
        return StringUtils.capitalize(palabra);
    }
    
}

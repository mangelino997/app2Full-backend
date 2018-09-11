package ar.com.wecoode.jitws.constant;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Funciones globales
 * @author blas
 */
public class FuncionConstante {
    
    private static final String WORD_SEPARATOR = " ";

    public static String convertToTitleCase(String text) {
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
    
}

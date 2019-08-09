package ar.com.draimo.jitws.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * Define Excepciones
 * @author blas
 */

@ControllerAdvice
public class Excepcion {
    
    //Define la excepcion de tamanio de archivo excedido
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseEntity<?> tamanioArchivoExcedido(MaxUploadSizeExceededException e) {
        String[] elementos = e.getCause().getCause().getMessage().split(" ");
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.ARCHIVO_LONGITUD, 
                "Tamaño " + elementos[2] + " excedido", 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    //Define la excepcion de tamanio de short excedido
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<?> tamanioShortExcedido(HttpMessageNotReadableException e) {
        System.out.println(e.getCause());
        String[] elementos = e.getCause().getLocalizedMessage().split(" ");
        String s = elementos[19].substring(elementos[19].indexOf("[") + 2);
        s = s.substring(0, s.indexOf("]") -1);
        return new ResponseEntity(new EstadoRespuesta(CodigoRespuesta.LONGITUD, 
                "Tamaño " + s + " excedido", 0), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}

//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.exception;

/**
 * Clase Estado de respuesta
 * Define los codigos y mensajes de respuesta al realizar una operacion
 * contra el servicio web y base de datos
 * @author blas
 */

public class EstadoRespuesta {
    
    //Codigo de respuesta
    private int codigo;
    
    //Mensaje de respuesta
    private String mensaje;
    
    //Constructor de la clase Estado de respuesta
    public EstadoRespuesta(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
    
    //Getters y Setters de la clase

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}

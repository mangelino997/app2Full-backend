//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.exception;

/**
 * Clase Estado de respuesta Agregar
 * Define los codigos y mensajes de respuesta al realizar una operacion
 * contra el servicio web y base de datos
 * @author blas
 */

public class EstadoRespuestaAgregar {
    
    //Codigo de respuesta
    private int codigo;
    
    //Mensaje de respuesta
    private String mensaje;
    
    //Sigueinte id
    private int id;
    
    //Constructor de la clase Estado de respuesta
    public EstadoRespuestaAgregar(int codigo, String mensaje, int id) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}

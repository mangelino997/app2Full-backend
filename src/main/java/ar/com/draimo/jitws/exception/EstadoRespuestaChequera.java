//Paquete al que pertenece la clase
package ar.com.draimo.jitws.exception;

import java.util.List;

/**
 * Clase Estado de respuesta Agregar
 * Define los codigos y mensajes de respuesta al realizar una operacion
 * contra el servicio web y base de datos
 * @author blas
 */

public class EstadoRespuestaChequera {
    
    //Codigo de respuesta
    private int codigo;
    
    //Mensaje de respuesta
    private List<String> mensajes;
    
    //Sigueinte id
    private int id;

    public EstadoRespuestaChequera(int codigo, List<String> mensajes, int id) {
        this.codigo = codigo;
        this.mensajes = mensajes;
        this.id = id;
    }
    
    //Getters y Setters de la clase

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}

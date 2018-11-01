//Paquete al que pertenece la clase
package ar.com.draimo.jitws.dto;

import java.util.List;

/**
 * DTO RolSubopcion
 * Define la estructura JSON para la transferencia de datos.
 * @author blas
 */

public class RolSubopcionDTO {
    
    //Define el id
    private int id;
    
    //Define el id del rol
    private int idRol;
    
    //Define el id del submodulo
    private int idSubmodulo;
    
    //Define el id del subopcion
    private List<Integer> idSubopciones;
    
    //Getters y Setters de la clase

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRol() {
        return idRol;
    }

    public int getIdSubmodulo() {
        return idSubmodulo;
    }

    public void setIdSubmodulo(int idSubmodulo) {
        this.idSubmodulo = idSubmodulo;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public List<Integer> getIdSubopciones() {
        return idSubopciones;
    }

    public void setIdSubopciones(List<Integer> idSubopciones) {
        this.idSubopciones = idSubopciones;
    }
    
}
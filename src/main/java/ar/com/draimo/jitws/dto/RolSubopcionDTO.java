//Paquete al que pertenece la clase
package ar.com.draimo.jitws.dto;

import java.util.List;

/**
 * Define un RolSubopcionDTO
 * @author blas
 */

public class RolSubopcionDTO {
    
    //Define el id
    private int id;
    
    //Define el id del rol
    private RolDTO rol;
    
    //Define el id del submodulo
    private SubmoduloDTO submodulo;
    
    //Define el id del subopcion
    private List<SubopcionDTO> subopciones;
    
    //Getters y Setters de la clase

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }

    public SubmoduloDTO getSubmodulo() {
        return submodulo;
    }

    public void setSubmodulo(SubmoduloDTO submodulo) {
        this.submodulo = submodulo;
    }

    public List<SubopcionDTO> getSubopciones() {
        return subopciones;
    }

    public void setSubopciones(List<SubopcionDTO> subopciones) {
        this.subopciones = subopciones;
    }
    
}
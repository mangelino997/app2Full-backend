package ar.com.wecoode.jitws.dto;

import java.util.List;

/**
 * Submodulo Menu DTO
 * @author blas
 */

public class SubmoduloMenuDTO {

    //Define el nombre del submodulo
    private String submodulo;
    
    //Define la lista de subopciones del submodulo
    private List<SubopcionMenuDTO> subopciones;
    
    //Getters y Setters de la clase

    public String getSubmodulo() {
        return submodulo;
    }

    public void setSubmodulo(String submodulo) {
        this.submodulo = submodulo;
    }

    public List<SubopcionMenuDTO> getSubopciones() {
        return subopciones;
    }

    public void setSubopciones(List<SubopcionMenuDTO> subopciones) {
        this.subopciones = subopciones;
    }
    
}

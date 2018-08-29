package ar.com.wecoode.jitws.model;

import java.util.List;

/**
 * Submodulo Menu
 * @author blas
 */

public class SubmoduloMenu {

    //Define el nombre del submodulo
    private String submodulo;
    
    //Define la lista de subopciones del submodulo
    private List<SubopcionMenu> subopciones;
    
    //Getters y Setters de la clase

    public String getSubmodulo() {
        return submodulo;
    }

    public void setSubmodulo(String submodulo) {
        this.submodulo = submodulo;
    }

    public List<SubopcionMenu> getSubopciones() {
        return subopciones;
    }

    public void setSubopciones(List<SubopcionMenu> subopciones) {
        this.subopciones = subopciones;
    }
    
}

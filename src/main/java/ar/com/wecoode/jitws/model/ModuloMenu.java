package ar.com.wecoode.jitws.model;

import java.util.List;

/**
 * Modulo Menu
 * @author blas
 */

public class ModuloMenu {
    
    //Define el nombre del modulo
    private String modulo;
    
    //Define la lista de submodulos del modulo
    private List<SubmoduloMenu> submodulos;
    
    //Getters y Setters de la clase

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public List<SubmoduloMenu> getSubmodulos() {
        return submodulos;
    }

    public void setSubmodulos(List<SubmoduloMenu> submodulos) {
        this.submodulos = submodulos;
    }
    
}

package ar.com.wecoode.jitws.model;

import java.util.List;

/**
 * Menu
 * @author blas
 */

public class Menu {
    
    //Define la lista de modulos
    private List<ModuloMenu> modulos;
    
    //Getters y Setters de la clase

    public List<ModuloMenu> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloMenu> modulos) {
        this.modulos = modulos;
    }
    
}

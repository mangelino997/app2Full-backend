package ar.com.wecoode.jitws.dto;

import java.util.List;

/**
 * Menu DTO
 * @author blas
 */

public class MenuDTO {
    
    //Define la lista de modulos
    private List<ModuloMenuDTO> modulos;
    
    //Getters y Setters de la clase

    public List<ModuloMenuDTO> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloMenuDTO> modulos) {
        this.modulos = modulos;
    }
    
}

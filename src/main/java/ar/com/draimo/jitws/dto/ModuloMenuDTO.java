package ar.com.draimo.jitws.dto;

import java.util.List;

/**
 * Modulo Menu DTO
 * @author blas
 */

public class ModuloMenuDTO {
    
    //Define el nombre del modulo
    private String modulo;
    
    //Define la lista de submodulos del modulo
    private List<SubmoduloMenuDTO> submodulos;
    
    //Getters y Setters de la clase

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public List<SubmoduloMenuDTO> getSubmodulos() {
        return submodulos;
    }

    public void setSubmodulos(List<SubmoduloMenuDTO> submodulos) {
        this.submodulos = submodulos;
    }
    
}

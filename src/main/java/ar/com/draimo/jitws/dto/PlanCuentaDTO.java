package ar.com.draimo.jitws.dto;

import java.util.List;

/**
 * Define Plan de Cuenta DTO
 * @author blas
 */

public class PlanCuentaDTO {
    
    //Define el nombre
    private String nombre;
    
    //Define la lista de hijos
    private List<PlanCuentaDTO> hijos;
    
    //Define los getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PlanCuentaDTO> getHijos() {
        return hijos;
    }

    public void setHijos(List<PlanCuentaDTO> hijos) {
        this.hijos = hijos;
    }
    
}

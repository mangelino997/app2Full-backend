package ar.com.draimo.jitws.dto;

import java.util.List;

/**
 *
 * @author blas
 */
public class PlanCuentaDTO {
    
    //Define el id
    private int id;
    
    //Define la version
    private int version;
    
    //Define el nombre
    private String nombre;
    
    //Define si es imputable
    private boolean esImputable;
    
    //Define si esta activo
    private boolean estaActivo;
    
    //Define el nivel
    private short nivel;
    
    //Define los hijos
    private List<PlanCuentaDTO> hijos;
    
    //Define los getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsImputable() {
        return esImputable;
    }

    public void setEsImputable(boolean esImputable) {
        this.esImputable = esImputable;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public short getNivel() {
        return nivel;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }
    
    public List<PlanCuentaDTO> getHijos() {
        return hijos;
    }

    public void setHijos(List<PlanCuentaDTO> hijos) {
        this.hijos = hijos;
    }
    
}

package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Vendedor;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitOrdenVentaDTO {
    
    //Define el ultimo id
    private int ultimoId; 
    
    //Lista de pestanias
    private List<Pestania> pestanias;
    
    //Lista de vendedores
    private List<Vendedor> vendedores;

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }
    
}
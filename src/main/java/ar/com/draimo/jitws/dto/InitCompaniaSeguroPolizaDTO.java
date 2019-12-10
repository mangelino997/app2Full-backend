package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Pestania;
import java.util.List;

/**
 * Data Transfer Object of rubroProducto
 * @author blas
 */
public class InitCompaniaSeguroPolizaDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de rubros
    private List<Pestania> pestanias;
    
    //Lista de empresas
    private List<Empresa> empresas;

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

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

}
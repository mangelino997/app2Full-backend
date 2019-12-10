package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitPuntoVentaDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de tipos de docs
    private List<TipoComprobante> tipoComprobantes; 
    
    //Lista de sucursales
    private List<Sucursal> sucursales;  
    
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

    public List<TipoComprobante> getTipoComprobantes() {
        return tipoComprobantes;
    }

    public void setTipoComprobantes(List<TipoComprobante> tipoComprobantes) {
        this.tipoComprobantes = tipoComprobantes;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
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
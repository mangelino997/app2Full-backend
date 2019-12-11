package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.sql.Date;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitViajeRemitoDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //obtiene la fecha
    private Date fecha; 
    
    //Lista de sucursales
    private List<Sucursal> sucursales; 
    
    //Lista de tipos de comprobante
    private List<TipoComprobante> tipoComprobantes; 
    
    //Define las pestanias
    private List<Pestania> pestanias;
    
    //Define Getters y Setters
    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public List<TipoComprobante> getTipoComprobantes() {
        return tipoComprobantes;
    }

    public void setTipoComprobantes(List<TipoComprobante> tipoComprobantes) {
        this.tipoComprobantes = tipoComprobantes;
    }
    
    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}
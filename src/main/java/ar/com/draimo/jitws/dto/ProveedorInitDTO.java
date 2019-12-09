package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.AfipActividad;
import ar.com.draimo.jitws.model.AfipCondicionIva;
import ar.com.draimo.jitws.model.CondicionCompra;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.ObraSocial;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.SeguridadSocial;
import ar.com.draimo.jitws.model.TipoCuentaBancaria;
import ar.com.draimo.jitws.model.TipoDocumento;
import ar.com.draimo.jitws.model.TipoProveedor;
import java.sql.Date;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class ProveedorInitDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Define la fecha actual
    private Date fecha;
    
    //Lista de afipCondicionesIva
    private List<AfipCondicionIva> afipCondicionesIva;  
    
    //Lista de condicionCompras
    private List<CondicionCompra>  condicionCompras; 
    
    //Lista de tipoCuentaBancarias
    private List<TipoCuentaBancaria> tipoCuentaBancarias;  
    
    //Lista de tipoDocumentos
    private List<TipoDocumento> tipoDocumentos;
    
    //Lista de tipoProveedores
    private List<TipoProveedor> tipoProveedores;
    
    //Lista de pestanias
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<AfipCondicionIva> getAfipCondicionesIva() {
        return afipCondicionesIva;
    }

    public void setAfipCondicionesIva(List<AfipCondicionIva> afipCondicionesIva) {
        this.afipCondicionesIva = afipCondicionesIva;
    }

    public List<CondicionCompra> getCondicionCompras() {
        return condicionCompras;
    }

    public void setCondicionCompras(List<CondicionCompra> condicionCompras) {
        this.condicionCompras = condicionCompras;
    }

    public List<TipoCuentaBancaria> getTipoCuentaBancarias() {
        return tipoCuentaBancarias;
    }

    public void setTipoCuentaBancarias(List<TipoCuentaBancaria> tipoCuentaBancarias) {
        this.tipoCuentaBancarias = tipoCuentaBancarias;
    }

    public List<TipoDocumento> getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public List<TipoProveedor> getTipoProveedores() {
        return tipoProveedores;
    }

    public void setTipoProveedores(List<TipoProveedor> tipoProveedores) {
        this.tipoProveedores = tipoProveedores;
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
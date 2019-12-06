package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.AfipCondicionIva;
import ar.com.draimo.jitws.model.Cobrador;
import ar.com.draimo.jitws.model.CondicionVenta;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.ResumenCliente;
import ar.com.draimo.jitws.model.RolOpcion;
import ar.com.draimo.jitws.model.Rubro;
import ar.com.draimo.jitws.model.SituacionCliente;
import ar.com.draimo.jitws.model.SubopcionPestania;
import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.TipoDocumento;
import ar.com.draimo.jitws.model.Vendedor;
import ar.com.draimo.jitws.model.Zona;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class PruebaDTO {
    
    //Lista de condicion de iva
    private List<AfipCondicionIva> afipCondicionesIvas; 
    
    //Lista de tipos de docs
    private List<TipoDocumento> tipoDocumentos; 
    
    //Lista de resumenes clientes
    private List<ResumenCliente> resumenClientes;
    
    //Lista de sutiaciones clientes
    private List<SituacionCliente> situacionClientes;  
    
    //Lista de condiciones de venta
    private List<CondicionVenta> condicionVentas; 
    
    //Lista de sucursales
    private List<Sucursal> sucursales;  
    
    //Lista de cobradores
    private List<Cobrador> cobradores; 
    
    //Lista de vendedores
    private List<Vendedor> vendedores; 
    
    //Lista de zonas
    private List<Zona> zonas; 
    
    //Lista de rubros
    private List<Rubro> rubros; 
    
    //Lista de rubros
    private List<RolOpcion> rolOpciones; 
    
    //Lista de rubros
    private List<SubopcionPestania> subopcionPestanias; 
    
    //Lista de empresas
    private List<Empresa> empresas;

    public List<AfipCondicionIva> getAfipCondicionesIvas() {
        return afipCondicionesIvas;
    }

    public void setAfipCondicionesIvas(List<AfipCondicionIva> afipCondicionesIvas) {
        this.afipCondicionesIvas = afipCondicionesIvas;
    }

    public List<TipoDocumento> getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public List<ResumenCliente> getResumenClientes() {
        return resumenClientes;
    }

    public void setResumenClientes(List<ResumenCliente> resumenClientes) {
        this.resumenClientes = resumenClientes;
    }

    public List<SituacionCliente> getSituacionClientes() {
        return situacionClientes;
    }

    public void setSituacionClientes(List<SituacionCliente> situacionClientes) {
        this.situacionClientes = situacionClientes;
    }

    public List<CondicionVenta> getCondicionVentas() {
        return condicionVentas;
    }

    public void setCondicionVentas(List<CondicionVenta> condicionVentas) {
        this.condicionVentas = condicionVentas;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public List<Cobrador> getCobradores() {
        return cobradores;
    }

    public void setCobradores(List<Cobrador> cobradores) {
        this.cobradores = cobradores;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public List<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }

    public List<Rubro> getRubros() {
        return rubros;
    }

    public void setRubros(List<Rubro> rubros) {
        this.rubros = rubros;
    }

    public List<RolOpcion> getRolOpciones() {
        return rolOpciones;
    }

    public void setRolOpciones(List<RolOpcion> rolOpciones) {
        this.rolOpciones = rolOpciones;
    }

    public List<SubopcionPestania> getSubopcionPestanias() {
        return subopcionPestanias;
    }

    public void setSubopcionPestanias(List<SubopcionPestania> subopcionPestanias) {
        this.subopcionPestanias = subopcionPestanias;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

}

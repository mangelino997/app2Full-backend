package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.AfipActividad;
import ar.com.draimo.jitws.model.AfipCondicion;
import ar.com.draimo.jitws.model.AfipLocalidad;
import ar.com.draimo.jitws.model.AfipModContratacion;
import ar.com.draimo.jitws.model.AfipSiniestrado;
import ar.com.draimo.jitws.model.AfipSituacion;
import ar.com.draimo.jitws.model.Area;
import ar.com.draimo.jitws.model.Categoria;
import ar.com.draimo.jitws.model.EstadoCivil;
import ar.com.draimo.jitws.model.Moneda;
import ar.com.draimo.jitws.model.ObraSocial;
import ar.com.draimo.jitws.model.Opcion;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.SeguridadSocial;
import ar.com.draimo.jitws.model.Sexo;
import ar.com.draimo.jitws.model.Sindicato;
import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.TipoCuentaBancaria;
import ar.com.draimo.jitws.model.TipoDocumento;
import java.sql.Date;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitPersonalDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Define la fecha actual
    private Date fecha;
    
    //Lista de SeguridadSocial
    private List<SeguridadSocial> seguridadSociales; 
    
    //Lista de ObraSocial
    private List<ObraSocial> obraSociales; 
    
    //Lista de  AfipActividad
    private List<AfipActividad> afipActividades;
    
    //Lista de AfipCondicion
    private List<AfipCondicion> afipCondiciones;  
    
    //Lista de tipoCuentaBancarias
    private List<TipoCuentaBancaria> tipoCuentaBancarias;  
    
    //Lista de AfipLocalidad
    private List<AfipLocalidad> afipLocalidades; 
    
    //Lista de AfipModContratacion
    private List<AfipModContratacion> afipModContrataciones;  
    
    //Lista de AfipSiniestrado
    private List<AfipSiniestrado> afipSiniestrados; 
    
    //Lista de AfipSituacion
    private List<AfipSituacion> AfipSituacion; 
    
    //Lista de sexos
    private List<Sexo> sexos; 
    
    //Lista de EstadoCivil
    private List<EstadoCivil> estadoCiviles;
    
    //Lista de tipoDocumentos
    private List<TipoDocumento> tipoDocumentos;
    
    //Lista de Sucursales
    private List<Sucursal> sucursales; 
    
    //Lista de areas
    private List<Area> areas;
    
    //Lista de Sindicato
    private List<Sindicato> sindicatos;
    
    //Lista de categoria
    private List<Categoria> categorias;
    
    //Lista de rubros
    private List<Pestania> pestanias;
    
    //Lista de rubros
    private List<Opcion> opciones; 
    
    //Lista de sucursales
    private List<Moneda> monedas;  

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

    public List<SeguridadSocial> getSeguridadSociales() {
        return seguridadSociales;
    }

    public void setSeguridadSociales(List<SeguridadSocial> seguridadSociales) {
        this.seguridadSociales = seguridadSociales;
    }

    public List<ObraSocial> getObraSociales() {
        return obraSociales;
    }

    public void setObraSociales(List<ObraSocial> obraSociales) {
        this.obraSociales = obraSociales;
    }

    public List<AfipActividad> getAfipActividades() {
        return afipActividades;
    }

    public void setAfipActividades(List<AfipActividad> afipActividades) {
        this.afipActividades = afipActividades;
    }

    public List<AfipCondicion> getAfipCondiciones() {
        return afipCondiciones;
    }

    public void setAfipCondiciones(List<AfipCondicion> afipCondiciones) {
        this.afipCondiciones = afipCondiciones;
    }
    
    public List<TipoCuentaBancaria> getTipoCuentaBancarias() {
        return tipoCuentaBancarias;
    }

    public void setTipoCuentaBancarias(List<TipoCuentaBancaria> tipoCuentaBancarias) {
        this.tipoCuentaBancarias = tipoCuentaBancarias;
    }

    public List<AfipLocalidad> getAfipLocalidades() {
        return afipLocalidades;
    }

    public void setAfipLocalidades(List<AfipLocalidad> afipLocalidades) {
        this.afipLocalidades = afipLocalidades;
    }

    public List<AfipModContratacion> getAfipModContrataciones() {
        return afipModContrataciones;
    }

    public void setAfipModContrataciones(List<AfipModContratacion> afipModContrataciones) {
        this.afipModContrataciones = afipModContrataciones;
    }

    public List<AfipSiniestrado> getAfipSiniestrados() {
        return afipSiniestrados;
    }

    public void setAfipSiniestrados(List<AfipSiniestrado> afipSiniestrados) {
        this.afipSiniestrados = afipSiniestrados;
    }

    public List<AfipSituacion> getAfipSituacion() {
        return AfipSituacion;
    }

    public void setAfipSituacion(List<AfipSituacion> AfipSituacion) {
        this.AfipSituacion = AfipSituacion;
    }

    public List<Sexo> getSexos() {
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
    }

    public List<EstadoCivil> getEstadoCiviles() {
        return estadoCiviles;
    }

    public void setEstadoCiviles(List<EstadoCivil> estadoCiviles) {
        this.estadoCiviles = estadoCiviles;
    }

    public List<TipoDocumento> getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<Sindicato> getSindicatos() {
        return sindicatos;
    }

    public void setSindicatos(List<Sindicato> sindicatos) {
        this.sindicatos = sindicatos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }
    
    
    
}
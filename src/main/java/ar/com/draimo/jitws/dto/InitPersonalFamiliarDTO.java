package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Mes;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Sexo;
import ar.com.draimo.jitws.model.TipoDocumento;
import ar.com.draimo.jitws.model.TipoFamiliar;
import java.sql.Date;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitPersonalFamiliarDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Define la fecha actual
    private Date fecha;
    
    //Lista de sexos
    private List<Sexo> sexos; 
    
    //Lista de tipoDocumentos
    private List<TipoDocumento> tipoDocumentos;
    
    //Lista de rubros
    private List<Pestania> pestanias;
    
    //Lista de MESES
    private List<Mes> meses;
    
    //Lista de Anios
    private List<Integer> anios;
    
    //Lista de Anios
    private List<TipoFamiliar> tipoFamiliares;

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

    public List<Sexo> getSexos() {
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
    }

    public List<TipoDocumento> getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }

    public List<Mes> getMeses() {
        return meses;
    }

    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }

    public List<Integer> getAnios() {
        return anios;
    }

    public void setAnios(List<Integer> anios) {
        this.anios = anios;
    }

    public List<TipoFamiliar> getTipoFamiliares() {
        return tipoFamiliares;
    }

    public void setTipoFamiliares(List<TipoFamiliar> tipoFamiliares) {
        this.tipoFamiliares = tipoFamiliares;
    }
    
}
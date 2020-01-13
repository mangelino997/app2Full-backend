/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Mes;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Provincia;
import ar.com.draimo.jitws.model.TipoRetencion;
import java.util.List;

/**
 *
 * @author marcio
 */
public class InitCobranzaRetencionDTO {

    //Lista de anios
    private List<Short> anios;

    //Lista de resumenes meses
    private List<Mes> meses;

    //Lista de empresas
    private List<Provincia> provincias;
    
    //Lista de empresas
    private List<TipoRetencion> tiposRetencion;

    public List<Short> getAnios() {
        return anios;
    }

    public void setAnios(List<Short> anios) {
        this.anios = anios;
    }

    public List<Mes> getMeses() {
        return meses;
    }

    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public List<TipoRetencion> getTiposRetencion() {
        return tiposRetencion;
    }

    public void setTiposRetencion(List<TipoRetencion> tiposRetencion) {
        this.tiposRetencion = tiposRetencion;
    }
    

}

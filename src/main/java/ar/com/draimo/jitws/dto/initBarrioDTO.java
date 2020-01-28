/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Modulo;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Zona;
import java.util.List;

/**
 *
 * @author Marcio
 */
public class initBarrioDTO {
    //Define el ultimo id
    private int ultimoId;

    //Define las pestanias
    private List<Pestania> pestanias;

    //Lista de modulos
    private List<Modulo> modulos;
    
    //Lista de zonas
    private List<Zona> zonas;

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

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public List<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }
    
    
}

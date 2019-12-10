package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.TipoContacto;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitContactoGenericoDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de condicion de iva
    private List<TipoContacto> tipoContactos; 
    
    //Lista de rubros
    private List<Pestania> pestanias;

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<TipoContacto> getTipoContactos() {
        return tipoContactos;
    }

    public void setTipoContactos(List<TipoContacto> tipoContactos) {
        this.tipoContactos = tipoContactos;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }

}
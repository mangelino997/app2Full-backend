package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pais;
import ar.com.draimo.jitws.model.Pestania;
import java.util.List;

/**
 * Data Transfer Object of rubroProducto
 * @author blas
 */
public class InitProvinciaDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de rubros
    private List<Pestania> pestanias;
    
    //Lista de empresas
    private List<Pais> paises;

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

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }
 
}
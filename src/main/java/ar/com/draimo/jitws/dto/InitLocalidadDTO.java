package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Provincia;
import java.util.List;

/**
 * Data Transfer Object of rubroProducto
 * @author blas
 */
public class InitLocalidadDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de rubros
    private List<Pestania> pestanias;
    
    //Lista de empresas
    private List<Provincia> provincias;

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

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

}
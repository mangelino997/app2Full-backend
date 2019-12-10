package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.TipoDocumento;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitChoferProveedorDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Define tipo de documentos
    private List<TipoDocumento> tipoDocumentos;
    
    //Define pestanias
    private List<Pestania> pestanias;

    public int getUltimoId() {
        return ultimoId;
    }

//Getters and setters de la clase
    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
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
    
}

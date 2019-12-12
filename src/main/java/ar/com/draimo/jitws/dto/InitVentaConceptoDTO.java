package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitVentaConceptoDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Define tipo de documentos
    private List<TipoComprobante> tipoComprobantes;
    
    //Define pestanias
    private List<Pestania> pestanias;

    public int getUltimoId() {
        return ultimoId;
    }

//Getters and setters de la clase
    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<TipoComprobante> getTipoComprobantes() {
        return tipoComprobantes;
    }

    public void setTipoComprobantes(List<TipoComprobante> tipoComprobantes) {
        this.tipoComprobantes = tipoComprobantes;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}

package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Cobrador;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.TalonarioReciboLote;
import java.util.List;

/**
 * Data Transfer Object of rubroProducto
 * @author blas
 */
public class InitTalonarioReciboDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de rubros
    private List<Pestania> pestanias;
    
    //Lista de talonarioReciboLotes
    private List<TalonarioReciboLote> talonarioReciboLotes;
    
    //Lista de cobradores
    private List<Cobrador> cobradores;

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

    public List<TalonarioReciboLote> getTalonarioReciboLotes() {
        return talonarioReciboLotes;
    }

    public void setTalonarioReciboLotes(List<TalonarioReciboLote> talonarioReciboLotes) {
        this.talonarioReciboLotes = talonarioReciboLotes;
    }

    public List<Cobrador> getCobradores() {
        return cobradores;
    }

    public void setCobradores(List<Cobrador> cobradores) {
        this.cobradores = cobradores;
    }

}
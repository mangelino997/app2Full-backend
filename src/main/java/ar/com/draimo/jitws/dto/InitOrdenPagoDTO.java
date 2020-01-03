package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.MedioPago;
import ar.com.draimo.jitws.model.Pestania;
import java.util.List;

/**
 * Inicializador de Orden Pago (DTO)
 * @author blas
 */
public class InitOrdenPagoDTO {
    
    //Define las pestanias
    private List<Pestania> pestanias;
    
    //Define la lista de medios de pagos
    private List<MedioPago> mediosPagos;

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }

    public List<MedioPago> getMediosPagos() {
        return mediosPagos;
    }

    public void setMediosPagos(List<MedioPago> mediosPagos) {
        this.mediosPagos = mediosPagos;
    }
    
}

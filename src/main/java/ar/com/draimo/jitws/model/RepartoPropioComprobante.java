package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase reparto propio comprobante
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "repartopropiocomprobante")
public class RepartoPropioComprobante extends ObjetoGenerico {
    
    //Referencia a la clase reparto propio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRepartoPropio", nullable = false)
    private RepartoPropio repartoPropio;
    
    //Referencia a la clase VentaComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVentaComprobante", nullable = true)
    private VentaComprobante ventaComprobante;
    
    //Referencia a la clase OrdenRecoleccion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idOrdenRecoleccion", nullable = true)
    private OrdenRecoleccion ordenRecoleccion;
    
    //Referencia a la clase ViajeRemito
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeRemito", nullable = true)
    private ViajeRemito viajeRemito;
    
    //Getters y Setters de la clase

    public RepartoPropio getRepartoPropio() {
        return repartoPropio;
    }

    public void setRepartoPropio(RepartoPropio repartoPropio) {
        this.repartoPropio = repartoPropio;
    }

    public VentaComprobante getVentaComprobante() {
        return ventaComprobante;
    }

    public void setVentaComprobante(VentaComprobante ventaComprobante) {
        this.ventaComprobante = ventaComprobante;
    }

    public OrdenRecoleccion getOrdenRecoleccion() {
        return ordenRecoleccion;
    }

    public void setOrdenRecoleccion(OrdenRecoleccion ordenRecoleccion) {
        this.ordenRecoleccion = ordenRecoleccion;
    }

    public ViajeRemito getViajeRemito() {
        return viajeRemito;
    }

    public void setViajeRemito(ViajeRemito viajeRemito) {
        this.viajeRemito = viajeRemito;
    }
    
}

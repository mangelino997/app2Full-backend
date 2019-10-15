package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase reparto comprobante
 * Mapea con la tabla en la base de datos.
 * 
 * @author blas
 */

@Entity
@Table(name = "repartocomprobante")
public class RepartoComprobante extends ObjetoGenerico {
    
    //Referencia a la clase reparto
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idReparto", nullable = false)
    private Reparto reparto;
    
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
    public Reparto getReparto() {
        return reparto;
    }

    public void setReparto(Reparto reparto) {
        this.reparto = reparto;
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
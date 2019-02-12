package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase retiro deposito comprobante
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "retirodepositocomprobante")
public class RetiroDepositoComprobante extends ObjetoGenerico {
    
    //Referencia a la clase RetiroDeposito
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRetiroDeposito", nullable = false)
    private RetiroDeposito retiroDeposito;
    
    //Referencia a la clase VentaComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVentaComprobante", nullable = true)
    private VentaComprobante ventaComprobante;
    
    //Referencia a la clase ViajeRemito
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeRemito", nullable = true)
    private ViajeRemito viajeRemito;
    
    //Define los getters y setters

    public RetiroDeposito getRetiroDeposito() {
        return retiroDeposito;
    }

    public void setRetiroDeposito(RetiroDeposito retiroDeposito) {
        this.retiroDeposito = retiroDeposito;
    }

    public VentaComprobante getVentaComprobante() {
        return ventaComprobante;
    }

    public void setVentaComprobante(VentaComprobante ventaComprobante) {
        this.ventaComprobante = ventaComprobante;
    }

    public ViajeRemito getViajeRemito() {
        return viajeRemito;
    }

    public void setViajeRemito(ViajeRemito viajeRemito) {
        this.viajeRemito = viajeRemito;
    }
    
}

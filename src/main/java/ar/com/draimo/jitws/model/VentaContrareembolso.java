//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Venta contrareembolso
 * Define el modelo (columnas) de la base de datos
 * @author blas
 */

@Entity
@Table(name = "ventacontrareembolso")
public class VentaContrareembolso extends ObjetoGenerico {
    
    //Referencia a la clase Venta comprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVentaComprobante", nullable = false)
    private VentaComprobante ventaComprobante;
    
    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;
    
    //Define comisionCR
    @Column(name = "comisionCR", nullable = false)
    private BigDecimal comisionCR;
    
    //Getters y Setters de la clase

    public VentaComprobante getVentaComprobante() {
        return ventaComprobante;
    }

    public void setVentaComprobante(VentaComprobante ventaComprobante) {
        this.ventaComprobante = ventaComprobante;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getComisionCR() {
        return comisionCR;
    }

    public void setComisionCR(BigDecimal comisionCR) {
        this.comisionCR = comisionCR;
    }

}

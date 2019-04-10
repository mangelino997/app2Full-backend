//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase VentaComprobanteItemCR
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ventacomprobanteitemCR")
public class VentaComprobanteItemCR extends ObjetoGenerico {

    //Referencia a la clase VentaComprobante
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
            property = "id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVentaComprobante", nullable = false)
    private VentaComprobante ventaComprobante;
    
    //Define importeContraReembolso
    @Column(name = "importeContraReembolso", nullable = false)
    private BigDecimal importeContraReembolso;
    
    //Referencia a la clase OrdenVenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idOrdenVenta", nullable = false)
    private OrdenVenta ordenVenta;
    
    //Define pComision
    @Column(name = "pComision", nullable = false)
    private BigDecimal pComision;
    
    //Define fechaCobro
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaCobro", nullable = true)
    private Date fechaCobro;
    
    //Define fechaPago
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaPago", nullable = true)
    private Date fechaPago;
    
    //Getters y Setters de la clase

    public VentaComprobante getVentaComprobante() {
        return ventaComprobante;
    }

    public void setVentaComprobante(VentaComprobante ventaComprobante) {
        this.ventaComprobante = ventaComprobante;
    }

    public BigDecimal getImporteContraReembolso() {
        return importeContraReembolso;
    }

    public void setImporteContraReembolso(BigDecimal importeContraReembolso) {
        this.importeContraReembolso = importeContraReembolso;
    }

    public OrdenVenta getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
    }

    public BigDecimal getpComision() {
        return pComision;
    }

    public void setpComision(BigDecimal pComision) {
        this.pComision = pComision;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

}
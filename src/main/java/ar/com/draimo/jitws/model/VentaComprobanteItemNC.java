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
 * Clase VentaComprobanteItem NC
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ventacomprobanteitemNC")
public class VentaComprobanteItemNC extends ObjetoGenerico {

    //Referencia a la clase VentaComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVentaComprobante", nullable = false)
    private VentaComprobante ventaComprobante;
    
    //Referencia a la clase Concepto
    //@ManyToOne(cascade = CascadeType.REFRESH)
    //@JoinColumn(name = "idConcepto", nullable = false)
    //private Concepto concepto;
    
    //Define el importe neto gravado
    @Column(name = "importeNetoGravado", nullable = true)
    private BigDecimal importeNetoGravado;
    
    //Referencia a la clase idAliCuotaIva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipAlicuotaIva", nullable = true)
    private AfipAlicuotaIva viajeRemito;
    
    //Define la alicuota iva
    @Column(name = "alicuotaIva", nullable = false)
    private BigDecimal alicuotaiva;
    
    //Define el importe de iva
    @Column(name = "importeIva", nullable = true)
    private BigDecimal importeIva;
    
    //Define el importe no gravado
    @Column(name = "importeNoGravado", nullable = true)
    private BigDecimal importeNoGravado;
    
    //Define el importe excento
    @Column(name = "importeExento", nullable = true)
    private BigDecimal importeExento;
    
    //Referencia a la clase Jurisdiccion
//    @Column(name = "idJurisdiccion", nullable = true)
//    private Jurisdiccion jurisdiccion;

//    //Referencia a la clase VentaComprobanteAplicado
//    @ManyToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "idVentaComprobanteAplicado", nullable = true)
//    private VentaComprobanteAplicado ventaComprobanteAplicado;
    
    //Define comprobanteAplicado
    @Column(name = "comprobanteAplicado",length = 14, nullable = false)
    private String comprobanteAplicado;

    public VentaComprobante getVentaComprobante() {
        return ventaComprobante;
    }

    public void setVentaComprobante(VentaComprobante ventaComprobante) {
        this.ventaComprobante = ventaComprobante;
    }

    public BigDecimal getImporteNetoGravado() {
        return importeNetoGravado;
    }

    public void setImporteNetoGravado(BigDecimal importeNetoGravado) {
        this.importeNetoGravado = importeNetoGravado;
    }

    public AfipAlicuotaIva getViajeRemito() {
        return viajeRemito;
    }

    public void setViajeRemito(AfipAlicuotaIva viajeRemito) {
        this.viajeRemito = viajeRemito;
    }

    public BigDecimal getAlicuotaiva() {
        return alicuotaiva;
    }

    public void setAlicuotaiva(BigDecimal alicuotaiva) {
        this.alicuotaiva = alicuotaiva;
    }

    public BigDecimal getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(BigDecimal importeIva) {
        this.importeIva = importeIva;
    }

    public BigDecimal getImporteNoGravado() {
        return importeNoGravado;
    }

    public void setImporteNoGravado(BigDecimal importeNoGravado) {
        this.importeNoGravado = importeNoGravado;
    }

    public BigDecimal getImporteExento() {
        return importeExento;
    }

    public void setImporteExento(BigDecimal importeExento) {
        this.importeExento = importeExento;
    }

    public String getComprobanteAplicado() {
        return comprobanteAplicado;
    }

    public void setComprobanteAplicado(String comprobanteAplicado) {
        this.comprobanteAplicado = comprobanteAplicado;
    }
    
}
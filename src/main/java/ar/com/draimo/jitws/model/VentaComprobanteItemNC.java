//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFilter;
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
@JsonFilter(value = "filtroVentaComprobanteItemNC")
public class VentaComprobanteItemNC extends ObjetoGenerico {

    //Referencia a la clase VentaComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVentaComprobante", nullable = false)
    private VentaComprobante ventaComprobante;
    
    //Referencia a la clase Concepto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVentaTipoItem", nullable = false)
    private VentaTipoItem ventaTipoItem;
    
    //Define el importe neto gravado
    @Column(name = "importeNetoGravado", nullable = false)
    private BigDecimal importeNetoGravado;
    
    //Referencia a la clase afipAliCuotaIva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipAlicuotaIva", nullable = false)
    private AfipAlicuotaIva viajeRemito;
    
    //Define la alicuota iva
    @Column(name = "alicuotaIva", nullable = false)
    private BigDecimal alicuotaiva;
    
    //Define el importe de iva
    @Column(name = "importeIva", nullable = false)
    private BigDecimal importeIva;
    
    //Define el importe no gravado
    @Column(name = "importeNoGravado", nullable = false)
    private BigDecimal importeNoGravado;
    
    //Define el importe excento
    @Column(name = "importeExento", nullable = false)
    private BigDecimal importeExento;
    
    //Referencia a la clase provincia
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProvincia", nullable = false)
    private Provincia provincia;

    //Referencia a la clase VentaComprobanteAplicado
    @Column(name = "idVentaComprobanteAplicado", nullable = true)
    private int ventaComprobanteAplicado;
    
    //Define comprobanteAplicado
    @Column(name = "comprobanteAplicado",length = 14, nullable = true)
    private String comprobanteAplicado;
    
    //Getters y setters de la clase
    public VentaComprobante getVentaComprobante() {
        return ventaComprobante;
    }

    public void setVentaComprobante(VentaComprobante ventaComprobante) {
        this.ventaComprobante = ventaComprobante;
    }

    public VentaTipoItem getVentaTipoItem() {
        return ventaTipoItem;
    }

    public void setVentaTipoItem(VentaTipoItem ventaTipoItem) {
        this.ventaTipoItem = ventaTipoItem;
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public int getVentaComprobanteAplicado() {
        return ventaComprobanteAplicado;
    }

    public void setVentaComprobanteAplicado(int ventaComprobanteAplicado) {
        this.ventaComprobanteAplicado = ventaComprobanteAplicado;
    }

    public String getComprobanteAplicado() {
        return comprobanteAplicado;
    }

    public void setComprobanteAplicado(String comprobanteAplicado) {
        this.comprobanteAplicado = comprobanteAplicado;
    }
    
}
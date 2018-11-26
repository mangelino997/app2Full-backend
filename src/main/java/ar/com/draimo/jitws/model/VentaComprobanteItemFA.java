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
 * Clase VentaComprobanteItem
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ventacomprobanteitemFA")
public class VentaComprobanteItemFA extends ObjetoGenerico {

    //Referencia a la clase VentaComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVentaComprobante", nullable = false)
    private VentaComprobante ventaComprobante;
    
    //Referencia a la clase Concepto
    //@ManyToOne(cascade = CascadeType.REFRESH)
    //@JoinColumn(name = "idConcepto", nullable = false)
    //private Concepto concepto;
    
    //Referencia a la clase ViajeRemito
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeRemito", nullable = true)
    private ViajeRemito viajeRemito;
    
    //Define los bultos
    @Column(name = "bultos", nullable = false)
    private short bultos;
    
    //Define los kilos efectivos
    @Column(name = "kilosEfectivo", nullable = true)
    private BigDecimal kilosEfectivo;
    
    //Define los kilos aforados
    @Column(name = "kilosAforado", nullable = true)
    private BigDecimal kilosAforado;
    
    //Define los m3
    @Column(name = "m3", nullable = true)
    private BigDecimal m3;
    
    //Define la descripcion carga
    @Column(name = "descripcionCarga", nullable = true)
    private String descripcionCarga;
    
    //Define el valor declarado
    @Column(name = "valorDeclarado", nullable = true)
    private BigDecimal valorDeclarado;
    
    //Define el p seguro
    @Column(name = "pSeguro", nullable = true)
    private BigDecimal pSeguro;
    
    //Define el importe seguro
    @Column(name = "importeSeguro", nullable = true)
    private BigDecimal importeSeguro;
    
    //Define el flete
    @Column(name = "flete", nullable = false)
    private BigDecimal flete;
    
    //Define el descuento flete
    @Column(name = "descuentoFlete", nullable = true)
    private BigDecimal descuentoFlete;
    
    //Define el importe flete
    @Column(name = "importeFlete", nullable = false)
    private BigDecimal importeFlete;
    
    //Define el importe retiro
    @Column(name = "importeRetiro", nullable = true)
    private BigDecimal importeRetiro;
    
    //Define el importe entrega
    @Column(name = "importeEntrega", nullable = true)
    private BigDecimal importeEntrega;
    
    //Define el importe neto gravado
    @Column(name = "importeNetoGravado", nullable = true)
    private BigDecimal importeNetoGravado;
    
    //Referencia a la clase AlicuotaIva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipAlicuotaIva", nullable = false)
    private AfipAlicuotaIva afipAlicuotaIva;
    
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

    //Getters y Setters de la clase

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

    public short getBultos() {
        return bultos;
    }

    public void setBultos(short bultos) {
        this.bultos = bultos;
    }

    public BigDecimal getKilosEfectivo() {
        return kilosEfectivo;
    }

    public void setKilosEfectivo(BigDecimal kilosEfectivo) {
        this.kilosEfectivo = kilosEfectivo;
    }

    public BigDecimal getKilosAforado() {
        return kilosAforado;
    }

    public void setKilosAforado(BigDecimal kilosAforado) {
        this.kilosAforado = kilosAforado;
    }

    public BigDecimal getM3() {
        return m3;
    }

    public void setM3(BigDecimal m3) {
        this.m3 = m3;
    }

    public String getDescripcionCarga() {
        return descripcionCarga;
    }

    public void setDescripcionCarga(String descripcionCarga) {
        this.descripcionCarga = descripcionCarga;
    }

    public BigDecimal getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(BigDecimal valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public BigDecimal getpSeguro() {
        return pSeguro;
    }

    public void setpSeguro(BigDecimal pSeguro) {
        this.pSeguro = pSeguro;
    }

    public BigDecimal getImporteSeguro() {
        return importeSeguro;
    }

    public void setImporteSeguro(BigDecimal importeSeguro) {
        this.importeSeguro = importeSeguro;
    }

    public BigDecimal getFlete() {
        return flete;
    }

    public void setFlete(BigDecimal flete) {
        this.flete = flete;
    }

    public BigDecimal getDescuentoFlete() {
        return descuentoFlete;
    }

    public void setDescuentoFlete(BigDecimal descuentoFlete) {
        this.descuentoFlete = descuentoFlete;
    }

    public BigDecimal getImporteFlete() {
        return importeFlete;
    }

    public void setImporteFlete(BigDecimal importeFlete) {
        this.importeFlete = importeFlete;
    }

    public BigDecimal getImporteRetiro() {
        return importeRetiro;
    }

    public void setImporteRetiro(BigDecimal importeRetiro) {
        this.importeRetiro = importeRetiro;
    }

    public BigDecimal getImporteEntrega() {
        return importeEntrega;
    }

    public void setImporteEntrega(BigDecimal importeEntrega) {
        this.importeEntrega = importeEntrega;
    }

    public BigDecimal getImporteNetoGravado() {
        return importeNetoGravado;
    }

    public void setImporteNetoGravado(BigDecimal importeNetoGravado) {
        this.importeNetoGravado = importeNetoGravado;
    }

    public AfipAlicuotaIva getAfipAlicuotaIva() {
        return afipAlicuotaIva;
    }

    public void setAfipAlicuotaIva(AfipAlicuotaIva afipAlicuotaIva) {
        this.afipAlicuotaIva = afipAlicuotaIva;
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
    
    public AfipAlicuotaIva getAlicuotaIva() {
        return afipAlicuotaIva;
    }

    public void setAlicuotaIva(AfipAlicuotaIva alicuotaIva) {
        this.afipAlicuotaIva = alicuotaIva;
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
    
}
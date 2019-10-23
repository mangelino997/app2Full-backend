//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Viaje Tramo Cliente Remito
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "viajetramoclienteremito")
public class ViajeTramoClienteRemito extends ObjetoGenerico {

    //Referencia a la clase viajeTramoCliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTramoCliente", nullable = false)
    private ViajeTramoCliente viajeTramoCliente;
    
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    //Referencia a la clase tipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define el punto de venta
    @Column(name = "puntoVenta", length = 5, nullable = false)
    private int puntoVenta;

    //Define la letra
    @Column(name = "letra", length = 1, nullable = false)
    private String letra;

    //Define numero
    @Column(name = "numero", length = 8, nullable = false)
    private int numero;
    
    //Define los bultos
    @Column(name = "bultos", nullable = false)
    private short bultos;
    
    //Define los kilos efectivo
    @Column(name = "kgEfectivo", nullable = true)
    private BigDecimal kgEfectivo;
    
    //Define los kilos aforados
    @Column(name = "kgAforado", nullable = true)
    private BigDecimal kgAforado;
    
    //Define los m3
    @Column(name = "m3", nullable = true)
    private BigDecimal m3;
    
    //Define el valor declarado
    @Column(name = "valorDeclarado", nullable = true)
    private BigDecimal valorDeclarado;
    
    //Define el importe retiro
    @Column(name = "importeRetiro", nullable = true)
    private BigDecimal importeRetiro;
    
    //Define el importe entrega
    @Column(name = "importeEntrega", nullable = true)
    private BigDecimal importeEntrega;
    
    //Define las observaciones
    @Column(name = "observaciones",length = 60, nullable = true)
    private String observaciones;
    
    //Define si esta facturado
    @Column(name = "estaFacturado", nullable = false)
    private boolean estaFacturado;
 
    //Referencia a la clase Usuario (alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Referencia a la clase Usuario (mod)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Getters y Setters de la clase

    public ViajeTramoCliente getViajeTramoCliente() {
        return viajeTramoCliente;
    }

    public void setViajeTramoCliente(ViajeTramoCliente viajeTramoCliente) {
        this.viajeTramoCliente = viajeTramoCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public int getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(int puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public short getBultos() {
        return bultos;
    }

    public void setBultos(short bultos) {
        this.bultos = bultos;
    }

    public BigDecimal getKgEfectivo() {
        return kgEfectivo;
    }

    public void setKgEfectivo(BigDecimal kgEfectivo) {
        this.kgEfectivo = kgEfectivo;
    }

    public BigDecimal getKgAforado() {
        return kgAforado;
    }

    public void setKgAforado(BigDecimal kgAforado) {
        this.kgAforado = kgAforado;
    }
    
    public BigDecimal getM3() {
        return m3;
    }

    public void setM3(BigDecimal m3) {
        this.m3 = m3;
    }

    public BigDecimal getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(BigDecimal valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isEstaFacturado() {
        return estaFacturado;
    }

    public void setEstaFacturado(boolean estaFacturado) {
        this.estaFacturado = estaFacturado;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }
    
}

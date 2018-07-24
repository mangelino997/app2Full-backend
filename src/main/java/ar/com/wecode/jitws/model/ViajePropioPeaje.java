//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Viaje Propio Peaje
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajepropiopeaje")
public class ViajePropioPeaje extends ObjetoGenerico {

    //Referencia a la clase Viaje Propio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViajePropio", nullable = false)
    private ViajePropio viajePropio;
    
    //Referencia a la clase Proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;
    
    //Referencia a la clase TipoComprobante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define el punto de venta
    @Column(name = "puntoVenta", nullable = false)
    private int puntoVenta;
    
    //Define el letra
    @Column(name = "letra", nullable = false)
    private String letra;
    
    //Define el numero
    @Column(name = "numeroComprobante", nullable = false)
    private int numeroComprobante;
    
    //Define la fecha
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;
    
    //Referencia a la clase Empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpresaCFiscal", nullable = true)
    private Empresa empresaCFiscal;
    
    //Referencia a la clase
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRegistroCFiscal", nullable = true)
    private Registro registroCFiscal;*/
    
    //Referencia a la clase Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    //Getters y Setters de la clase

    public ViajePropio getViajePropio() {
        return viajePropio;
    }

    public void setViajePropio(ViajePropio viajePropio) {
        this.viajePropio = viajePropio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    public int getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(int numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Empresa getEmpresaCFiscal() {
        return empresaCFiscal;
    }

    public void setEmpresaCFiscal(Empresa empresaCFiscal) {
        this.empresaCFiscal = empresaCFiscal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
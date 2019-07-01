package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase CompraComprobante
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "compracomprobante")
public class CompraComprobante extends ObjetoGenerico {
    
    //Referencia a la clase empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Define el puntoVenta
    @Column(name = "puntoVenta",length = 5, nullable = false)
    private int puntoVenta;
    
    //Define letra
    @Column(name = "letra",length = 1, nullable = false)
    private String letra;
    
    //Define numero
    @Column(name = "numero",length = 8, nullable = false)
    private int numero;
    
    //Referencia a la clase TipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define codigoAfip
    @Column(name = "codigoAfip",length = 3, nullable = false)
    private String codigoAfip;
    
    //Define fechaEmision
    @Column(name = "fechaEmision", nullable = false)
    private Date fechaEmision;
    
    //Define fechaContable
    @Column(name = "fechaContable", nullable = false)
    private Date fechaContable;
    
    //Define fechaRegistracion
    @Column(name = "fechaRegistracion", nullable = false)
    private Timestamp fechaRegistracion;
    
    //Referencia a la clase proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;
    
    //Referencia a la clase AfipCondicionIva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipCondicionIva", nullable = false)
    private AfipCondicionIva afipCondicionIva;
    
    //Referencia a la clase TipoDocumento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;
    
    //Define numeroDocumento
    @Column(name = "numeroDocumento",length = 15, nullable = false)
    private String numeroDocumento;

    //Referencia a la clase CondicionCompra
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCondicionCompra", nullable = false)
    private CondicionCompra condicionCompra;
    
    //Define importeNetoGravado
    @Column(name = "importeNetoGravado", nullable = false)
    private BigDecimal importeNetoGravado;
    
    //Define importeIVA
    @Column(name = "importeIVA", nullable = false)
    private BigDecimal importeIVA;
    
    //Define importeNoGravado
    @Column(name = "importeNoGravado", nullable = false)
    private BigDecimal importeNoGravado;
    
    //Define importeExento
    @Column(name = "importeExento", nullable = false)
    private BigDecimal importeExento;
    
    //Define importeImpuestoInterno
    @Column(name = "importeImpuestoInterno", nullable = false)
    private BigDecimal importeImpuestoInterno;
    
    //Define importePercepcion
    @Column(name = "importePercepcion", nullable = false)
    private BigDecimal importePercepcion;
    
    //Define importeTotal
    @Column(name = "importeTotal", nullable = false)
    private BigDecimal importeTotal;
    
    //Define importeSaldo
    @Column(name = "importeSaldo", nullable = false)
    private BigDecimal importeSaldo;

    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;

    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = false)
    private Usuario usuarioMod;

    //Referencia a la clase moneda
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMoneda", nullable = false)
    private Moneda moneda;
    
    //Define monedaCotizacion
    @Column(name = "monedaCotizacion", nullable = false)
    private BigDecimal monedaCotizacion;
    
    //Getters y Setters de la clase

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
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

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(Date fechaContable) {
        this.fechaContable = fechaContable;
    }

    public Timestamp getFechaRegistracion() {
        return fechaRegistracion;
    }

    public void setFechaRegistracion(Timestamp fechaRegistracion) {
        this.fechaRegistracion = fechaRegistracion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public AfipCondicionIva getAfipCondicionIva() {
        return afipCondicionIva;
    }

    public void setAfipCondicionIva(AfipCondicionIva afipCondicionIva) {
        this.afipCondicionIva = afipCondicionIva;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public CondicionCompra getCondicionCompra() {
        return condicionCompra;
    }

    public void setCondicionCompra(CondicionCompra condicionCompra) {
        this.condicionCompra = condicionCompra;
    }

    public BigDecimal getImporteNetoGravado() {
        return importeNetoGravado;
    }

    public void setImporteNetoGravado(BigDecimal importeNetoGravado) {
        this.importeNetoGravado = importeNetoGravado;
    }

    public BigDecimal getImporteIVA() {
        return importeIVA;
    }

    public void setImporteIVA(BigDecimal importeIVA) {
        this.importeIVA = importeIVA;
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

    public BigDecimal getImporteImpuestoInterno() {
        return importeImpuestoInterno;
    }

    public void setImporteImpuestoInterno(BigDecimal importeImpuestoInterno) {
        this.importeImpuestoInterno = importeImpuestoInterno;
    }

    public BigDecimal getImportePercepcion() {
        return importePercepcion;
    }

    public void setImportePercepcion(BigDecimal importePercepcion) {
        this.importePercepcion = importePercepcion;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigDecimal getImporteSaldo() {
        return importeSaldo;
    }

    public void setImporteSaldo(BigDecimal importeSaldo) {
        this.importeSaldo = importeSaldo;
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

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getMonedaCotizacion() {
        return monedaCotizacion;
    }

    public void setMonedaCotizacion(BigDecimal monedaCotizacion) {
        this.monedaCotizacion = monedaCotizacion;
    }

}

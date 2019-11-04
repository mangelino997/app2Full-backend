//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase VentaComprobante Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "ventacomprobante")
public class VentaComprobante extends ObjetoGenerico {

    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;

    //Define el punto de venta
    @Column(name = "puntoVenta", length = 5, nullable = false)
    private int puntoVenta;

    //Define la letra
    @Column(name = "letra", length = 1, nullable = false)
    private String letra;

    //Define el numero
    @Column(name = "numero", length = 8, nullable = false)
    private int numero;

    //Referencia a la clase TipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;

    //Define el codigo de afip
    @Column(name = "codigoAfip", length = 3, nullable = false)
    private String codigoAfip;

    //Define la fecha de emision
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaEmision", nullable = false)
    private Date fechaEmision;

    //Define la fecha de vencimiento de pago
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaVtoPago", nullable = false)
    private Date fechaVtoPago;

    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    //Referencia a la clase Cliente (clienteGrupo)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idClienteGrupo", nullable = true)
    private Cliente clienteGrupo;

    //Referencia a la clase afipCondicionIva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipCondicionIva", nullable = false)
    private AfipCondicionIva afipCondicionIva;

    //Referencia a la clase tipoDocumento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;

    //Define el numeroDocumento
    @Column(name = "numeroDocumento", length = 15, nullable = false)
    private String numeroDocumento;

    //Referencia a la clase Cliente (remitente)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idClienteRemitente", nullable = false)
    private Cliente clienteRemitente;

    //Referencia a la clase Cliente (destinatario)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idClienteDestinatario", nullable = false)
    private Cliente clienteDestinatario;

    //Referencia a la clase SucursalCliente (remitente)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursalClienteRem", nullable = true)
    private SucursalCliente sucursalClienteRem;

    //Referencia a la clase SucursalCliente (destinatario)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursalClienteDes", nullable = true)
    private SucursalCliente sucursalClienteDes;

    //Referencia a la clase CondicionVenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCondicionVenta", nullable = false)
    private CondicionVenta condicionVenta;

    //Define el importe no gravado
    @Column(name = "importeNoGravado", nullable = false)
    private BigDecimal importeNoGravado;

    //Define el importe neto gravado
    @Column(name = "importeNetoGravado", nullable = false)
    private BigDecimal importeNetoGravado;

    //Define el importe iva
    @Column(name = "importeIva", nullable = false)
    private BigDecimal importeIva;

    //Define el importe de otros tributos
    @Column(name = "importeOtrosTributos", nullable = false)
    private BigDecimal importeOtrosTributos;

    //Define el importe total
    @Column(name = "importeTotal", nullable = false)
    private BigDecimal importeTotal;

    //Define el importe saldo
    @Column(name = "importeSaldo", nullable = false)
    private BigDecimal importeSaldo;

    //Referencia a la clase Cobrador
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCobrador", nullable = false)
    private Cobrador cobrador;

    //Define si el pago es en origen
    @Column(name = "pagoEnOrigen", nullable = false)
    private boolean pagoEnOrigen;

    //Referencia a la clase Usuario (alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;

    //Referencia a la clase Usuario (modificacion)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;

    //Define si es CAEA
    @Column(name = "esCAEA", nullable = false)
    private boolean esCAEA;

    //Define CAE
    @Column(name = "CAE", length = 14, nullable = true)
    private String CAE;

    //Define CAEVencimiento
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "CAEVencimiento", nullable = true)
    private Date CAEVencimiento;

    //Define CAEEstado
    @Column(name = "CAEEstado", length = 1, nullable = true)
    private String CAEEstado;

    //Define CAEVencimiento
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "UTC-3")
    @Column(name = "fechaRegistracion", nullable = true)
    private Timestamp fechaRegistracion;

    //Referencia a la clase AfipConcepto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipConcepto", nullable = false)
    private AfipConcepto afipConcepto;

    //Referencia a la clase Moneda
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMoneda", nullable = false)
    private Moneda moneda;

    //Define monedaCotizacion
    @Column(name = "monedaCotizacion", nullable = false)
    private BigDecimal monedaCotizacion;

    //Define observaciones
    @Column(name = "observaciones",length = 150, nullable = false)
    private String observaciones;

    //Referencia a la clase ventaComprobanteItemFA (lista)
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ventaComprobante")
    @JsonIgnoreProperties("ventaComprobante")
    private List<VentaComprobanteItemFA> ventaComprobanteItemFAs;

    //Referencia a la clase ventaComprobanteItemCR (lista)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "ventaComprobante")
    @JsonIgnoreProperties("ventaComprobante")
    private List<VentaComprobanteItemCR> ventaComprobanteItemCR;

    //Referencia a la clase ventaComprobanteItemNC(lista)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "ventaComprobante")
    @JsonIgnoreProperties("ventaComprobante")
    private List<VentaComprobanteItemNC> ventaComprobanteItemNC;

    //Referencia a la clase ventaComprobanteItemNC (lista)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "ventaComprobante")
    @JsonIgnoreProperties("ventaComprobante")
    private List<VentaComprobanteItemND> ventaComprobanteItemND;

    //Referencia a la clase SeguimientoVentaComprobante(lista)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "ventaComprobante")
    @JsonIgnoreProperties("ventaComprobante")
    private List<SeguimientoVentaComprobante> seguimientoVentaComprobantes;

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

    public Date getFechaVtoPago() {
        return fechaVtoPago;
    }

    public void setFechaVtoPago(Date fechaVtoPago) {
        this.fechaVtoPago = fechaVtoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getClienteGrupo() {
        return clienteGrupo;
    }

    public void setClienteGrupo(Cliente clienteGrupo) {
        this.clienteGrupo = clienteGrupo;
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

    public Cliente getClienteRemitente() {
        return clienteRemitente;
    }

    public void setClienteRemitente(Cliente clienteRemitente) {
        this.clienteRemitente = clienteRemitente;
    }

    public Cliente getClienteDestinatario() {
        return clienteDestinatario;
    }

    public void setClienteDestinatario(Cliente clienteDestinatario) {
        this.clienteDestinatario = clienteDestinatario;
    }

    public SucursalCliente getSucursalClienteRem() {
        return sucursalClienteRem;
    }

    public void setSucursalClienteRem(SucursalCliente sucursalClienteRem) {
        this.sucursalClienteRem = sucursalClienteRem;
    }

    public SucursalCliente getSucursalClienteDes() {
        return sucursalClienteDes;
    }

    public void setSucursalClienteDes(SucursalCliente sucursalClienteDes) {
        this.sucursalClienteDes = sucursalClienteDes;
    }

    public CondicionVenta getCondicionVenta() {
        return condicionVenta;
    }

    public void setCondicionVenta(CondicionVenta condicionVenta) {
        this.condicionVenta = condicionVenta;
    }

    public BigDecimal getImporteNoGravado() {
        return importeNoGravado;
    }

    public void setImporteNoGravado(BigDecimal importeNoGravado) {
        this.importeNoGravado = importeNoGravado;
    }

    public BigDecimal getImporteNetoGravado() {
        return importeNetoGravado;
    }

    public void setImporteNetoGravado(BigDecimal importeNetoGravado) {
        this.importeNetoGravado = importeNetoGravado;
    }

    public BigDecimal getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(BigDecimal importeIva) {
        this.importeIva = importeIva;
    }

    public BigDecimal getImporteOtrosTributos() {
        return importeOtrosTributos;
    }

    public void setImporteOtrosTributos(BigDecimal importeOtrosTributos) {
        this.importeOtrosTributos = importeOtrosTributos;
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

    public Cobrador getCobrador() {
        return cobrador;
    }

    public void setCobrador(Cobrador cobrador) {
        this.cobrador = cobrador;
    }

    public boolean isPagoEnOrigen() {
        return pagoEnOrigen;
    }

    public void setPagoEnOrigen(boolean pagoEnOrigen) {
        this.pagoEnOrigen = pagoEnOrigen;
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

    public boolean isEsCAEA() {
        return esCAEA;
    }

    public void setEsCAEA(boolean esCAEA) {
        this.esCAEA = esCAEA;
    }

    public String getCAE() {
        return CAE;
    }

    public void setCAE(String CAE) {
        this.CAE = CAE;
    }

    public Date getCAEVencimiento() {
        return CAEVencimiento;
    }

    public void setCAEVencimiento(Date CAEVencimiento) {
        this.CAEVencimiento = CAEVencimiento;
    }

    public String getCAEEstado() {
        return CAEEstado;
    }

    public void setCAEEstado(String CAEEstado) {
        this.CAEEstado = CAEEstado;
    }

    public Timestamp getFechaRegistracion() {
        return fechaRegistracion;
    }

    public void setFechaRegistracion(Timestamp fechaRegistracion) {
        this.fechaRegistracion = fechaRegistracion;
    }

    public AfipConcepto getAfipConcepto() {
        return afipConcepto;
    }

    public void setAfipConcepto(AfipConcepto afipConcepto) {
        this.afipConcepto = afipConcepto;
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

    public List<VentaComprobanteItemFA> getVentaComprobanteItemFAs() {
        return ventaComprobanteItemFAs;
    }

    public void setVentaComprobanteItemFAs(List<VentaComprobanteItemFA> ventaComprobanteItemFAs) {
        this.ventaComprobanteItemFAs = ventaComprobanteItemFAs;
    }

    public List<VentaComprobanteItemCR> getVentaComprobanteItemCR() {
        return ventaComprobanteItemCR;
    }

    public void setVentaComprobanteItemCR(List<VentaComprobanteItemCR> ventaComprobanteItemCR) {
        this.ventaComprobanteItemCR = ventaComprobanteItemCR;
    }

    public List<VentaComprobanteItemNC> getVentaComprobanteItemNC() {
        return ventaComprobanteItemNC;
    }

    public void setVentaComprobanteItemNC(List<VentaComprobanteItemNC> ventaComprobanteItemNC) {
        this.ventaComprobanteItemNC = ventaComprobanteItemNC;
    }

    public List<VentaComprobanteItemND> getVentaComprobanteItemND() {
        return ventaComprobanteItemND;
    }

    public void setVentaComprobanteItemND(List<VentaComprobanteItemND> ventaComprobanteItemND) {
        this.ventaComprobanteItemND = ventaComprobanteItemND;
    }

    public List<SeguimientoVentaComprobante> getSeguimientoVentaComprobantes() {
        return seguimientoVentaComprobantes;
    }

    public void setSeguimientoVentaComprobantes(List<SeguimientoVentaComprobante> seguimientoVentaComprobantes) {
        this.seguimientoVentaComprobantes = seguimientoVentaComprobantes;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}

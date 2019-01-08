//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase VentaComprobante
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ventacomprobante")
public class VentaComprobante extends ObjetoGenerico {

    //referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Define el punto de venta
    @Column(name = "puntoVenta", nullable = false)
    private int puntoVenta;
    
    //Define la letra
    @Column(name = "letra", nullable = false)
    private String letra;
    
    //Define el numero
    @Column(name = "numero", nullable = false)
    private int numero;
    
    //Referencia a la clase TipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define el codigo de afip
    @Column(name = "codigoAfip", nullable = false)
    private String codigoAfip;
    
    //Define la fecha de emision
    @Column(name = "fechaEmision", nullable = false)
    private LocalDate fechaEmision;
    
    //Define la fecha de vencimiento de pago
    @Column(name = "fechaVtoPago", nullable = false)
    private LocalDate fechaVtoPago;
    
    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    
    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idClienteGrupo", nullable = true)
    private Cliente clienteGrupo;
    
    //Referencia a la clase CondicionIva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipCondicionIva", nullable = false)
    private AfipCondicionIva afipCondicionIva;
    
    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idClienteRemitente", nullable = false)
    private Cliente clienteRemitente;
    
    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idClienteDestinatario", nullable = false)
    private Cliente clienteDestinatario;
    
    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idClienteDestinatarioSuc", nullable = true)
    private Cliente clienteDestinatarioSuc;
    
    //Referencia a la clase CondicionVenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCondicionVenta", nullable = false)
    private CondicionVenta condicionVenta;
    
    //Define el importe no gravado
    @Column(name = "importeNoGravado", nullable = true)
    private BigDecimal importeNoGravado;
    
    //Define el importe exento
    @Column(name = "importeExento", nullable = true)
    private BigDecimal importeExento;
    
    //Define el importe neto gravado
    @Column(name = "importeNetoGravado", nullable = true)
    private BigDecimal importeNetoGravado;
    
    //Define el importe iva
    @Column(name = "importeIva", nullable = true)
    private BigDecimal importeIva;
    
    //Define el importe de otros tributos
    @Column(name = "importeOtrosTributos", nullable = true)
    private BigDecimal importeOtrosTributos;
    
    //Define el importe total
    @Column(name = "importeTotal", nullable = true)
    private BigDecimal importeTotal;
    
    //Define el importe saldo
    @Column(name = "importeSaldo", nullable = true)
    private BigDecimal importeSaldo;
    
    //Referencia a la clase Cobrador
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCobrador", nullable = false)
    private Cobrador cobrador;
    
    //Define si el pago es en origen
    @Column(name = "pagoEnOrigen", nullable = false)
    private boolean pagoEnOrigen;

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

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDate getFechaVtoPago() {
        return fechaVtoPago;
    }

    public void setFechaVtoPago(LocalDate fechaVtoPago) {
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

    public Cliente getClienteDestinatarioSuc() {
        return clienteDestinatarioSuc;
    }

    public void setClienteDestinatarioSuc(Cliente clienteDestinatarioSuc) {
        this.clienteDestinatarioSuc = clienteDestinatarioSuc;
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

    public BigDecimal getImporteExento() {
        return importeExento;
    }

    public void setImporteExento(BigDecimal importeExento) {
        this.importeExento = importeExento;
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
    
}
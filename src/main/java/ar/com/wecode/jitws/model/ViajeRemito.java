//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Viaje Remito
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajeremito")
public class ViajeRemito extends ObjetoGenerico {
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresaEmision", nullable = false)
    private Empresa empresaEmision;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    //Define la fecha
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //Define el numero de camion
    @Column(name = "numeroCamion", nullable = false)
    private short numeroCamion;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursalDestino", nullable = false)
    private Sucursal sucursalDestino;
    
    //Referencia a la clase Afip Comprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipComprobante", nullable = false)
    private AfipComprobante afipComprobante;
    
    //Define el punto de venta
    @Column(name = "puntoVenta", nullable = false)
    private int puntoVenta;
    
    //Define la letra
    @Column(name = "letra", nullable = false)
    private String letra;
    
    //Define el numero
    @Column(name = "numeroComprobante", nullable = false)
    private int numeroComprobante;
    
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
    
    //Define los bultos
    @Column(name = "bultos", nullable = false)
    private short bultos;
    
    //Define los kilos
    @Column(name = "kilos", nullable = true)
    private BigDecimal kilos;
    
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
    
    //Define si esta pendiente
    @Column(name = "estaPendiente", nullable = true)
    private boolean estaPendiente;
    
    //Referencia a la clase Viaje Propio Tramo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajePropioTramo", nullable = true)
    private ViajePropioTramo viajePropioTramo;
    
    //Referencia a la clase Viaje Tercero Tramo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTerceroTramo", nullable = true)
    private ViajeTerceroTramo viajeTerceroTramo;
    
    //Referencia a la clase Reparto Propio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRepartoPropio", nullable = true)
    private RepartoPropio repartoPropio;

    //Referencia a la clase Reparto Tercero
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRepartoTercero", nullable = true)
    private RepartoTercero repartoTercero;
    
    //Define el orden impuesto reparto
    @Column(name = "ordenImpReparto", nullable = true)
    private short ordenImpReparto;
    
    //Referencia a la clase Estado Carga
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoCargo", nullable = true)
    private EstadoCarga estadoCarga;*/
    
    //Define las observaciones
    @Column(name = "observaciones", nullable = true)
    private String observaciones;
    
    //Getters y Setters de la clase

    public Empresa getEmpresaEmision() {
        return empresaEmision;
    }

    public void setEmpresaEmision(Empresa empresaEmision) {
        this.empresaEmision = empresaEmision;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getNumeroCamion() {
        return numeroCamion;
    }

    public void setNumeroCamion(short numeroCamion) {
        this.numeroCamion = numeroCamion;
    }

    public Sucursal getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(Sucursal sucursalDestino) {
        this.sucursalDestino = sucursalDestino;
    }

    public AfipComprobante getAfipComprobante() {
        return afipComprobante;
    }

    public void setAfipComprobante(AfipComprobante afipComprobante) {
        this.afipComprobante = afipComprobante;
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

    public short getBultos() {
        return bultos;
    }

    public void setBultos(short bultos) {
        this.bultos = bultos;
    }

    public BigDecimal getKilos() {
        return kilos;
    }

    public void setKilos(BigDecimal kilos) {
        this.kilos = kilos;
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

    public boolean getEstaPendiente() {
        return estaPendiente;
    }

    public void setEstaPendiente(boolean estaPendiente) {
        this.estaPendiente = estaPendiente;
    }

    public ViajePropioTramo getViajePropioTramo() {
        return viajePropioTramo;
    }

    public void setViajePropioTramo(ViajePropioTramo viajePropioTramo) {
        this.viajePropioTramo = viajePropioTramo;
    }

    public ViajeTerceroTramo getViajeTerceroTramo() {
        return viajeTerceroTramo;
    }

    public void setViajeTerceroTramo(ViajeTerceroTramo viajeTerceroTramo) {
        this.viajeTerceroTramo = viajeTerceroTramo;
    }

    public RepartoPropio getRepartoPropio() {
        return repartoPropio;
    }

    public void setRepartoPropio(RepartoPropio repartoPropio) {
        this.repartoPropio = repartoPropio;
    }

    public RepartoTercero getRepartoTercero() {
        return repartoTercero;
    }

    public void setRepartoTercero(RepartoTercero repartoTercero) {
        this.repartoTercero = repartoTercero;
    }

    public short getOrdenImpReparto() {
        return ordenImpReparto;
    }

    public void setOrdenImpReparto(short ordenImpReparto) {
        this.ordenImpReparto = ordenImpReparto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}
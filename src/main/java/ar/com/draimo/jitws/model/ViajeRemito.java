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
 * Clase Viaje Remito
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajeremito")
public class ViajeRemito extends ObjetoGenerico {
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursalEmision", nullable = false)
    private Sucursal sucursalEmision;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresaEmision", nullable = false)
    private Empresa empresaEmision;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    //Define la fecha
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    
    //Define el numero de camion
    @Column(name = "numeroCamion", nullable = false)
    private short numeroCamion;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursalDestino", nullable = false)
    private Sucursal sucursalDestino;
    
    //Referencia a la clase Tipo Comprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define el punto de venta
    @Column(name = "puntoVenta", nullable = false)
    private int puntoVenta;
    
    //Define la letra
    @Column(name = "letra", nullable = false)
    private String letra;
    
    //Define el numero
    @Column(name = "numero", nullable = false)
    private int numero;
    
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
    
    //Define los kilos efectivo
    @Column(name = "kilosEfectivo", nullable = true)
    private BigDecimal kilosEfectivo;
    
    //Define los kilos aforados
    @Column(name = "kilosAforado", nullable = true)
    private BigDecimal kilosAforado;
    
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
    @Column(name = "estaPendiente", nullable = false)
    private boolean estaPendiente;
    
    //Referencia a la clase Viaje Propio Tramo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajePropioTramo", nullable = true)
    private ViajePropioTramo viajePropioTramo;
    
    //Referencia a la clase Viaje Tercero Tramo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTerceroTramo", nullable = true)
    private ViajeTerceroTramo viajeTerceroTramo;
    
    //Define las observaciones
    @Column(name = "observaciones", nullable = true)
    private String observaciones;
    
    //Define si esta facturado
    @Column(name = "estaFacturado", nullable = false)
    private boolean estaFacturado;
    
    //Referencia a la clase Seguimiento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSeguimiento", nullable = true)
    private Seguimiento siguimiento;
    
    //Define si esta en reparto
    @Column(name = "estaEnReparto", nullable = false)
    private boolean estaEnReparto;
    
    //Define el alias para las busquedas
    @Column(name = "alias", nullable = true)
    private String alias;
    
    //Getters y Setters de la clase

    public Sucursal getSucursalEmision() {
        return sucursalEmision;
    }

    public void setSucursalEmision(Sucursal sucursalEmision) {
        this.sucursalEmision = sucursalEmision;
    }

    public Empresa getEmpresaEmision() {
        return empresaEmision;
    }

    public void setEmpresaEmision(Empresa empresaEmision) {
        this.empresaEmision = empresaEmision;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

    public boolean isEstaPendiente() {
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

    public Seguimiento getSiguimiento() {
        return siguimiento;
    }

    public void setSiguimiento(Seguimiento siguimiento) {
        this.siguimiento = siguimiento;
    }

    public boolean isEstaEnReparto() {
        return estaEnReparto;
    }

    public void setEstaEnReparto(boolean estaEnReparto) {
        this.estaEnReparto = estaEnReparto;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
}
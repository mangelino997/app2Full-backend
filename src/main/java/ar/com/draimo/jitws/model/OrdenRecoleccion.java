//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase OrdenRecoleccion
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ordenrecoleccion")
public class OrdenRecoleccion extends ObjetoGenerico {

    //Referencia a la clase RepartoPropio
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRepartoPropio", nullable = true)
    private RepartoPropio repartoPropio;
    
    //Referencia a la clase RepartoTercero
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRepartoTercero", nullable = true)
    private RepartoTercero repartoTercero;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase Sucursal
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    //Referencia a la clase TipoComprobante
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    
    //Define Domicilio Recoleccion
    @Column(name = "domicilioRecoleccion",length = 60, nullable = false)
    private String domicilioRecoleccion;
    
    //Referencia a la clase Barrio
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBarrioRecoleccion", nullable = true)
    private Barrio barrioRecoleccion;
    
    //Referencia a la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidadRecoleccion", nullable = true)
    private Localidad localidadRecoleccion;
    
    //Define fechaEmision
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fechaEmision", nullable = false)  
    private LocalDateTime fechaEmision;
    
    //Define fechaRecoleccion
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fechaRecoleccion", nullable = false)
    private Date fechaRecoleccion;
    
    //Define horaDesde
    @JsonFormat(pattern = "HH:mm:ss", timezone = "ART")
    @Column(name = "horaDesde", nullable = false)
    private Time horaDesde;
    
    //Define horaHasta
    @JsonFormat(pattern = "HH:mm:ss", timezone = "ART")
    @Column(name = "horaHasta", nullable = false)
    private Time horaHasta;
    
    //Define solicitadoPor
    @Column(name = "solicitadoPor", length = 30, nullable = false)
    private String solicitadoPor;
    
    //Define telefonoContacto
    @Column(name = "telefonoContacto", length = 45, nullable = false)
    private String telefonoContacto;
    
    //Define descripcionCarga
    @Column(name = "descripcionCarga", length = 60, nullable = false)
    private String descripcionCarga;
    
    //Define bultos
    @Column(name = "bultos",nullable = false)
    private short bultos;
    
    //Define el kilosEfectivo
    @Column(name = "kilosEfectivo" ,nullable = true)
    private BigDecimal kilosEfectivo;
    
    //Define el m3
    @Column(name = "m3" ,nullable = true)
    private BigDecimal m3;
    
    //Define el valorDeclarado
    @Column(name = "valorDeclarado", nullable = true)
    private BigDecimal valorDeclarado;
    
    //Referencia a la clase SucursalDestino
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursalDestino", nullable = false)
    private Sucursal sucursalDestino;
    
    //Define el entregaEnDomicilio
    @Column(name = "entregaEnDomicilio", nullable = false)
    private boolean entregaEnDomicilio;
    
    //Define el pagoEnOrigen
    @Column(name = "pagoEnOrigen", nullable = true)
    private boolean pagoEnOrigen;
    
    //Define el estaEnReparto
    @Column(name = "estaEnReparto", nullable = false)
    private boolean estaEnReparto;
    
    //Define observaciones
    @Column(name = "observaciones", length = 60, nullable = true)
    private String observaciones;
    
    //Define alias
    @Column(name = "alias", length = 100, nullable = true)
    private String alias;
    
    //Getters y Setters de la clase

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDomicilioRecoleccion() {
        return domicilioRecoleccion;
    }

    public void setDomicilioRecoleccion(String domicilioRecoleccion) {
        this.domicilioRecoleccion = domicilioRecoleccion;
    }

    public Barrio getBarrioRecoleccion() {
        return barrioRecoleccion;
    }

    public void setBarrioRecoleccion(Barrio barrioRecoleccion) {
        this.barrioRecoleccion = barrioRecoleccion;
    }

    public Localidad getLocalidadRecoleccion() {
        return localidadRecoleccion;
    }

    public void setLocalidadRecoleccion(Localidad localidadRecoleccion) {
        this.localidadRecoleccion = localidadRecoleccion;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaRecoleccion() {
        return fechaRecoleccion;
    }

    public void setFechaRecoleccion(Date fechaRecoleccion) {
        this.fechaRecoleccion = fechaRecoleccion;
    }

    public Time getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(Time horaDesde) {
        this.horaDesde = horaDesde;
    }

    public Time getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(Time horaHasta) {
        this.horaHasta = horaHasta;
    }

    public String getSolicitadoPor() {
        return solicitadoPor;
    }

    public void setSolicitadoPor(String solicitadoPor) {
        this.solicitadoPor = solicitadoPor;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getDescripcionCarga() {
        return descripcionCarga;
    }

    public void setDescripcionCarga(String descripcionCarga) {
        this.descripcionCarga = descripcionCarga;
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

    public Sucursal getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(Sucursal sucursalDestino) {
        this.sucursalDestino = sucursalDestino;
    }

    public boolean isEntregaEnDomicilio() {
        return entregaEnDomicilio;
    }

    public void setEntregaEnDomicilio(boolean entregaEnDomicilio) {
        this.entregaEnDomicilio = entregaEnDomicilio;
    }

    public boolean isPagoEnOrigen() {
        return pagoEnOrigen;
    }

    public void setPagoEnOrigen(boolean pagoEnOrigen) {
        this.pagoEnOrigen = pagoEnOrigen;
    }

    public boolean isEstaEnReparto() {
        return estaEnReparto;
    }

    public void setEstaEnReparto(boolean estaEnReparto) {
        this.estaEnReparto = estaEnReparto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
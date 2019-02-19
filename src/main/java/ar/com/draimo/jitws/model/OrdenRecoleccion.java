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
    
    //Define Domicilio
    @Column(name = "domicilio",length = 60, nullable = false)
    private String domicilio;
    
    //Referencia a la clase Barrio
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBarrio", nullable = true)
    private Barrio barrio;
    
    //Referencia a la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidad", nullable = true)
    private Localidad localidad;
    
    //Define fechaEmision
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fechaEmision", nullable = false)  
    private LocalDateTime fechaEmision;
    
    //Define fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
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
    @Column(name = "entregarEnDomicilio", nullable = false)
    private boolean entregarEnDomicilio;
    
    //Define el pagoEnOrigen
    @Column(name = "pagoEnOrigen", nullable = true)
    private boolean pagoEnOrigen;
    
    //Define el estaEnReparto
    @Column(name = "estaEnReparto", nullable = false)
    private boolean estaEnReparto;
    
    //Define observaciones
    @Column(name = "observaciones", length = 60, nullable = true)
    private String observaciones;
    
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public boolean isEntregarEnDomicilio() {
        return entregarEnDomicilio;
    }

    public void setEntregarEnDomicilio(boolean entregarEnDomicilio) {
        this.entregarEnDomicilio = entregarEnDomicilio;
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

}
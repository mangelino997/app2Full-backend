//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase PersonalAdelanto 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "personaladelanto")
@JsonFilter("personaladelantofiltro")
public class PersonalAdelanto extends ObjetoGenerico {

    //Referencia a la clase empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    //Referencia a la clase sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;

    //Referencia a la clase personal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPersonal", nullable = false)
    private Personal personal;

    //Referencia a la clase tipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;

    //Define Fecha de emision
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaEmision", nullable = false)
    private Date fechaEmision;

    //Define Fecha de vencimiento
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaVto", nullable = false)
    private Date fechaVto;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    //Define cuota
    @Column(name = "cuota", nullable = false)
    private short cuota;

    //Define Totalcuotas
    @Column(name = "totalCuotas", nullable = false)
    private short totalCuotas;

    //Referencia a la clase Usuario(alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;

    //Define observaciones
    @Column(name = "observaciones", length = 60, nullable = true)
    private String observaciones;

    //Define si esta anulado
    @Column(name = "estaAnulado", nullable = true)
    private boolean estaAnulado;

    //Define observaciones anulado
    @Column(name = "observacionesAnulado", length = 60, nullable = true)
    private String observacionesAnulado;

    //Referencia a usuario (modificacion)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;

    //Referencia a usuario (baja)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioBaja", nullable = true)
    private Usuario usuarioBaja;

    //Define numero de lote
    @Column(name = "numeroLote", nullable = true)
    private int numeroLote;

    //Referencia a la clase viaje
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViaje", nullable = true)
    private Viaje viaje;

    //Referencia a la clase reparto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReparto", nullable = true)
    private Reparto reparto;

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

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVto() {
        return fechaVto;
    }

    public void setFechaVto(Date fechaVto) {
        this.fechaVto = fechaVto;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public short getCuota() {
        return cuota;
    }

    public void setCuota(short cuota) {
        this.cuota = cuota;
    }

    public short getTotalCuotas() {
        return totalCuotas;
    }

    public void setTotalCuotas(short totalCuotas) {
        this.totalCuotas = totalCuotas;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isEstaAnulado() {
        return estaAnulado;
    }

    public void setEstaAnulado(boolean estaAnulado) {
        this.estaAnulado = estaAnulado;
    }

    public String getObservacionesAnulado() {
        return observacionesAnulado;
    }

    public void setObservacionesAnulado(String observacionesAnulado) {
        this.observacionesAnulado = observacionesAnulado;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public Usuario getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(Usuario usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(int numeroLote) {
        this.numeroLote = numeroLote;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Reparto getReparto() {
        return reparto;
    }

    public void setReparto(Reparto reparto) {
        this.reparto = reparto;
    }

}
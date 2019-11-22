//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Cobranza
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "cobranza")
public class Cobranza extends ObjetoGenerico {

    //Define referencia a la clase empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    //Define referencia a la clase sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;

    //Define referencia a la clase tipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;

    //Define fecha de Emision
    @Column(name = "fechaEmision", nullable = false)
    private Date fechaEmision;

    //Define fecha de registro
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC-3")
    @Column(name = "fechaRegistracion", nullable = false)
    private Timestamp fechaRegistracion;

    //Define referencia a la clase cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    //Define observaciones
    @Column(name = "observaciones", length = 100, nullable = true)
    private String observaciones;

    //Define referencia a la clase usuario(alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;

    //Define referencia a la clase usuario(baja)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioBaja", nullable = true)
    private Usuario usuarioBaja;

    //Define referencia a la clase usuario(mod)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Define la referencia a cobranzaAnticipo
    @OneToMany
    @JoinTable(
      name = "cobranzamediopago", 
      joinColumns = @JoinColumn(name = "idCobranza"), 
      inverseJoinColumns = @JoinColumn(name = "idCobranzaAnticipo"))
    @JsonIgnoreProperties("cobranza")
    private List<CobranzaAnticipo> cobranzaAnticipo;

    //Define la referencia a efectivo
    @OneToMany
    @JoinTable(
      name = "cobranzamediopago", 
      joinColumns = @JoinColumn(name = "idCobranza"), 
      inverseJoinColumns = @JoinColumn(name = "idEfectivo"))
    @JsonIgnoreProperties("cobranza")
    private List<Efectivo> efectivo;
    
    //Define la referencia a ChequeCartera
    @OneToMany
    @JoinTable(
      name = "cobranzamediopago", 
      joinColumns = @JoinColumn(name = "idCobranza"), 
      inverseJoinColumns = @JoinColumn(name = "idChequeCartera"))
    @JsonIgnoreProperties("cobranza")
    private List<ChequeCartera> chequeCartera;
    
    //Define la referencia a DocumentoCartera
    @OneToMany
    @JoinTable(
      name = "cobranzamediopago", 
      joinColumns = @JoinColumn(name = "idCobranza"), 
      inverseJoinColumns = @JoinColumn(name = "idDocumentoCartera"))
    @JsonIgnoreProperties("cobranza")
    private List<DocumentoCartera> documentoCartera;
    
    //Define la referencia a MonedaCartera
    @OneToMany
    @JoinTable(
      name = "cobranzamediopago", 
      joinColumns = @JoinColumn(name = "idCobranza"), 
      inverseJoinColumns = @JoinColumn(name = "idMonedaCartera"))
    @JsonIgnoreProperties("cobranza")
    private List<MonedaCartera> monedaCartera;
    
    //Define la referencia a LibroBanco
    @OneToMany
    @JoinTable(
      name = "cobranzamediopago", 
      joinColumns = @JoinColumn(name = "idCobranza"), 
      inverseJoinColumns = @JoinColumn(name = "idLibroBanco"))
    @JsonIgnoreProperties("cobranza")
    private List<LibroBanco> libroBanco;
    
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

    public Timestamp getFechaRegistracion() {
        return fechaRegistracion;
    }

    public void setFechaRegistracion(Timestamp fechaRegistracion) {
        this.fechaRegistracion = fechaRegistracion;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Usuario getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(Usuario usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public List<Efectivo> getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(List<Efectivo> efectivo) {
        this.efectivo = efectivo;
    }

    public List<ChequeCartera> getChequeCartera() {
        return chequeCartera;
    }

    public void setChequeCartera(List<ChequeCartera> chequeCartera) {
        this.chequeCartera = chequeCartera;
    }

    public List<DocumentoCartera> getDocumentoCartera() {
        return documentoCartera;
    }

    public void setDocumentoCartera(List<DocumentoCartera> documentoCartera) {
        this.documentoCartera = documentoCartera;
    }

    public List<MonedaCartera> getMonedaCartera() {
        return monedaCartera;
    }

    public void setMonedaCartera(List<MonedaCartera> monedaCartera) {
        this.monedaCartera = monedaCartera;
    }

    public List<LibroBanco> getLibroBanco() {
        return libroBanco;
    }

    public void setLibroBanco(List<LibroBanco> libroBanco) {
        this.libroBanco = libroBanco;
    }

    public List<CobranzaAnticipo> getCobranzaAnticipo() {
        return cobranzaAnticipo;
    }

    public void setCobranzaAnticipo(List<CobranzaAnticipo> cobranzaAnticipo) {
        this.cobranzaAnticipo = cobranzaAnticipo;
    }
    
}

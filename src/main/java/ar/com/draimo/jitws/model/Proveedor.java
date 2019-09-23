//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;
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
 * Clase Proveedor
 * Define el modelo (columnas) de la base de datos.
 * 
 * @author blas
 */

@Entity
@Table(name = "proveedor")
public class Proveedor extends ObjetoGenerico {
    
    //Define la razon social
    @Column(name = "razonSocial",length = 45, nullable = false)
    private String razonSocial;
    
    //Define el nombre de fantasia
    @Column(name = "nombreFantasia", length = 45,nullable = true)
    private String nombreFantasia;
    
    //Define el domicilio
    @Column(name = "domicilio", length = 60, nullable = false)
    private String domicilio;
    
    //Referencia a la clase Barrio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBarrio", nullable = true)
    private Barrio barrio;
    
    //Referencia a la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidad", nullable = false)
    private Localidad localidad;
    
    //Referencia a la clase Tipo de documento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;
    
    //Define el numeroDocumento
    @Column(name = "numeroDocumento",length = 15, nullable = false)
    private String numeroDocumento;
    
    //Define el numero de ingresos brutos
    @Column(name = "numeroIIBB",length = 15, nullable = true)
    private String numeroIIBB;
    
    //Define el sitio web
    @Column(name = "sitioWeb",length = 60, nullable = true)
    private String sitioWeb;
    
    //Define el telefono
    @Column(name = "telefono",length = 45, nullable = true)
    private String telefono;
    
    //Referencia a la clase AfipCondicionIva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipCondicionIva", nullable = false)
    private AfipCondicionIva afipCondicionIva;
    
    //Referencia a la clase Condicion de compra
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCondicionCompra", nullable = true)
    private CondicionCompra condicionCompra;
    
    //Referencia a la clase Usuario (Alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define la fecha de alta de registro
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Referencia a la clase Usuario (Baja)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioBaja", nullable = true)
    private Usuario usuarioBaja;
    
    //Define la fecha de baja de registro
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaBaja", nullable = true)
    private Date fechaBaja;
    
    //Referencia a la clase Usuario (Modificacion)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Define la fecha de ultima modificacion
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaUltimaMod", nullable = true)
    private Date fechaUltimaMod;
    
    //Define las observaciones
    @Column(name = "observaciones",length = 400, nullable = true)
    private String observaciones;
    
    //Define la nota ingreso comprobante
    @Column(name = "notaIngresarComprobante",length = 200, nullable = true)
    private String notaIngresarComprobante;
    
    //Define la nota impresion orden de pago
    @Column(name = "notaImpresionOrdenPago",length = 200, nullable = true)
    private String notaImpresionOrdenPago;
    
    //Referencia a la clase Banco
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBanco", nullable = true)
    private Banco banco;
    
    //Referencia a la clase Tipo cuenta bancaria
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoCuentaBancaria", nullable = true)
    private TipoCuentaBancaria tipoCuentaBancaria;
    
    //Define el numero de cuenta
    @Column(name = "numeroCuenta",length = 20, nullable = true)
    private String numeroCuenta;
    
    //Define el titular de la cuenta
    @Column(name = "titular",length = 45, nullable = true)
    private String titular;
    
    //Define el numero de cbu de la cuenta
    @Column(name = "numeroCBU",length = 22, nullable = true)
    private String numeroCBU;
    
    //Define el alias de cbu de la cuenta
    @Column(name = "aliasCBU",length = 45, nullable = true)
    private String aliasCBU;
    
    //Referencia a la clase Tipo Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoProveedor", nullable = false)
    private TipoProveedor tipoProveedor;
    
    //Define si esta activa
    @Column(name = "estaActiva", nullable = false)
    private boolean estaActiva;
    
    //Define el alias
    @Column(name = "alias",length = 100, nullable = true)
    private String alias;
    
    //Referencia a la clase proveedoriCuentaContable
    @JsonIgnoreProperties("proveedor")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proveedor")
    private List<ProveedorCuentaContable> proveedorCuentasContables;
    
    //Getters y Setters de la clase
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
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

    public String getNumeroIIBB() {
        return numeroIIBB;
    }

    public void setNumeroIIBB(String numeroIIBB) {
        this.numeroIIBB = numeroIIBB;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public AfipCondicionIva getAfipCondicionIva() {
        return afipCondicionIva;
    }

    public void setAfipCondicionIva(AfipCondicionIva afipCondicionIva) {
        this.afipCondicionIva = afipCondicionIva;
    }

    public CondicionCompra getCondicionCompra() {
        return condicionCompra;
    }

    public void setCondicionCompra(CondicionCompra condicionCompra) {
        this.condicionCompra = condicionCompra;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Usuario getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(Usuario usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public Date getFechaUltimaMod() {
        return fechaUltimaMod;
    }

    public void setFechaUltimaMod(Date fechaUltimaMod) {
        this.fechaUltimaMod = fechaUltimaMod;
    }
    
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNotaIngresarComprobante() {
        return notaIngresarComprobante;
    }

    public void setNotaIngresarComprobante(String notaIngresarComprobante) {
        this.notaIngresarComprobante = notaIngresarComprobante;
    }

    public String getNotaImpresionOrdenPago() {
        return notaImpresionOrdenPago;
    }

    public void setNotaImpresionOrdenPago(String notaImpresionOrdenPago) {
        this.notaImpresionOrdenPago = notaImpresionOrdenPago;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public TipoCuentaBancaria getTipoCuentaBancaria() {
        return tipoCuentaBancaria;
    }

    public void setTipoCuentaBancaria(TipoCuentaBancaria tipoCuentaBancaria) {
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumeroCBU() {
        return numeroCBU;
    }

    public void setNumeroCBU(String numeroCBU) {
        this.numeroCBU = numeroCBU;
    }

    public String getAliasCBU() {
        return aliasCBU;
    }

    public void setAliasCBU(String aliasCBU) {
        this.aliasCBU = aliasCBU;
    }

    public TipoProveedor getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(TipoProveedor tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean getEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }

    public List<ProveedorCuentaContable> getProveedorCuentasContables() {
        return proveedorCuentasContables;
    }

    public void setProveedorCuentasContables(List<ProveedorCuentaContable> proveedorCuentasContables) {
        this.proveedorCuentasContables = proveedorCuentasContables;
    }
    
}
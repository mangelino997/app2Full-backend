package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase CuentaBancaria
 * Mapea con la tabla de base de datos
 * @author blas
 */
@Entity
@Table(name = "cuentabancaria")
public class CuentaBancaria extends ObjetoGenerico {

    //Define la referencia a la tabla empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;

    //Define la referencia a la tabla sucursalBanco
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursalBanco", nullable = false)
    private SucursalBanco sucursalBanco;

    //Define la referencia a la tabla TipoCuentaBancaria
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoCuentaBancaria", nullable = false)
    private TipoCuentaBancaria tipoCuentaBancaria;

    //Define el numeroCuenta
    @Column(name = "numeroCuenta", length = 20, nullable = false)
    private String numeroCuenta;

    //Define la referencia a la tabla moneda
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMoneda", nullable = false)
    private Moneda moneda;

    //Define el cbu
    @Column(name = "CBU", length = 22, nullable = true)
    private String cbu;

    //Define el alias del cbu
    @Column(name = "aliasCBU", length = 45, nullable = true)
    private String aliasCBU;

    //Define si esta activa
    @Column(name = "estaActiva", nullable = false)
    private boolean estaActiva;

    //Define la fecha de apertura
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaApertura", nullable = true)
    private Date fechaApertura;

    //Define la fecha de alta
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;

    //Define la referencia a la tabla usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;

    //Define la fecha de baja
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaBaja", nullable = true)
    private Date fechaBaja;

    //Define la referencia a la tabla usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioBaja", nullable = true)
    private Usuario usuarioBaja;

    //Define la fecha de cierre
    @Column(name = "fechaCierre", nullable = true)
    private Date fechaCierre;
    
//    @ManyToMany(cascade = CascadeType.REFRESH)
//    @JoinTable(name = "clientecuentabancaria",
//        joinColumns = @JoinColumn(name = "idCuentaBancaria"),
//        inverseJoinColumns = @JoinColumn(name = "idCliente"),
//         uniqueConstraints={@UniqueConstraint(columnNames={"idCuentaBancaria", "idCliente"})})  
//    @JsonIgnoreProperties("cuentaBancaria")
//    private List<Cliente> clientes = new ArrayList<>();

    //Getters y Setters de la clase
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public SucursalBanco getSucursalBanco() {
        return sucursalBanco;
    }

    public void setSucursalBanco(SucursalBanco sucursalBanco) {
        this.sucursalBanco = sucursalBanco;
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

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getAliasCBU() {
        return aliasCBU;
    }

    public void setAliasCBU(String aliasCBU) {
        this.aliasCBU = aliasCBU;
    }

    public boolean getEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Usuario getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(Usuario usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

}
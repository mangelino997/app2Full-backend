//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase ProveedorCuentaBancaria
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "proveedorcuentabancaria")
public class ProveedorCuentaBancaria extends ObjetoGenerico {

    //Referencia a la clase proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;

    //Define la referencia a la clase sucursalBanco
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursalBanco", nullable = false)
    private SucursalBanco sucursalBanco;

    //Define la referencia a la clase TipoCuentaBancaria
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoCuentaBancaria", nullable = false)
    private TipoCuentaBancaria tipoCuentaBancaria;

    //Define el numeroCuenta
    @Column(name = "numeroCuenta", length = 20, nullable = false)
    private String numeroCuenta;

    //Define el titular
    @Column(name = "titular", length = 45, nullable = true)
    private String titular;

    //Define la referencia a la clase moneda
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMoneda", nullable = false)
    private Moneda moneda;

    //Define el cbu
    @Column(name = "CBU", length = 22, nullable = false)
    private String cbu;

    //Define el alias del cbu
    @Column(name = "aliasCBU", length = 20, nullable = true)
    private String aliasCBU;

    //Define si esta activa
    @Column(name = "estaActiva", nullable = false)
    private boolean estaActiva;

    //Define si esta porDefecto
    @Column(name = "porDefecto", nullable = false)
    private boolean porDefecto;
    
    //Getters y Setters de la clase

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
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

    public boolean isEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }

    public boolean isPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(boolean porDefecto) {
        this.porDefecto = porDefecto;
    }
    
}
//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase AfipComprobante
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "afipcomprobante")
public class AfipComprobante extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define la abreviatura
    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;
    
    //Define la letra
    @Column(name = "letra", nullable = false)
    private String letra;
    
    //Define si esta activo venta
    @Column(name = "estaActivoVenta", nullable = false)
    private boolean estaActivoVenta;
    
    //Define si esta activo compra
    @Column(name = "estaActivoCompra", nullable = false)
    private boolean estaActivoCompra;
    
    //Define si esta activo ingreso carga
    @Column(name = "estaActivoIngresoCarga", nullable = false)
    private boolean estaActivoIngresoCarga;
    
    //Define el codigo afip
    @Column(name = "codigoAfip", nullable = false)
    private String codigoAfip;

    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public boolean getEstaActivoVenta() {
        return estaActivoVenta;
    }

    public void setEstaActivoVenta(boolean estaActivoVenta) {
        this.estaActivoVenta = estaActivoVenta;
    }

    public boolean getEstaActivoCompra() {
        return estaActivoCompra;
    }

    public void setEstaActivoCompra(boolean estaActivoCompra) {
        this.estaActivoCompra = estaActivoCompra;
    }

    public boolean getEstaActivoIngresoCarga() {
        return estaActivoIngresoCarga;
    }

    public void setEstaActivoIngresoCarga(boolean estaActivoIngresoCarga) {
        this.estaActivoIngresoCarga = estaActivoIngresoCarga;
    }

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }
    
}
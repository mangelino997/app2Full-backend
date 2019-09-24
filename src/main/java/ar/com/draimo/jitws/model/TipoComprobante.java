//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Tipo Comprobante
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "tipocomprobante")
public class TipoComprobante extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;
    
    //Define la abreviatura
    @Column(name = "abreviatura",length = 8, nullable = false)
    private String abreviatura;
    
    //Define si esta activo ingreso carga
    @Column(name = "estaActivoIngresoCarga", nullable = false)
    private boolean estaActivoIngresoCarga;
    
    //Define si esta activo venta
    @Column(name = "estaActivoVenta", nullable = false)
    private boolean estaActivoVentaCarga;
    
    //Define si esta activo compra
    @Column(name = "estaActivoCompra", nullable = false)
    private boolean estaActivoCompraCarga;
    
    //Define numeracion punto venta
    @Column(name = "numeracionPuntoVenta", nullable = false)
    private boolean numeracionPuntoVenta;
    
    //Define si esta activo reparto
    @Column(name = "estaActivoReparto")
    private boolean estaActivoReparto;

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

    public boolean getEstaActivoIngresoCarga() {
        return estaActivoIngresoCarga;
    }

    public void setEstaActivoIngresoCarga(boolean estaActivoIngresoCarga) {
        this.estaActivoIngresoCarga = estaActivoIngresoCarga;
    }

    public boolean getEstaActivoVentaCarga() {
        return estaActivoVentaCarga;
    }

    public void setEstaActivoVentaCarga(boolean estaActivoVentaCarga) {
        this.estaActivoVentaCarga = estaActivoVentaCarga;
    }

    public boolean getEstaActivoCompraCarga() {
        return estaActivoCompraCarga;
    }

    public void setEstaActivoCompraCarga(boolean estaActivoCompraCarga) {
        this.estaActivoCompraCarga = estaActivoCompraCarga;
    }

    public boolean getNumeracionPuntoVenta() {
        return numeracionPuntoVenta;
    }

    public void setNumeracionPuntoVenta(boolean numeracionPuntoVenta) {
        this.numeracionPuntoVenta = numeracionPuntoVenta;
    }

    public boolean getEstaActivoReparto() {
        return estaActivoReparto;
    }

    public void setEstaActivoReparto(boolean estaActivoReparto) {
        this.estaActivoReparto = estaActivoReparto;
    }
    
}
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
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define la abreviatura
    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;
    
    //Define esta activo ingreso carga
    @Column(name = "estaActivoIngresoCarga", nullable = false)
    private boolean estaActivoIngresoCarga;
    
    //Define esta activo venta
    @Column(name = "estaActivoVenta", nullable = false)
    private boolean estaActivoVentaCarga;
    
    //Define esta activo compra
    @Column(name = "estaActivoCompra", nullable = false)
    private boolean estaActivoCompraCarga;
    
    //Define numeracion punto venta
    @Column(name = "numeracionPuntoVenta", nullable = false)
    private boolean numeracionPuntoVenta;

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

    public boolean isEstaActivoIngresoCarga() {
        return estaActivoIngresoCarga;
    }

    public void setEstaActivoIngresoCarga(boolean estaActivoIngresoCarga) {
        this.estaActivoIngresoCarga = estaActivoIngresoCarga;
    }

    public boolean isEstaActivoVentaCarga() {
        return estaActivoVentaCarga;
    }

    public void setEstaActivoVentaCarga(boolean estaActivoVentaCarga) {
        this.estaActivoVentaCarga = estaActivoVentaCarga;
    }

    public boolean isEstaActivoCompraCarga() {
        return estaActivoCompraCarga;
    }

    public void setEstaActivoCompraCarga(boolean estaActivoCompraCarga) {
        this.estaActivoCompraCarga = estaActivoCompraCarga;
    }

    public boolean isNumeracionPuntoVenta() {
        return numeracionPuntoVenta;
    }

    public void setNumeracionPuntoVenta(boolean numeracionPuntoVenta) {
        this.numeracionPuntoVenta = numeracionPuntoVenta;
    }
    
}
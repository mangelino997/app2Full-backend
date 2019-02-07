//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Moneda
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "moneda")
public class Moneda extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    //Define si esta activo o no
    @Column(name = "estaActivo", nullable = false)
    private boolean estaActivo;
    
    //Define por defecto
    @Column(name = "porDefecto", nullable = false)
    private boolean porDefecto;
    
    //Define el codigo de afip
    @Column(name = "codigoAfip", nullable = false)
    private String codigoAfip;
    
    //Define el simbolo
    @Column(name = "simbolo", nullable = false)
    private String simbolo;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public boolean isPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(boolean porDefecto) {
        this.porDefecto = porDefecto;
    }

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    
}

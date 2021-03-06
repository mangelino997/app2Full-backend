package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Afip Concepto
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "afipconceptoventa")
public class AfipConceptoVenta extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false, unique = true)
    private String nombre;

    //Define el código Afip
    @Column(name = "codigoAfip", length = 3, nullable = false)
    private String codigoAfip;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }

}

package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Tipo Concepto Sueldo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */
@Entity
@Table(name = "tipoconceptosueldo")
public class TipoConceptoSueldo extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false, unique = true)
    private String nombre;

    //Define abreviatura
    @Column(name = "abreviatura", length = 10, nullable = false, unique = true)
    private String abreviatura;

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

}

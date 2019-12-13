//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase AfipConceptoSueldoGrupo
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "afipconceptosueldogrupo")
public class AfipConceptoSueldoGrupo extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", length = 140, nullable = false)
    private String nombre;

    //Referencia a la clase AfipConceptoSueldoGrupo
    @Column(name = "idTipoConceptoSueldo", nullable = true)
    private TipoConceptoSueldo tipoConceptoSueldo;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoConceptoSueldo getTipoConceptoSueldo() {
        return tipoConceptoSueldo;
    }

    public void setTipoConceptoSueldo(TipoConceptoSueldo tipoConceptoSueldo) {
        this.tipoConceptoSueldo = tipoConceptoSueldo;
    }
    
}

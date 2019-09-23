//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Localidad
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "localidad")
public class Localidad extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Define el codigo postal
    @Column(name = "codigoPostal",length = 10, nullable = true)
    private String codigoPostal;
    
    //Referencia a la clase Provincia
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProvincia", nullable = false)
    private Provincia provincia;
    
    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
}

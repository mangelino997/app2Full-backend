//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Agenda telefonica 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "agendatelefonica")
public class AgendaTelefonica extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    //Define el domicilio
    @Column(name = "domicilio", length = 45, nullable = true)
    private String domicilio;

    //Define el telefono fijo
    @Column(name = "telefonoFijo", length = 45, nullable = true)
    private String telefonoFijo;

    //Define el telefono movil
    @Column(name = "telefonoMovil", length = 45, nullable = true)
    private String telefonoMovil;

    //Define el correo electronico
    @Column(name = "correoelectronico", length = 60, nullable = true, unique = true)
    private String correoelectronico;

    //Referencia a la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidad", nullable = false)
    private Localidad localidad;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

}

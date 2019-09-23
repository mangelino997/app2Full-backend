//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Contacto Compañia seguro 
 * Define el modelo (columnas) de la base de
 * datos.
 *
 * @author blas
 */
@Entity
@Table(name = "contactocompaniaseguro")
public class ContactoCompaniaSeguro extends ObjetoGenerico {

    //Referencia a la clase Compañia seguro
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompaniaSeguro", nullable = false)
    private CompaniaSeguro companiaSeguro;

    //Referencia a la clase Tipo Contacto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoContacto", nullable = false)
    private TipoContacto tipoContacto;

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    //Define el telefono fijo
    @Column(name = "telefonoFijo", length = 45, nullable = true)
    private String telefonoFijo;

    //Define el telefono movil
    @Column(name = "telefonoMovil", length = 45, nullable = true)
    private String telefonoMovil;

    //Define el correo electronico
    @Column(name = "correoelectronico", length = 60, nullable = true)
    private String correoelectronico;

    //Referencia a la clase Usuario (Alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;

    //Referencia a la clase Usuario (Mod)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;

    //Getters y Setters de la clase
    public CompaniaSeguro getCompaniaSeguro() {
        return companiaSeguro;
    }

    public void setCompaniaSeguro(CompaniaSeguro companiaSeguro) {
        this.companiaSeguro = companiaSeguro;
    }

    public TipoContacto getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(TipoContacto tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

}

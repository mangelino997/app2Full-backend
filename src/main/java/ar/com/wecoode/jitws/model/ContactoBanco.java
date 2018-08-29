//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Contacto Banco
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "contactobanco")
public class ContactoBanco extends ObjetoGenerico {
    
    //Referencia a la clase Banco
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursalBanco", nullable = false)
    private SucursalBanco sucursalBanco;
    
    //Referencia a la clase Tipo Contacto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoContacto", nullable = false)
    private TipoContacto tipoContacto;
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define el telefono fijo
    @Column(name = "telefonoFijo", nullable = true)
    private String telefonoFijo;
    
    //Define el telefono movil
    @Column(name = "telefonoMovil", nullable = true)
    private String telefonoMovil;
    
    //Define el correo electronico
    @Column(name = "correoelectronico", nullable = true)
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

    public SucursalBanco getSucursalBanco() {
        return sucursalBanco;
    }

    public void setSucursalBanco(SucursalBanco sucursalBanco) {
        this.sucursalBanco = sucursalBanco;
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

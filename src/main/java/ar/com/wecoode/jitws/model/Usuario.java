package ar.com.wecoode.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.context.annotation.Scope;

/**
 * Clase Usuario
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "usuario")
@Scope("session")
public class Usuario extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define el usuario
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    //Define la contraseña
    @Column(name = "password", nullable = false)
    private String password;
    
    //Referencia a la clase rol
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;
    
    //Define si la cuenta esta habilitada
    @Column(name = "cuentaHabilitada", nullable = false)
    private boolean cuentaHabilitada;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isCuentaHabilitada() {
        return cuentaHabilitada;
    }

    public void setCuentaHabilitada(boolean cuentaHabilitada) {
        this.cuentaHabilitada = cuentaHabilitada;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
}

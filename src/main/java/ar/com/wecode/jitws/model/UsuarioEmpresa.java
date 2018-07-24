//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Usuario Empresa
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "usuarioempresa")
public class UsuarioEmpresa extends ObjetoGenerico {
    
    //Referencia a la clase Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    //Referencia a la clase Empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Define si se muestra
    @Column(name = "mostrar", nullable = false)
    private boolean mostrar;
    
    //Getters y Setters de la clase

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
    
}

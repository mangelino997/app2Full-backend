package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Seguimiento Estado
 * Define el modelo (columnas) de la base de datos
 * @author blas
 */

@Entity
@Table(name = "seguimientoestado")
public class SeguimientoEstado extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false, unique = true)
    private String nombre;
    
    //Define si es entregado
    @Column(name = "esEntregado", nullable = false)
    private boolean esEntregado;
    
    //Define si mostrar el reparto
    @Column(name = "repartoMostrar", nullable = true)
    private boolean repartoMostrar;
    
    //Define los getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsEntregado() {
        return esEntregado;
    }

    public void setEsEntregado(boolean esEntregado) {
        this.esEntregado = esEntregado;
    }

    public boolean isRepartoMostrar() {
        return repartoMostrar;
    }

    public void setRepartoMostrar(boolean repartoMostrar) {
        this.repartoMostrar = repartoMostrar;
    }
    
}

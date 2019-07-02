package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Clase bugimagen
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "bugimagen")
@JsonFilter("filtroImagen")
public class BugImagen extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false)
    private String nombre;
    
    //Define el nombre
    @Column(name = "tipo",length = 10, nullable = false)
    private String tipo;
    
    //Define el nombre
    @Column(name = "tamanio", nullable = false)
    private long tamanio;
    
    //Define los datos
    @Lob
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "datos", nullable = true)
    private byte[] datos;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getTamanio() {
        return tamanio;
    }

    public void setTamanio(long tamanio) {
        this.tamanio = tamanio;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

}

//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Obra social.
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "obrasocial")
public class ObraSocial extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 60, nullable = false, unique = true)
    private String nombre;
    
    //Define el codigo de afip
    @Column(name = "codigoAfip",length = 6, nullable = false)
    private String codigoAfip;
    
    //Define el sitio web
    @Column(name = "sitioWeb",length = 60, nullable = true)
    private String sitioWeb;
    
    //Define el alias
    @Column(name = "alias",length = 100, nullable = true)
    private String alias;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
}

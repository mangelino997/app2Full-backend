//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Clase generica para definir id y version que se utilizaran en todas
 * las clases correspondientes.
 * @author blas
 */

@MappedSuperclass
public class ObjetoGenerico {
    
    //Define el id autoincremental, unico y not null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    
    //Define la version
    @Version
    @Column(name = "version", nullable = false)
    private int version;

    //Getters y Setters de la clase

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
}

//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Sueldo CFG
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "sueldocfg")
public class SueldoCFG extends ObjetoGenerico {
    
    //Define el folio libro sueldos
    @Column(name = "folioLibroSueldos", nullable = true)
    private int folioLibroSueldos;
    
    //Define el smvm
    @Column(name = "smvm", nullable = true)
    private BigDecimal smvm;
    
    //Getters y Setters de la clase

    public int getFolioLibroSueldos() {
        return folioLibroSueldos;
    }

    public void setFolioLibroSueldos(int folioLibroSueldos) {
        this.folioLibroSueldos = folioLibroSueldos;
    }

    public BigDecimal getSmvm() {
        return smvm;
    }

    public void setSmvm(BigDecimal smvm) {
        this.smvm = smvm;
    }
    
}

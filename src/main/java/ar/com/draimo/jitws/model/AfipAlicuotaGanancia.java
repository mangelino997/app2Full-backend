//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Afip Alicuota 
 * Ganancia Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "afipalicuotaganancia")
public class AfipAlicuotaGanancia extends ObjetoGenerico {

    //Define la alicuota
    @Column(name = "alicuota", nullable = false)
    private BigDecimal alicuota;

    //Getters y Setters de la clase
    public BigDecimal getAlicuota() {
        return alicuota;
    }

}

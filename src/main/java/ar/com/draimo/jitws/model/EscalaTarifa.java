//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase EscalaTarifa
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "escalatarifa")
public class EscalaTarifa extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    //Getters y Setters de la clase

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
}
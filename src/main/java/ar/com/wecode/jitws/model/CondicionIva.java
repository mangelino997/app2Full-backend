//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Condicion Iva
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "condicioniva")
public class CondicionIva extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    //Define la abreviatura
    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;
    
    //Define la alicuota
    @Column(name = "alicuota", nullable = false)
    private BigDecimal alicuota;
    
    //Define la sobretasa
    @Column(name = "sobretasa", nullable = false)
    private BigDecimal sobretasa;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public BigDecimal getAlicuota() {
        return alicuota;
    }

    public void setAlicuota(BigDecimal alicuota) {
        this.alicuota = alicuota;
    }

    public BigDecimal getSobretasa() {
        return sobretasa;
    }

    public void setSobretasa(BigDecimal sobretasa) {
        this.sobretasa = sobretasa;
    }
    
}

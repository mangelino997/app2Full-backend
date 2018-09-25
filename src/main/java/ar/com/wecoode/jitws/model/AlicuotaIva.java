//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase AlicuotaIva
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "alicuotaiva")
public class AlicuotaIva extends ObjetoGenerico {

    //Define la alicuota
    @Column(name = "alicuota", nullable = false)
    private BigDecimal alicuota;
    
    //Define el codigo de afip
    @Column(name = "codigoAfip", nullable = false)
    private String codigoAfip;
    
    //Define la alicuota por defecto
    @Column(name = "porDefecto", nullable = false)
    private boolean porDefecto;
    
    //Define si esta activa
    @Column(name = "estaActiva", nullable = true)
    private boolean estaActiva;

    //Getters y Setters de la clase

    public BigDecimal getAlicuota() {
        return alicuota;
    }

    public void setAlicuota(BigDecimal alicuota) {
        this.alicuota = alicuota;
    }

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }

    public boolean isPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(boolean porDefecto) {
        this.porDefecto = porDefecto;
    }

    public boolean isEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }
    
}
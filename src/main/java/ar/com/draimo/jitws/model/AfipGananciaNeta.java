//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase AfipGananciaNeta
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "afipganancianeta")
public class AfipGananciaNeta extends ObjetoGenerico {

    //Define el anio
    @Column(name = "anio", length = 4, nullable = false)
    private short anio;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    //Define el importeFijo
    @Column(name = "importeFijo", nullable = false)
    private BigDecimal importeFijo;

    //Referencia a afiptipobeneficio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipAlicuotaGanancia", nullable = false)
    private AfipAlicuotaGanancia afipAlicuotaGanancia;
    
    //Getters y Setters de la clase

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getImporteFijo() {
        return importeFijo;
    }

    public void setImporteFijo(BigDecimal importeFijo) {
        this.importeFijo = importeFijo;
    }

    public AfipAlicuotaGanancia getAfipAlicuotaGanancia() {
        return afipAlicuotaGanancia;
    }

    public void setAfipAlicuotaGanancia(AfipAlicuotaGanancia afipAlicuotaGanancia) {
        this.afipAlicuotaGanancia = afipAlicuotaGanancia;
    }

}
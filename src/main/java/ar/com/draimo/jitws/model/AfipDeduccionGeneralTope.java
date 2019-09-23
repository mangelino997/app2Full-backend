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
 * Clase AfipDeduccionGeneralTope 
 * Define el modelo (columnas) de la base de
 * datos.
 *
 * @author blas
 */
@Entity
@Table(name = "afipdeducciongeneraltope")
public class AfipDeduccionGeneralTope extends ObjetoGenerico {

    //referencia a afipDeduccionGeneral
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipDeduccionGeneral", nullable = false)
    private AfipDeduccionGeneral afipDeduccionGeneral;

    //Define el anio
    @Column(name = "anio", length = 4, nullable = false)
    private short anio;

    //Define la descripcion
    @Column(name = "descripcion", length = 80, nullable = true)
    private String descripcion;

    //Define el importe
    @Column(name = "importe", nullable = true)
    private BigDecimal importe;

    //Define porcentajeGananciaNeta
    @Column(name = "porcentajeGananciaNeta", nullable = true)
    private BigDecimal porcentajeGananciaNeta;

    //Getters y Setters de la clase
    public AfipDeduccionGeneral getAfipDeduccionGeneral() {
        return afipDeduccionGeneral;
    }

    public void setAfipDeduccionGeneral(AfipDeduccionGeneral afipDeduccionGeneral) {
        this.afipDeduccionGeneral = afipDeduccionGeneral;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getPorcentajeGananciaNeta() {
        return porcentajeGananciaNeta;
    }

    public void setPorcentajeGananciaNeta(BigDecimal porcentajeGananciaNeta) {
        this.porcentajeGananciaNeta = porcentajeGananciaNeta;
    }

}

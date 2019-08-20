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
 * Clase AfipTipoBeneficioDeduccion
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "afiptipobeneficiodeduccion")
public class AfipTipoBeneficioDeduccion extends ObjetoGenerico {

    //Define el anio
    @Column(name = "anio", length = 4, nullable = false)
    private short anio;

    //Referencia a afiptipobeneficio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipTipoBeneficio", nullable = false)
    private AfipTipoBeneficio afipTipoBeneficio;

    //Referencia a afipDeduccionPersonal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipDeduccionPersonal", nullable = false)
    private AfipDeduccionPersonal afipDeduccionPersonal;

    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    //Define el importe anual mensual
    @Column(name = "importeAnualMensual", nullable = false)
    private boolean importeAnualMensual;

    //Referencia a mes
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMes", nullable = true)
    private Mes mes;

    //Getters y Setters de la clase

    public AfipTipoBeneficio getAfipTipoBeneficio() {
        return afipTipoBeneficio;
    }

    public void setAfipTipoBeneficio(AfipTipoBeneficio afipTipoBeneficio) {
        this.afipTipoBeneficio = afipTipoBeneficio;
    }

    public AfipDeduccionPersonal getAfipDeduccionPersonal() {
        return afipDeduccionPersonal;
    }

    public void setAfipDeduccionPersonal(AfipDeduccionPersonal afipDeduccionPersonal) {
        this.afipDeduccionPersonal = afipDeduccionPersonal;
    }

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

    public boolean isImporteAnualMensual() {
        return importeAnualMensual;
    }

    public void setImporteAnualMensual(boolean importeAnualMensual) {
        this.importeAnualMensual = importeAnualMensual;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }
    
}
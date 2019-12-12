//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase ventaTipoItem
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ventatipoitem")
public class VentaTipoItem extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    //Referencia a la clase tipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;

    //Referencia a la clase AfipConcepto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipConcepto", nullable = false)
    private AfipConceptoVenta afipConcepto;

    //Define si esta habilitado
    @Column(name = "estaHabilitado", nullable = false)
    private boolean estaHabilitado;

    //Define si es cheque rechazado
    @Column(name = "esChequeRechazado", nullable = false)
    private boolean esChequeRechazado;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public AfipConceptoVenta getAfipConcepto() {
        return afipConcepto;
    }

    public void setAfipConcepto(AfipConceptoVenta afipConcepto) {
        this.afipConcepto = afipConcepto;
    }

    public boolean getEstaHabilitado() {
        return estaHabilitado;
    }

    public void setEstaHabilitado(boolean estaHabilitado) {
        this.estaHabilitado = estaHabilitado;
    }

    public boolean getEsChequeRechazado() {
        return esChequeRechazado;
    }

    public void setEsChequeRechazado(boolean esChequeRechazado) {
        this.esChequeRechazado = esChequeRechazado;
    }

}
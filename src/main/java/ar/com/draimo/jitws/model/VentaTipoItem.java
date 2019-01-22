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

    //Define si es contra Reembolso
    @Column(name = "esContraReembolso", nullable = false)
    private boolean esContrareembolso;

    //Referencia a la clase AfipConcepto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipConcepto", nullable = false)
    private AfipConcepto afipConcepto;

    //Define si esta habilitado
    @Column(name = "estaHabilitado", nullable = false)
    private boolean estaHabilitado;

    //Define si es cheque rechazado
    @Column(name = "esChequeRechazado", nullable = false)
    private boolean esChequeRechaazado;

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

    public boolean isEsContrareembolso() {
        return esContrareembolso;
    }

    public void setEsContrareembolso(boolean esContrareembolso) {
        this.esContrareembolso = esContrareembolso;
    }

    public AfipConcepto getAfipConcepto() {
        return afipConcepto;
    }

    public void setAfipConcepto(AfipConcepto afipConcepto) {
        this.afipConcepto = afipConcepto;
    }

    public boolean isEstaHabilitado() {
        return estaHabilitado;
    }

    public void setEstaHabilitado(boolean estaHabilitado) {
        this.estaHabilitado = estaHabilitado;
    }

    public boolean isEsChequeRechaazado() {
        return esChequeRechaazado;
    }

    public void setEsChequeRechaazado(boolean esChequeRechaazado) {
        this.esChequeRechaazado = esChequeRechaazado;
    }

}
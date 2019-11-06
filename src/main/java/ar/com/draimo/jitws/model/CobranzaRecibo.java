//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase CobranzaRecibo
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "cobranzarecibo")
public class CobranzaRecibo extends ObjetoGenerico {

    //Define la referencia a la clase cobranza
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCobranza", nullable = true)
    private Cobranza cobranza;

    //Define el numeroRecibo
    @Column(name = "numeroRecibo", length = 8, nullable = false)
    private int numeroRecibo;

    //Define la referencia a la clase talonarioRecibo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTalonarioRecibo", nullable = false)
    private TalonarioRecibo talonarioRecibo;

    //Getters y Setters de la clase

    public Cobranza getCobranza() {
        return cobranza;
    }

    public void setCobranza(Cobranza cobranza) {
        this.cobranza = cobranza;
    }

    public int getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(int numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public TalonarioRecibo getTalonarioRecibo() {
        return talonarioRecibo;
    }

    public void setTalonarioRecibo(TalonarioRecibo talonarioRecibo) {
        this.talonarioRecibo = talonarioRecibo;
    }

}

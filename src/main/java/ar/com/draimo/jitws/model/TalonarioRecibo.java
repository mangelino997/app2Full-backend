//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase TalonarioRecibo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "talonariorecibo")
public class TalonarioRecibo extends ObjetoGenerico {
    
    //Referencia a la clase talonarioReciboLote
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTalonarioReciboLote", nullable=false)
    private TalonarioReciboLote talonarioReciboLote;
    
    //Referencia a la clase talonarioReciboLote
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCobrador", nullable=false)
    private Cobrador cobrador;
    
    //Define el desde
    @Column(name = "desde",length = 8, nullable = false)
    private int desde;
    
    //Define el hasta
    @Column(name = "hasta",length = 8, nullable = false)
    private int hasta;
    
    //Referencia a la clase Usuario - Alta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable=false)
    private Usuario usuarioAlta;
    
    //Define el fechaAlta
        @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Getters y Setters de la clase

    public TalonarioReciboLote getTalonarioReciboLote() {
        return talonarioReciboLote;
    }

    public void setTalonarioReciboLote(TalonarioReciboLote talonarioReciboLote) {
        this.talonarioReciboLote = talonarioReciboLote;
    }

    public Cobrador getCobrador() {
        return cobrador;
    }

    public void setCobrador(Cobrador cobrador) {
        this.cobrador = cobrador;
    }

    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

}

package ar.com.draimo.jitws.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase retiro deposito comprobante
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "retirodepositocomprobante")
public class RetiroDepositoComprobante extends ObjetoGenerico {
    
    //Referencia a la clase RetiroDeposito
//    @ManyToOne(cascade = CascadeType.REFRESH)
    @Column(name = "idRetiroDeposito", nullable = false)
    private int retiroDeposito;
    
    //Referencia a la clase TipoComprobante
//    @ManyToOne(cascade = CascadeType.REFRESH)
    @Column(name = "idTipoComprobante", nullable = false)
    private int tipoComprobante;
    
    //Referencia a la Clase Comprobante
    @Column(name = "idComprobante", length = 15, nullable = false)
    private int comprobante;

    //Define los getters y setters

    public int getRetiroDeposito() {
        return retiroDeposito;
    }

    public void setRetiroDeposito(int retiroDeposito) {
        this.retiroDeposito = retiroDeposito;
    }

    public int getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(int tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public int getComprobante() {
        return comprobante;
    }

    public void setComprobante(int comprobante) {
        this.comprobante = comprobante;
    }
    
}

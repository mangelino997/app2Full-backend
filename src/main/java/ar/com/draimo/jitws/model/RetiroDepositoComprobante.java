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
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRetiroDeposito", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase TipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Referencia a la Clase Comprobante
    @Column(name = "idComprobante", length = 15, nullable = false)
    private int comprobante;
    
    //Referencia a la clase Estado
    @Column(name = "idEstado", length = 15, nullable = false)
    private int estado;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public int getComprobante() {
        return comprobante;
    }

    public void setComprobante(int comprobante) {
        this.comprobante = comprobante;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}

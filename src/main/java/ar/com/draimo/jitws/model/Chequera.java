package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Chequera 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "chequera")
public class Chequera extends ObjetoGenerico {

    //Referencia a la clase cuenta bancaria
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCuentaBancaria", nullable = false)
    private CuentaBancaria cuentaBancaria;

    //Define desde
    @Column(name = "desde", length = 8, nullable = false)
    private int desde;

    //Define hasta
    @Column(name = "hasta", length = 8, nullable = false)
    private int hasta;

    //Referencia a la clase tipo chequera
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoChequera", nullable = false)
    private TipoChequera tipoChequera;

    //Referencia a la clase usuario (alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;

    //Define hasta
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;

    //Getters y Setters de la clase
    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
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

    public TipoChequera getTipoChequera() {
        return tipoChequera;
    }

    public void setTipoChequera(TipoChequera tipoChequera) {
        this.tipoChequera = tipoChequera;
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

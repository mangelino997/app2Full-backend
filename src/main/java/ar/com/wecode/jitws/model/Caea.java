//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Caea
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "caea")
public class Caea extends ObjetoGenerico {

    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Define el periodo
    @Column(name = "periodo", nullable = false)
    private int periodo;
    
    //Define la quincena
    @Column(name = "quincena", nullable = false)
    private short quincena;
    
    //Define el numero caea
    @Column(name = "numeroCAEA", nullable = false)
    private String numeroCAEA;
    
    //Define fecha desde
    @Column(name = "fechaDesde", nullable = false)
    private Date fechaDesde;
    
    //Define fecha hasta
    @Column(name = "fechaHasta", nullable = false)
    private Date fechaHasta;
    
    //Define fecha tope informar
    @Column(name = "fechaTopeInformar", nullable = false)
    private Date fechaTopeInformar;
    
    //Define fecha solicitud
    @Column(name = "fechaSolicitud", nullable = false)
    private Date fechaSolicitud;

    //Getters y Setters de la clase

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public short getQuincena() {
        return quincena;
    }

    public void setQuincena(short quincena) {
        this.quincena = quincena;
    }

    public String getNumeroCAEA() {
        return numeroCAEA;
    }

    public void setNumeroCAEA(String numeroCAEA) {
        this.numeroCAEA = numeroCAEA;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaTopeInformar() {
        return fechaTopeInformar;
    }

    public void setFechaTopeInformar(Date fechaTopeInformar) {
        this.fechaTopeInformar = fechaTopeInformar;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
}
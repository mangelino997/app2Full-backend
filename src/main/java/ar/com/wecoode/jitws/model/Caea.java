//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import java.time.LocalDate;
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
    private LocalDate fechaDesde;
    
    //Define fecha hasta
    @Column(name = "fechaHasta", nullable = false)
    private LocalDate fechaHasta;
    
    //Define fecha tope informar
    @Column(name = "fechaTopeInformar", nullable = false)
    private LocalDate fechaTopeInformar;
    
    //Define fecha solicitud
    @Column(name = "fechaSolicitud", nullable = false)
    private LocalDate fechaSolicitud;

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

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public LocalDate getFechaTopeInformar() {
        return fechaTopeInformar;
    }

    public void setFechaTopeInformar(LocalDate fechaTopeInformar) {
        this.fechaTopeInformar = fechaTopeInformar;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
}
//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase AfipCaea
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "afipcaea")
public class AfipCaea extends ObjetoGenerico {

    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Define el anio
    @Column(name = "anio", nullable = false)
    private short anio;
    
    //Define el mes
    @Column(name = "mes", nullable = false)
    private short mes;
    
    //Define la quincena
    @Column(name = "quincena", nullable = false)
    private short quincena;
    
    //Define el numero caea
    @Column(name = "numeroCAEA",length = 14, nullable = false)
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
    private LocalDateTime fechaSolicitud;

    //Getters y Setters de la clase

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public short getMes() {
        return mes;
    }

    public void setMes(short mes) {
        this.mes = mes;
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

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

}
//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaDesde", nullable = false)
    private Date fechaDesde;
    
    //Define fecha hasta
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaHasta", nullable = false)
    private Date fechaHasta;
    
    //Define fecha tope informar
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaTopeInformar", nullable = false)
    private Date fechaTopeInformar;
    
    //Define fecha solicitud
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC-3")
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

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
}
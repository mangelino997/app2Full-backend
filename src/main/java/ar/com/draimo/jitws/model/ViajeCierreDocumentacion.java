package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Define ViajeCierreDocumentacion
 * @author blas
 */
@Entity
@Table(name = "viajecierredocumentacion")
public class ViajeCierreDocumentacion extends ObjetoGenerico {
    
    //Define la fecha de registracion
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC-3")
    @Column(name = "fechaRegistracion", nullable = false)
    private Timestamp fechaRegistracion;
    
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //Define km inicio
    @Column(name = "kmInicio", nullable = false)
    private int kmInicio;
    
    //Define km final
    @Column(name = "kmFinal", nullable = false)
    private int kmFinal;
    
    //Define km ajuste
    @Column(name = "kmAjuste", nullable = true)
    private int kmAjuste;
    
    //Define el usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define los litros rendidos
    @Column(name = "litrosRendidos", nullable = false)
    private BigDecimal litrosRendidos;
    
    //Getters y Setters de la clase

    public Timestamp getFechaRegistracion() {
        return fechaRegistracion;
    }

    public void setFechaRegistracion(Timestamp fechaRegistracion) {
        this.fechaRegistracion = fechaRegistracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getKmInicio() {
        return kmInicio;
    }

    public void setKmInicio(int kmInicio) {
        this.kmInicio = kmInicio;
    }

    public int getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(int kmFinal) {
        this.kmFinal = kmFinal;
    }

    public int getKmAjuste() {
        return kmAjuste;
    }

    public void setKmAjuste(int kmAjuste) {
        this.kmAjuste = kmAjuste;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public BigDecimal getLitrosRendidos() {
        return litrosRendidos;
    }

    public void setLitrosRendidos(BigDecimal litrosRendidos) {
        this.litrosRendidos = litrosRendidos;
    }
    
}
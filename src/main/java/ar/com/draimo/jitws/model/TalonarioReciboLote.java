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
 * Clase TalonarioReciboLote
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "talonariorecibolote")
public class TalonarioReciboLote extends ObjetoGenerico {
    
    //Referencia a la clase empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable=false)
    private Empresa empresa;
    
    //Define el puntoVenta
    @Column(name = "puntoVenta",length = 5, nullable = false)
    private int puntoVenta;
    
    //Define letra
    @Column(name = "letra",length = 1, nullable = false)
    private String letra;
    
    //Define el desde
    @Column(name = "desde",length = 8, nullable = false)
    private int desde;
    
    //Define el hasta
    @Column(name = "hasta",length = 8, nullable = false)
    private int hasta;
    
    //Define el CAI
    @Column(name = "CAI",length = 14, nullable = true)
    private String cai;
    
    //Define el CAIVencimiento
    @Column(name = "CAIVencimiento", nullable = true)
    private Date caiVencimiento;
    
    //Define el loteEntregado
    @Column(name = "loteEntregado",nullable = false)
    private boolean loteEntregado;
    
    //Referencia a la clase Usuario - Alta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable=false)
    private Usuario UsuarioAlta;
    
    //Define el fechaAlta
    @Column(name = "fechaAlta", nullable = true)
    private Date fechaAlta;
    
    //Getters y Setters de la clase

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(int puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
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

    public String getCai() {
        return cai;
    }

    public void setCai(String cai) {
        this.cai = cai;
    }

    public Date getCaiVencimiento() {
        return caiVencimiento;
    }

    public void setCaiVencimiento(Date caiVencimiento) {
        this.caiVencimiento = caiVencimiento;
    }

    public boolean isLoteEntregado() {
        return loteEntregado;
    }

    public void setLoteEntregado(boolean loteEntregado) {
        this.loteEntregado = loteEntregado;
    }

    public Usuario getUsuarioAlta() {
        return UsuarioAlta;
    }

    public void setUsuarioAlta(Usuario UsuarioAlta) {
        this.UsuarioAlta = UsuarioAlta;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

}
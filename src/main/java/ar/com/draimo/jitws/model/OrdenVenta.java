//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase OrdenVenta
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "ordenventa")
public class OrdenVenta extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false)
    private String nombre;
    
    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;
    
    //Referencia a la clase Vendedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVendedor", nullable = false)
    private Vendedor vendedor;
    
    //Define la fecha de alta
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Define el seguro
    @Column(name = "seguro", nullable = false)
    private BigDecimal seguro;
    
    //Define la comision cr
    @Column(name = "comisionCR", nullable = true)
    private BigDecimal comisionCR;
    
    //Define las observaciones
    @Column(name = "observaciones", length = 200, nullable = true)
    private String observaciones;
    
    //Define si esta activa
    @Column(name = "estaActiva", nullable = false)
    private boolean estaActiva;
    
    //Define la fecha desde que esta activa
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "activaDesde", nullable = true)
    private Date activaDesde;
    
    //Define si es contado
    @Column(name = "esContado", nullable = true)
    private boolean esContado;

    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public BigDecimal getSeguro() {
        return seguro;
    }

    public void setSeguro(BigDecimal seguro) {
        this.seguro = seguro;
    }

    public BigDecimal getComisionCR() {
        return comisionCR;
    }

    public void setComisionCR(BigDecimal comisionCR) {
        this.comisionCR = comisionCR;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean getEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }

    public Date getActivaDesde() {
        return activaDesde;
    }

    public void setActivaDesde(Date activaDesde) {
        this.activaDesde = activaDesde;
    }

    public boolean getEsContado() {
        return esContado;
    }

    public void setEsContado(boolean esContado) {
        this.esContado = esContado;
    }
    
}
//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;

    //Referencia a la clase TipoTarifa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoTarifa", nullable = false)
    private TipoTarifa tipoTarifa;
    
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "activaDesde", nullable = true)
    private Date activaDesde;
    
    //Define la lista de OrdenVentaEscala
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
            property = "id")
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "ordenVenta")
    private List<OrdenVentaEscala> ordenesVentasEscalas;
    
    //Define la lista de OrdenVentaTramo
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
            property = "id")
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "ordenVenta")
    private List<OrdenVentaTramo> ordenesVentasTramos;

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
    
    public TipoTarifa getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(TipoTarifa tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
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

    public boolean isEstaActiva() {
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
    
    public List<OrdenVentaEscala> getOrdenesVentasEscalas() {
        return ordenesVentasEscalas;
    }

    public void setOrdenesVentasEscalas(List<OrdenVentaEscala> ordenesVentasEscalas) {
        this.ordenesVentasEscalas = ordenesVentasEscalas;
    }

    public List<OrdenVentaTramo> getOrdenesVentasTramos() {
        return ordenesVentasTramos;
    }

    public void setOrdenesVentasTramos(List<OrdenVentaTramo> ordenesVentasTramos) {
        this.ordenesVentasTramos = ordenesVentasTramos;
    }
    
}
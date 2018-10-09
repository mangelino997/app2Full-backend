//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    //Referencia a la clase Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCliente", nullable = true)
    private Cliente cliente;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = true)
    private Empresa empresa;
    
    //Referencia a la clase Vendedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVendedor", nullable = false)
    private Vendedor vendedor;
    
    //Define la fecha de alta
    @Column(name = "fechaAlta", nullable = false)
    private LocalDate fechaAlta;
    
    //Referencia a la clase TipoTarifa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoTarifa", nullable = false)
    private TipoTarifa tipoTarifa;
    
    //Define el seguro
    @Column(name = "seguro", nullable = false)
    private BigDecimal seguro;
    
    //Define el aforo
    @Column(name = "aforo", nullable = false)
    private short aforo;
    
    //Define la comision cr
    @Column(name = "comisionCR", nullable = true)
    private BigDecimal comisionCR;
    
    //Define las observaciones
    @Column(name = "observaciones", nullable = true)
    private String observaciones;
    
    //Define si esta activa
    @Column(name = "estaActiva", nullable = false)
    private boolean estaActiva;
    
    //Define la fecha desde que esta activa
    @Column(name = "activaDesde", nullable = true)
    private LocalDate activaDesde;
    
    //Define la lista de OrdenVentaEscala
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "ordenVenta")
    private List<OrdenVentaEscala> ordenesVentasEscalas;
    
    //Define la lista de OrdenVentaTramo
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

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
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

    public short getAforo() {
        return aforo;
    }

    public void setAforo(short aforo) {
        this.aforo = aforo;
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

    public LocalDate getActivaDesde() {
        return activaDesde;
    }

    public void setActivaDesde(LocalDate activaDesde) {
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
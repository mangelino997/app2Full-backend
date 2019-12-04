//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase OrdenVenta 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "ordenventa")
public class OrdenVenta extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

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

    //Define la comision contra reembolso
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

////    Referencia a la clase cliente
//    @ManyToMany(cascade = CascadeType.REFRESH)
//    @JoinTable(name = "clienteordenventa",
//        joinColumns = @JoinColumn(name = "idOrdenVenta"),
//        inverseJoinColumns = @JoinColumn(name = "idCliente"))  
//    @JsonIgnoreProperties("ordenesVentas")
//    private List<Cliente>  clientes;
    
    //Referencia a la clase empresa
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "empresaordenventa",
        joinColumns = @JoinColumn(name = "idOrdenVenta"),
        inverseJoinColumns = @JoinColumn(name = "idEmpresa"))  
    @JsonIgnoreProperties("ordenesVentas")
    private List<Empresa> empresas;
    
    //Define la referencia a tipoTarifa
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(
      name = "ordenventatarifa", 
      joinColumns = @JoinColumn(name = "idOrdenVenta"), 
      inverseJoinColumns = @JoinColumn(name = "idTipoTarifa"))
    @JsonIgnoreProperties("ordenesVentas")
    private List<TipoTarifa> tipoTarifas;
    
    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
//
//    public List<Cliente> getClientes() {
//        return clientes;
//    }
//
//    public void setClientes(List<Cliente> clientes) {
//        this.clientes = clientes;
//    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<TipoTarifa> getTipoTarifas() {
        return tipoTarifas;
    }

    public void setTipoTarifas(List<TipoTarifa> tipoTarifas) {
        this.tipoTarifas = tipoTarifas;
    }

}

//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Viaje Tramo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajetramo")
@JsonFilter("viajetramofiltro")
public class ViajeTramo extends ObjetoGenerico {

    //Referencia a la clase Viaje
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViaje", nullable = false)
    private Viaje viaje;
    
    //Referencia a la clase Tramo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTramo", nullable = false)
    private Tramo tramo;
    
    //Define un numero de orden (ordenamiento de tramos)
    @Column(name = "numeroOrden", nullable = false)
    private short numeroOrden;
    
    //Define una fecha tramo
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaTramo", nullable = false)    
    private Date fechaTramo;
    
    //Define una fecha alta
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Define un km
    @Column(name = "km", nullable = false)
    private short km;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define observaciones
    @Column(name = "observaciones",length = 100, nullable = true)
    private String observaciones;
    
    //Referencia a la clase Viaje Tipo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTipo", nullable = false)
    private ViajeTipo viajeTipo;
    
    //Referencia a la clase Viaje Tipo Carga
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTipoCarga", nullable = false)
    private ViajeTipoCarga viajeTipoCarga;
    
    //Referencia a la clase Viaje Tarifa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTarifa", nullable = false)
    private ViajeTarifa viajeTarifa;
    
    //Referencia a la clase Viaje Unidad Negocio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeUnidadNegocio", nullable = false)
    private ViajeUnidadNegocio viajeUnidadNegocio;
    
    //Define la cantidad
    @Column(name = "cantidad", nullable = false)
    private short cantidad;
    
    //Define el precio unitario
    @Column(name = "precioUnitario", nullable = false)
    private BigDecimal precioUnitario;
    
    //Define el importe
    @Column(name = "importe", nullable = false)
    private BigDecimal importe;
    
    //Define la lista de tramos de clientes
    @OneToMany(mappedBy = "viajeTramo", cascade = CascadeType.REMOVE)
    private List<ViajeTramoCliente> viajeTramoClientes;
    
    //Define la lista de remitos
//    @JsonManagedReference
//    @OneToMany(mappedBy = "viajePropioTramo")
//    private List<ViajeRemitoTramo> viajeRemitoTramos;
    
    //Getters y Setters de la clase

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Tramo getTramo() {
        return tramo;
    }

    public void setTramo(Tramo tramo) {
        this.tramo = tramo;
    }

    public short getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(short numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFechaTramo() {
        return fechaTramo;
    }

    public void setFechaTramo(Date fechaTramo) {
        this.fechaTramo = fechaTramo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public short getKm() {
        return km;
    }

    public void setKm(short km) {
        this.km = km;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ViajeTipo getViajeTipo() {
        return viajeTipo;
    }

    public void setViajeTipo(ViajeTipo viajeTipo) {
        this.viajeTipo = viajeTipo;
    }

    public ViajeTipoCarga getViajeTipoCarga() {
        return viajeTipoCarga;
    }

    public void setViajeTipoCarga(ViajeTipoCarga viajeTipoCarga) {
        this.viajeTipoCarga = viajeTipoCarga;
    }

    public ViajeTarifa getViajeTarifa() {
        return viajeTarifa;
    }

    public void setViajeTarifa(ViajeTarifa viajeTarifa) {
        this.viajeTarifa = viajeTarifa;
    }

    public ViajeUnidadNegocio getViajeUnidadNegocio() {
        return viajeUnidadNegocio;
    }

    public void setViajeUnidadNegocio(ViajeUnidadNegocio viajeUnidadNegocio) {
        this.viajeUnidadNegocio = viajeUnidadNegocio;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public List<ViajeTramoCliente> getViajeTramoClientes() {
        return viajeTramoClientes;
    }

    public void setViajeTramoClientes(List<ViajeTramoCliente> viajeTramoClientes) {
        this.viajeTramoClientes = viajeTramoClientes;
    }

//    public List<ViajeRemitoTramo> getViajeRemitoTramos() {
//        return viajeRemitoTramos;
//    }
//
//    public void setViajeRemitoTramos(List<ViajeRemitoTramo> viajeRemitoTramos) {
//        this.viajeRemitoTramos = viajeRemitoTramos;
//    }

}
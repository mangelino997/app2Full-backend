//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
 * Clase Viaje Tercero
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajetercero")
public class ViajeTercero extends ObjetoGenerico {

    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresaEmision", nullable = false)
    private Empresa empresaEmision;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //Referencia a la clase Vehiculo Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoProveedor", nullable = false)
    private VehiculoProveedor vehiculoProveedor;
    
    //Referencia a la clase Chofer Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idChoferProveedor", nullable = false)
    private ChoferProveedor choferProveedor;
    
    //Define si el remolque es propio
    @Column(name = "esRemolquePropio", nullable = false)
    private boolean esRemolquePropio;
    
    //Referencia a la clase Vehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoRemolque", nullable = true)
    private Vehiculo vehiculoRemolque;
    
    //Referencia a la clase Vehiculo Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoProveedorRemolque", nullable = true)
    private VehiculoProveedor vehiculoProveedorRemolque;
    
    //Referencia a la clase Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;
    
    //Referencia a la clase Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProveedorRemolque", nullable = true)
    private Proveedor proveedorRemolque;
    
    //Referencia a la clase Afip Condicion de Iva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipCondicionIva", nullable = false)
    private AfipCondicionIva afipCondicionIva;
    
    //Define un numero de liquidacion
    @Column(name = "numeroLiquidacion", nullable = true)
    private String numeroLiquidacion;
    
    //Define una fecha de liquidacion
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fechaLiquidacion", nullable = true)
    private Date fechaLiquidacion;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioLiquidacion", nullable = true)
    private Usuario usuarioLiquidacion;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioVehiculoAutorizado", nullable = true)
    private Usuario usuarioVehiculoAutorizado;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioVehiculoRemAutorizado", nullable = true)
    private Usuario usuarioVehiculoRemAutorizado;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioChoferAutorizado", nullable = true)
    private Usuario usuarioChoferAutorizado;
    
    //Define una observacion vehiculo
    @Column(name = "observacionVehiculo", nullable = true)
    private String observacionVehiculo;
    
    //Define una observacion vehiculo remolque
    @Column(name = "observacionVehiculoRemolque", nullable = true)
    private String observacionVehiculoRemolque;
    
    //Define una observacion chofer
    @Column(name = "observacionChofer", nullable = true)
    private String observacionChofer;
    
    //Define una observaciones
    @Column(name = "observaciones", nullable = true)
    private String observaciones;
    
    //Define la lista de tramos
    @JsonManagedReference
    @OneToMany(mappedBy = "viajeTercero")
    private List<ViajeTerceroTramo> viajeTerceroTramos;
    
    //Define la lista de ordenes de combustible
    @JsonManagedReference
    @OneToMany(mappedBy = "viajeTercero")
    private List<ViajeTerceroCombustible> viajeTerceroCombustibles;
    
    //Define la lista de adelantos de efectivo
    @JsonManagedReference
    @OneToMany(mappedBy = "viajeTercero")
    private List<ViajeTerceroEfectivo> viajeTerceroEfectivos;
    
    //Define la lista de ordenes de insumo
    @JsonManagedReference
    @OneToMany(mappedBy = "viajeTercero")
    private List<ViajeTerceroInsumo> viajeTerceroInsumos;

    //Getters y Setters de la clase

    public Empresa getEmpresaEmision() {
        return empresaEmision;
    }

    public void setEmpresaEmision(Empresa empresaEmision) {
        this.empresaEmision = empresaEmision;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public VehiculoProveedor getVehiculoProveedor() {
        return vehiculoProveedor;
    }

    public void setVehiculoProveedor(VehiculoProveedor vehiculoProveedor) {
        this.vehiculoProveedor = vehiculoProveedor;
    }

    public ChoferProveedor getChoferProveedor() {
        return choferProveedor;
    }

    public void setChoferProveedor(ChoferProveedor choferProveedor) {
        this.choferProveedor = choferProveedor;
    }

    public boolean getEsRemolquePropio() {
        return esRemolquePropio;
    }

    public void setEsRemolquePropio(boolean esRemolquePropio) {
        this.esRemolquePropio = esRemolquePropio;
    }
    
    public Vehiculo getVehiculoRemolque() {
        return vehiculoRemolque;
    }

    public void setVehiculoRemolque(Vehiculo vehiculoRemolque) {
        this.vehiculoRemolque = vehiculoRemolque;
    }
    
    public VehiculoProveedor getVehiculoProveedorRemolque() {
        return vehiculoProveedorRemolque;
    }

    public void setVehiculoProveedorRemolque(VehiculoProveedor vehiculoProveedorRemolque) {
        this.vehiculoProveedorRemolque = vehiculoProveedorRemolque;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Proveedor getProveedorRemolque() {
        return proveedorRemolque;
    }

    public void setProveedorRemolque(Proveedor proveedorRemolque) {
        this.proveedorRemolque = proveedorRemolque;
    }

    public AfipCondicionIva getAfipCondicionIva() {
        return afipCondicionIva;
    }

    public void setAfipCondicionIva(AfipCondicionIva afipCondicionIva) {
        this.afipCondicionIva = afipCondicionIva;
    }

    public String getNumeroLiquidacion() {
        return numeroLiquidacion;
    }

    public void setNumeroLiquidacion(String numeroLiquidacion) {
        this.numeroLiquidacion = numeroLiquidacion;
    }

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public Usuario getUsuarioLiquidacion() {
        return usuarioLiquidacion;
    }

    public void setUsuarioLiquidacion(Usuario usuarioLiquidacion) {
        this.usuarioLiquidacion = usuarioLiquidacion;
    }

    public Usuario getUsuarioVehiculoAutorizado() {
        return usuarioVehiculoAutorizado;
    }

    public void setUsuarioVehiculoAutorizado(Usuario usuarioVehiculoAutorizado) {
        this.usuarioVehiculoAutorizado = usuarioVehiculoAutorizado;
    }

    public Usuario getUsuarioVehiculoRemAutorizado() {
        return usuarioVehiculoRemAutorizado;
    }

    public void setUsuarioVehiculoRemAutorizado(Usuario usuarioVehiculoRemAutorizado) {
        this.usuarioVehiculoRemAutorizado = usuarioVehiculoRemAutorizado;
    }

    public Usuario getUsuarioChoferAutorizado() {
        return usuarioChoferAutorizado;
    }

    public void setUsuarioChoferAutorizado(Usuario usuarioChoferAutorizado) {
        this.usuarioChoferAutorizado = usuarioChoferAutorizado;
    }

    public String getObservacionVehiculo() {
        return observacionVehiculo;
    }

    public void setObservacionVehiculo(String observacionVehiculo) {
        this.observacionVehiculo = observacionVehiculo;
    }

    public String getObservacionVehiculoRemolque() {
        return observacionVehiculoRemolque;
    }

    public void setObservacionVehiculoRemolque(String observacionVehiculoRemolque) {
        this.observacionVehiculoRemolque = observacionVehiculoRemolque;
    }

    public String getObservacionChofer() {
        return observacionChofer;
    }

    public void setObservacionChofer(String observacionChofer) {
        this.observacionChofer = observacionChofer;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<ViajeTerceroTramo> getViajeTerceroTramos() {
        return viajeTerceroTramos;
    }

    public void setViajeTerceroTramos(List<ViajeTerceroTramo> viajeTerceroTramos) {
        this.viajeTerceroTramos = viajeTerceroTramos;
    }

    public List<ViajeTerceroCombustible> getViajeTerceroCombustibles() {
        return viajeTerceroCombustibles;
    }

    public void setViajeTerceroCombustibles(List<ViajeTerceroCombustible> viajeTerceroCombustibles) {
        this.viajeTerceroCombustibles = viajeTerceroCombustibles;
    }

    public List<ViajeTerceroEfectivo> getViajeTerceroEfectivos() {
        return viajeTerceroEfectivos;
    }

    public void setViajeTerceroEfectivos(List<ViajeTerceroEfectivo> viajeTerceroEfectivos) {
        this.viajeTerceroEfectivos = viajeTerceroEfectivos;
    }

    public List<ViajeTerceroInsumo> getViajeTerceroInsumos() {
        return viajeTerceroInsumos;
    }

    public void setViajeTerceroInsumos(List<ViajeTerceroInsumo> viajeTerceroInsumos) {
        this.viajeTerceroInsumos = viajeTerceroInsumos;
    }
    
}
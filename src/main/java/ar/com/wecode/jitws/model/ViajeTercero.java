//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpresaEmision", nullable = false)
    private Empresa empresaEmision;
    
    //Referencia a la clase Sucursal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    //Define la fecha
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //Referencia a la clase Vehiculo Proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehiculoProveedor", nullable = false)
    private VehiculoProveedor vehiculoProveedor;
    
    //Referencia a la clase Chofer Proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idChoferProveedor", nullable = false)
    private ChoferProveedor choferProveedor;
    
    //Define si el remolque es propio
    @Column(name = "esRemolquePropio", nullable = false)
    private boolean esRemolquePropio;
    
    //Referencia a la clase Vehiculo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehiculoRemolque", nullable = true)
    private Vehiculo vehiculoRemolque;
    
    //Referencia a la clase Vehiculo Proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehiculoProveedorRemolque", nullable = true)
    private VehiculoProveedor vehiculoProveedorRemolque;
    
    //Referencia a la clase Proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;
    
    //Referencia a la clase Proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedorRemolque", nullable = true)
    private Proveedor proveedorRemolque;
    
    //Referencia a la clase Condicion de Iva
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCondicionIva", nullable = false)
    private CondicionIva condicionIva;
    
    //Define un numero de liquidacion
    @Column(name = "numeroLiquidacion", nullable = true)
    private String numeroLiquidacion;
    
    //Define una fecha de liquidacion
    @Column(name = "fechaLiquidacion", nullable = true)
    private Date fechaLiquidacion;
    
    //Referencia a la clase Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioLiquidacion", nullable = true)
    private Usuario usuarioLiquidacion;
    
    //Referencia a la clase Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioVehiculoAutorizado", nullable = true)
    private Usuario usuarioVehiculoAutorizado;
    
    //Referencia a la clase Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioVehiculoRemAutorizado", nullable = true)
    private Usuario usuarioVehiculoRemAutorizado;
    
    //Referencia a la clase Usuario
    @ManyToOne(fetch = FetchType.LAZY)
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

    public CondicionIva getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(CondicionIva condicionIva) {
        this.condicionIva = condicionIva;
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
    
}
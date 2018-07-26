//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Viaje Propio
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajepropio")
public class ViajePropio extends ObjetoGenerico {

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
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //Referencia a la clase Vehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculo", nullable = false)
    private Vehiculo vehiculo;
    
    //Referencia a la clase Personal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPersonal", nullable = false)
    private Personal personal;
    
    //Define si el remolque es propio
    @Column(name = "esRemolquePropio", nullable = false)
    private boolean esRemolquePropio;
    
    //Referencia a la clase Vehiculo Remolque
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoRemolque", nullable = true)
    private Vehiculo vehiculoRemolque;
    
    //Referencia a la clase Vehiculo Proveedor Remolque
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoProveedorRemolque", nullable = true)
    private VehiculoProveedor vehiculoProveedorRemolque;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase Empresa Remolque
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresaRemolque", nullable = true)
    private Empresa empresaRemolque;
    
    //Referencia a la clase Condicion de Iva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCondicionIva", nullable = false)
    private CondicionIva condicionIva;
    
    //Define un numero de documentacion
    @Column(name = "numeroDocumentacion", nullable = true)
    private int numeroDocumentacion;
    
    //Defina una fecha de documentacion
    @Column(name = "fechaDocumentacion", nullable = true)
    private Date fechaDocumentacion;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioDocumentacion", nullable = true)
    private Usuario usuarioDocumentacion;
    
    //Define un numero de liquidacion
    @Column(name = "numeroLiquidacion", nullable = true)
    private int numeroLiquidacion;
    
    //Define una fecha de liquidacion
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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresaRemolque() {
        return empresaRemolque;
    }

    public void setEmpresaRemolque(Empresa empresaRemolque) {
        this.empresaRemolque = empresaRemolque;
    }

    public CondicionIva getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(CondicionIva condicionIva) {
        this.condicionIva = condicionIva;
    }

    public int getNumeroDocumentacion() {
        return numeroDocumentacion;
    }

    public void setNumeroDocumentacion(int numeroDocumentacion) {
        this.numeroDocumentacion = numeroDocumentacion;
    }

    public Date getFechaDocumentacion() {
        return fechaDocumentacion;
    }

    public void setFechaDocumentacion(Date fechaDocumentacion) {
        this.fechaDocumentacion = fechaDocumentacion;
    }

    public Usuario getUsuarioDocumentacion() {
        return usuarioDocumentacion;
    }

    public void setUsuarioDocumentacion(Usuario usuarioDocumentacion) {
        this.usuarioDocumentacion = usuarioDocumentacion;
    }

    public int getNumeroLiquidacion() {
        return numeroLiquidacion;
    }

    public void setNumeroLiquidacion(int numeroLiquidacion) {
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
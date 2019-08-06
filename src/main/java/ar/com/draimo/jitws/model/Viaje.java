//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;
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
 * Clase Viaje
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */


@Entity
@Table(name = "viaje")
@JsonFilter("viajefiltro")
public class Viaje extends ObjetoGenerico {

    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresaEmision", nullable = false)
    private Empresa empresaEmision;
    
    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    
    //Define si es viaje propio
    @Column(name = "esViajePropio", nullable = false)
    private boolean esViajePropio;
    
    //Referencia a la clase Vehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculo", nullable = true)
    private Vehiculo vehiculo;
    
    //Referencia a la clase Personal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPersonal", nullable = true)
    private Personal personal;
    
    //Referencia a la clase Vehiculo Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoProveedor", nullable = true)
    private VehiculoProveedor vehiculoProveedor;
    
    //Referencia a la clase ChoferProveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idChoferProveedor", nullable = true)
    private ChoferProveedor choferProveedor;
    
    //Referencia a la clase Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProveedor", nullable = true)
    private Proveedor proveedor;
    
    //Referencia a la clase Afip Condicion de Iva Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipCondicionIvaProveedor", nullable = true)
    private AfipCondicionIva afipCondicionIvaProveedor;
    
    //Define si el remolque es propio
    @Column(name = "esRemolquePropio", nullable = false)
    private boolean esRemolquePropio;
    
    //Referencia a la clase Vehiculo Remolque
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoRemolque", nullable = true)
    private Vehiculo vehiculoRemolque;
    
    //Referencia a la clase Vehiculo Remolque Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoRemolqueProveedor", nullable = true)
    private VehiculoProveedor vehiculoRemolqueProveedor;
    
    //Define un numero de documentacion
    @Column(name = "numeroDocumentacion",length = 10, nullable = true)
    private int numeroDocumentacion;
    
    //Defina una fecha de documentacion
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaDocumentacion", nullable = true)
    private Timestamp fechaDocumentacion;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioDocumentacion", nullable = true)
    private Usuario usuarioDocumentacion;
    
    //Define un numero de liquidacion
    @Column(name = "numeroLiquidacion",length = 10, nullable = true)
    private int numeroLiquidacion;
    
    //Define una fecha de liquidacion
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaLiquidacion", nullable = true)
    private Timestamp fechaLiquidacion;
    
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
    @Column(name = "observacionVehiculo",length = 100, nullable = true)
    private String observacionVehiculo;
    
    //Define una observacion vehiculo remolque
    @Column(name = "observacionVehiculoRemolque",length = 100, nullable = true)
    private String observacionVehiculoRemolque;
    
    //Define una observacion chofer
    @Column(name = "observacionChofer",length = 100, nullable = true)
    private String observacionChofer;
    
    //Define una observaciones
    @Column(name = "observaciones",length = 100, nullable = true)
    private String observaciones;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define el alias para las busquedas
    @Column(name = "alias", length = 100, nullable = true)
    private String alias;
    
    //Define la lista de tramos
    @JsonIgnoreProperties("viaje")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
    private List<ViajeTramo> viajeTramos;
    
    //Define la lista de ordenes de combustible
    @JsonIgnoreProperties("viaje")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
    private List<ViajeCombustible> viajeCombustibles;
    
    //Define la lista de adelantos de efectivo
    @JsonIgnoreProperties("viaje")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
    private List<ViajeEfectivo> viajeEfectivos;
    
    //Define la lista de ordenes de insumo
    @JsonIgnoreProperties("viaje")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
    private List<ViajeInsumo> viajeInsumos;
    
    //Define la lista de gastos
    @JsonIgnoreProperties("viaje")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
    private List<ViajeGasto> viajeGastos;
    
    //Define la lista de peajes
    @JsonIgnoreProperties("viaje")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
    private List<ViajePeaje> viajePeajes;

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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public boolean isEsViajePropio() {
        return esViajePropio;
    }

    public void setEsViajePropio(boolean esViajePropio) {
        this.esViajePropio = esViajePropio;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public AfipCondicionIva getAfipCondicionIvaProveedor() {
        return afipCondicionIvaProveedor;
    }

    public void setAfipCondicionIvaProveedor(AfipCondicionIva afipCondicionIvaProveedor) {
        this.afipCondicionIvaProveedor = afipCondicionIvaProveedor;
    }

    public boolean isEsRemolquePropio() {
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

    public VehiculoProveedor getVehiculoRemolqueProveedor() {
        return vehiculoRemolqueProveedor;
    }

    public void setVehiculoRemolqueProveedor(VehiculoProveedor vehiculoRemolqueProveedor) {
        this.vehiculoRemolqueProveedor = vehiculoRemolqueProveedor;
    }

    public int getNumeroDocumentacion() {
        return numeroDocumentacion;
    }

    public void setNumeroDocumentacion(int numeroDocumentacion) {
        this.numeroDocumentacion = numeroDocumentacion;
    }

    public Timestamp getFechaDocumentacion() {
        return fechaDocumentacion;
    }

    public void setFechaDocumentacion(Timestamp fechaDocumentacion) {
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

    public Timestamp getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(Timestamp fechaLiquidacion) {
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

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<ViajeTramo> getViajeTramos() {
        return viajeTramos;
    }

    public void setViajeTramos(List<ViajeTramo> viajeTramos) {
        this.viajeTramos = viajeTramos;
    }

    public List<ViajeCombustible> getViajeCombustibles() {
        return viajeCombustibles;
    }

    public void setViajeCombustibles(List<ViajeCombustible> viajeCombustibles) {
        this.viajeCombustibles = viajeCombustibles;
    }

    public List<ViajeEfectivo> getViajeEfectivos() {
        return viajeEfectivos;
    }

    public void setViajeEfectivos(List<ViajeEfectivo> viajeEfectivos) {
        this.viajeEfectivos = viajeEfectivos;
    }

    public List<ViajeInsumo> getViajeInsumos() {
        return viajeInsumos;
    }

    public void setViajeInsumos(List<ViajeInsumo> viajeInsumos) {
        this.viajeInsumos = viajeInsumos;
    }

    public List<ViajeGasto> getViajeGastos() {
        return viajeGastos;
    }

    public void setViajeGastos(List<ViajeGasto> viajeGastos) {
        this.viajeGastos = viajeGastos;
    }

    public List<ViajePeaje> getViajePeajes() {
        return viajePeajes;
    }

    public void setViajePeajes(List<ViajePeaje> viajePeajes) {
        this.viajePeajes = viajePeajes;
    }
    
}
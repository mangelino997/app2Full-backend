//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Reparto
 * Define el modelo (columnas) de la base de datos.
 * 
 * @author blas
 */

@Entity
@Table(name = "reparto")
public class Reparto extends ObjetoGenerico {
    
    //Referencia a la clase empresa (emision)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresaEmision", nullable = false)
    private Empresa empresaEmision;
    
    //Referencia a la clase sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase tipoComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoComprobante", nullable = false)
    private TipoComprobante tipoComprobante;
    
    //Define fechaRegistracion
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC-3")
    @Column(name = "fechaRegistracion", nullable = false)  
    private Timestamp fechaRegistracion;
    
    //Define fechaSalida
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaSalida", nullable = true)
    private Date fechaSalida;
    
    //Define horaSalida
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "horaSalida", nullable = true)
    private Time horaSalida;
    
    //Referencia a la clase vehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculo", nullable = true)
    private Vehiculo vehiculo;
    
    //Referencia a la clase vehiculo (remolque)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoRemolque", nullable = true)
    private Vehiculo vehiculoRemolque;
    
    //Referencia a la clase Personal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPersonal", nullable = true)
    private Personal personal;
    
    //Referencia a la clase Zona
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idZona", nullable = false)
    private Zona zona;
    
    //Define observaciones
    @Column(name = "observaciones", length = 100, nullable = true)
    private String observaciones;
    
    //Referencia a la clase usuario (alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define si esta cerrada
    @Column(name = "estaCerrada", nullable = false)
    private boolean estaCerrada;
    
    //Define fecha regreso
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaRegreso", nullable = true)
    private Date fechaRegreso;
    
    //Define horaRegreso
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC-3")
    @Column(name = "horaRegreso", nullable = true)
    private Time horaRegreso;
    
    //Define si es reparto propio
    @Column(name = "esRepartoPropio", nullable = false)
    private boolean esRepartoPropio;
    
    //Referencia a la clase VehiculoProveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoProveedor", nullable = true)
    private VehiculoProveedor vehiculoProveedor;
    
    //Referencia a la clase VehiculoProveedor (remolque)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoRemolqueProveedor", nullable = true)
    private VehiculoProveedor vehiculoRemolqueProveedor;
    
    //Referencia a la clase ChoferProveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idChoferProveedor", nullable = true)
    private ChoferProveedor choferProveedor;
    
    //Referencia a la clase Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProveedor", nullable = true)
    private Proveedor proveedor;
    
    //Referencia a la clase AfipCondicionIva (proveedor)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipCondicionIvaProveedor", nullable = true)
    private AfipCondicionIva afipCondicionIvaProveedor;
    
    //Referencia a la clase Usuario (modificacion)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Define referencia a personal (acompaniantes)
    @JsonManagedReference
    @JsonIgnoreProperties("reparto")
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy="reparto")
    private List<RepartoPersonal> acompaniantes;
    
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

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Timestamp getFechaRegistracion() {
        return fechaRegistracion;
    }

    public void setFechaRegistracion(Timestamp fechaRegistracion) {
        this.fechaRegistracion = fechaRegistracion;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Vehiculo getVehiculoRemolque() {
        return vehiculoRemolque;
    }

    public void setVehiculoRemolque(Vehiculo vehiculoRemolque) {
        this.vehiculoRemolque = vehiculoRemolque;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
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

    public boolean getEstaCerrada() {
        return estaCerrada;
    }

    public void setEstaCerrada(boolean estaCerrada) {
        this.estaCerrada = estaCerrada;
    }

    public Date getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(Date fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public Time getHoraRegreso() {
        return horaRegreso;
    }

    public void setHoraRegreso(Time horaRegreso) {
        this.horaRegreso = horaRegreso;
    }

    public boolean isEsRepartoPropio() {
        return esRepartoPropio;
    }

    public void setEsRepartoPropio(boolean esRepartoPropio) {
        this.esRepartoPropio = esRepartoPropio;
    }

    public VehiculoProveedor getVehiculoProveedor() {
        return vehiculoProveedor;
    }

    public void setVehiculoProveedor(VehiculoProveedor vehiculoProveedor) {
        this.vehiculoProveedor = vehiculoProveedor;
    }

    public VehiculoProveedor getVehiculoRemolqueProveedor() {
        return vehiculoRemolqueProveedor;
    }

    public void setVehiculoRemolqueProveedor(VehiculoProveedor vehiculoRemolqueProveedor) {
        this.vehiculoRemolqueProveedor = vehiculoRemolqueProveedor;
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

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public List<RepartoPersonal> getAcompaniantes() {
        return acompaniantes;
    }

    public void setAcompaniantes(List<RepartoPersonal> acompaniantes) {
        this.acompaniantes = acompaniantes;
    }
    
}
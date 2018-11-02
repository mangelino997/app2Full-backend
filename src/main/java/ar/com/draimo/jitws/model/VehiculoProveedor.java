//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Vehiculo Proveedor
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "vehiculoproveedor")
public class VehiculoProveedor extends ObjetoGenerico {
    
    //Define el dominio
    @Column(name = "dominio", nullable = false)
    private String dominio;
    
    //Referencia a la clase Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;
    
    //Referencia a la clase TipoVehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoVehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;
    
    //Referencia a la clase MarcaVehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMarcaVehiculo", nullable = false)
    private MarcaVehiculo marcaVehiculo;
    
    //Referencia a la clase ChoferProveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idChoferProveedor", nullable = true)
    private ChoferProveedor choferProveedor;
    
    //Referencia a la clase VehiculoProveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoRemolque", nullable = true)
    private VehiculoProveedor vehiculoRemolque;
    
    //Define el año de fabricacion
    @Column(name = "anioFabricacion", nullable = false)
    private short anioFabricacion;
    
    //Define el numero de motor
    @Column(name = "numeroMotor", nullable = true)
    private String numeroMotor;
    
    //Define el numero de chasis
    @Column(name = "numeroChasis", nullable = true)
    private String numeroChasis;
    
    //Referencia a la clase Compañia de seguro
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompaniaSeguro", nullable = false)
    private CompaniaSeguro companiaSeguro;
    
    //Define el numero de poliza
    @Column(name = "numeroPoliza", nullable = false)
    private String numeroPoliza;
    
    //Define el vencimiento de la poliza
    @Column(name = "vtoPoliza", nullable = false)
    private LocalDate vtoPoliza;
    
    //Define el vencimiento del rto
    @Column(name = "vtoRTO", nullable = false)
    private LocalDate vtoRTO;
    
    //Define el numero de ruta
    @Column(name = "numeroRuta", nullable = false)
    private String numeroRuta;
    
    //Define el vencimiento de ruta
    @Column(name = "vtoRuta", nullable = false)
    private LocalDate vtoRuta;
    
    //Define el vencimiento de senasa
    @Column(name = "vtoSenasa", nullable = true)
    private LocalDate vtoSenasa;
    
    //Define el vencimiento de habilitacion bromatologia
    @Column(name = "vtoHabBromatologica", nullable = true)
    private LocalDate vtoHabBromatologica;
    
    //Referencia a la clase Usuario (Alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define la fecha de alta
    @Column(name = "fechaAlta", nullable = true)
    private LocalDate fechaAlta;
    
    //Referencia a la clase Usuario (Baja)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioBaja", nullable = true)
    private Usuario usuarioBaja;
    
    //Define la fecha de baja
    @Column(name = "fechaBaja", nullable = true)
    private LocalDate fechaBaja;
    
    //Referencia a la clase Usuario (Modificacion)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Define la fecha de ultima modificacion
    @Column(name = "fechaUltimaMod", nullable = true)
    private LocalDate fechaUltimaMod;
    
    //Define el alias
    @Column(name = "alias", nullable = true)
    private String alias;
    
    //Getters y Setters de la clase

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public MarcaVehiculo getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(MarcaVehiculo marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public ChoferProveedor getChoferProveedor() {
        return choferProveedor;
    }

    public void setChoferProveedor(ChoferProveedor choferProveedor) {
        this.choferProveedor = choferProveedor;
    }
    
    public VehiculoProveedor getVehiculoRemolque() {
        return vehiculoRemolque;
    }

    public void setVehiculoRemolque(VehiculoProveedor vehiculoRemolque) {
        this.vehiculoRemolque = vehiculoRemolque;
    }

    public short getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(short anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }
    
    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public CompaniaSeguro getCompaniaSeguro() {
        return companiaSeguro;
    }

    public void setCompaniaSeguro(CompaniaSeguro companiaSeguro) {
        this.companiaSeguro = companiaSeguro;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public LocalDate getVtoPoliza() {
        return vtoPoliza;
    }

    public void setVtoPoliza(LocalDate vtoPoliza) {
        this.vtoPoliza = vtoPoliza;
    }

    public LocalDate getVtoRTO() {
        return vtoRTO;
    }

    public void setVtoRTO(LocalDate vtoRTO) {
        this.vtoRTO = vtoRTO;
    }

    public String getNumeroRuta() {
        return numeroRuta;
    }

    public void setNumeroRuta(String numeroRuta) {
        this.numeroRuta = numeroRuta;
    }

    public LocalDate getVtoRuta() {
        return vtoRuta;
    }

    public void setVtoRuta(LocalDate vtoRuta) {
        this.vtoRuta = vtoRuta;
    }

    public LocalDate getVtoSenasa() {
        return vtoSenasa;
    }

    public void setVtoSenasa(LocalDate vtoSenasa) {
        this.vtoSenasa = vtoSenasa;
    }

    public LocalDate getVtoHabBromatologica() {
        return vtoHabBromatologica;
    }

    public void setVtoHabBromatologica(LocalDate vtoHabBromatologica) {
        this.vtoHabBromatologica = vtoHabBromatologica;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Usuario getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(Usuario usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public LocalDate getFechaUltimaMod() {
        return fechaUltimaMod;
    }

    public void setFechaUltimaMod(LocalDate fechaUltimaMod) {
        this.fechaUltimaMod = fechaUltimaMod;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
}
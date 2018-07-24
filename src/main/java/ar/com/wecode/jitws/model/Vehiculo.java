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
 * Clase Vehiculo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "vehiculo")
public class Vehiculo extends ObjetoGenerico {
    
    //Referencia a la clase Configuracion Vehiculo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConfiguracionVehiculo", nullable = false)
    private ConfiguracionVehiculo configuracionVehiculo;
    
    //Define el dominio
    @Column(name = "dominio", nullable = false)
    private String dominio;
    
    //Define el numero interno
    @Column(name = "numeroInterno", nullable = true)
    private String numeroInterno;
    
    //Referencia a la clase Localidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLocalidad", nullable = false)
    private Localidad localidad;
    
    //Define el año de fabricacion
    @Column(name = "anioFabricacion", nullable = false)
    private short anioFabricacion;
    
    //Define el numero de motor
    @Column(name = "numeroMotor", nullable = true)
    private String numeroMotor;
    
    //Define el numero de chasis
    @Column(name = "numeroChasis", nullable = true)
    private String numeroChasis;
    
    //Referencia a la clase Empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase Personal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonal", nullable = true)
    private Personal personal;
    
    //Referencia a la clase Vehiculo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehiculoRemolque", nullable = true)
    private Vehiculo vehiculoRemolque;
    
    //Referencia a la clase Compañia de seguro
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCompaniaSeguro", nullable = false)
    private CompaniaSeguro companiaSeguro;
    
    //Define el numero de poliza
    @Column(name = "numeroPoliza", nullable = false)
    private String numeroPoliza;
    
    //Define el vencimiento de la poliza
    @Column(name = "vtoPoliza", nullable = false)
    private Date vtoPoliza;
    
    //Define el vencimiento del rto
    @Column(name = "vtoRTO", nullable = false)
    private Date vtoRTO;
    
    //Define el numero de ruta
    @Column(name = "numeroRuta", nullable = false)
    private String numeroRuta;
    
    //Define el vencimiento de ruta
    @Column(name = "vtoRuta", nullable = false)
    private Date vtoRuta;
    
    //Define el vencimiento de senasa
    @Column(name = "vtoSenasa", nullable = true)
    private Date vtoSenasa;
    
    //Define el vencimiento de habilitacion bromatologia
    @Column(name = "vtoHabBromatologica", nullable = true)
    private Date vtoHabBromatologica;
    
    //Referencia a la clase Usuario (Alta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define la fecha de alta
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Referencia a la clase Usuario (Baja)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioBaja", nullable = true)
    private Usuario usuarioBaja;
    
    //Define la fecha de baja
    @Column(name = "fechaBaja", nullable = true)
    private Date fechaBaja;
    
    //Referencia a la clase Usuario (Modificacion)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Define la fecha de ultima modificacion
    @Column(name = "fechaUltimaMod", nullable = true)
    private Date fechaUltimaMod;
    
    //Getters y Setters de la clase

    public ConfiguracionVehiculo getConfiguracionVehiculo() {
        return configuracionVehiculo;
    }

    public void setConfiguracionVehiculo(ConfiguracionVehiculo configuracionVehiculo) {
        this.configuracionVehiculo = configuracionVehiculo;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getNumeroInterno() {
        return numeroInterno;
    }

    public void setNumeroInterno(String numeroInterno) {
        this.numeroInterno = numeroInterno;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Vehiculo getVehiculoRemolque() {
        return vehiculoRemolque;
    }

    public void setVehiculoRemolque(Vehiculo vehiculoRemolque) {
        this.vehiculoRemolque = vehiculoRemolque;
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

    public Date getVtoPoliza() {
        return vtoPoliza;
    }

    public void setVtoPoliza(Date vtoPoliza) {
        this.vtoPoliza = vtoPoliza;
    }

    public Date getVtoRTO() {
        return vtoRTO;
    }

    public void setVtoRTO(Date vtoRTO) {
        this.vtoRTO = vtoRTO;
    }

    public String getNumeroRuta() {
        return numeroRuta;
    }

    public void setNumeroRuta(String numeroRuta) {
        this.numeroRuta = numeroRuta;
    }

    public Date getVtoRuta() {
        return vtoRuta;
    }

    public void setVtoRuta(Date vtoRuta) {
        this.vtoRuta = vtoRuta;
    }

    public Date getVtoSenasa() {
        return vtoSenasa;
    }

    public void setVtoSenasa(Date vtoSenasa) {
        this.vtoSenasa = vtoSenasa;
    }

    public Date getVtoHabBromatologica() {
        return vtoHabBromatologica;
    }

    public void setVtoHabBromatologica(Date vtoHabBromatologica) {
        this.vtoHabBromatologica = vtoHabBromatologica;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Usuario getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(Usuario usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public Date getFechaUltimaMod() {
        return fechaUltimaMod;
    }

    public void setFechaUltimaMod(Date fechaUltimaMod) {
        this.fechaUltimaMod = fechaUltimaMod;
    }
    
}

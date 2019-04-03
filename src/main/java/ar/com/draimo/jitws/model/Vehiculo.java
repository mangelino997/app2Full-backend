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
 * Clase Vehiculo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "vehiculo")
public class Vehiculo extends ObjetoGenerico {
    
    //Referencia a la clase Configuracion Vehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idConfiguracionVehiculo", nullable = false)
    private ConfiguracionVehiculo configuracionVehiculo;
    
    //Define el dominio
    @Column(name = "dominio",length = 10, nullable = false)
    private String dominio;
    
    //Define el numero interno
    @Column(name = "numeroInterno",length = 5, nullable = true)
    private String numeroInterno;
    
    //Referencia a la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidad", nullable = false)
    private Localidad localidad;
    
    //Define el año de fabricacion
    @Column(name = "anioFabricacion", nullable = false)
    private short anioFabricacion;
    
    //Define el numero de motor
    @Column(name = "numeroMotor",length = 25, nullable = true)
    private String numeroMotor;
    
    //Define el numero de chasis
    @Column(name = "numeroChasis",length = 25, nullable = true)
    private String numeroChasis;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Referencia a la clase Personal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPersonal", nullable = true)
    private Personal personal;
    
    //Referencia a la clase Vehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoRemolque", nullable = true)
    private Vehiculo vehiculoRemolque;
    
    //Referencia a la clase Compañia de seguro
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompaniaSeguroPoliza", nullable = false)
    private CompaniaSeguroPoliza companiaSeguroPoliza;
    
    //Define el vencimiento del rto
    @Column(name = "vtoRTO", nullable = false) 
    private LocalDate vtoRTO;
    
    //Define el numero de ruta
    @Column(name = "numeroRuta",length = 15, nullable = false)
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
    @Column(name = "fechaAlta", nullable = false)
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
    @Column(name = "alias",length = 100, nullable = true)
    private String alias;
    
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

    public CompaniaSeguroPoliza getCompaniaSeguroPoliza() {
        return companiaSeguroPoliza;
    }

    public void setCompaniaSeguroPoliza(CompaniaSeguroPoliza companiaSeguroPoliza) {
        this.companiaSeguroPoliza = companiaSeguroPoliza;
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

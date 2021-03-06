//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
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
    
    //Referencia a la clase Vehiculo (remolque)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVehiculoRemolque", nullable = true)
    private Vehiculo vehiculoRemolque;
    
    //Referencia a la clase Compañia de seguro poliza
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompaniaSeguroPoliza", nullable = false)
    private CompaniaSeguroPoliza companiaSeguroPoliza;
    
    //Define el vencimiento del rto
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "vtoRTO", nullable = false) 
    private Date vtoRTO;
    
    //Define el vencimiento de ruta
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "vtoRuta", nullable = false)
    private Date vtoRuta;
    
    //Define el vencimiento de senasa
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "vtoSenasa", nullable = true)
    private Date vtoSenasa;
    
    //Define el vencimiento de habilitacion bromatologia
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "vtoHabBromatologica", nullable = true)
    private Date vtoHabBromatologica;
    
    //Referencia a la clase Usuario (Alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define la fecha de alta
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Referencia a la clase Usuario (Baja)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioBaja", nullable = true)
    private Usuario usuarioBaja;
    
    //Define la fecha de baja
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaBaja", nullable = true)
    private Date fechaBaja;
    
    //Referencia a la clase Usuario (Modificacion)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Define la fecha de ultima modificacion
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaUltimaMod", nullable = true)
    private Date fechaUltimaMod;
    
    //Define el alias
    @Column(name = "alias",length = 100, nullable = true)
    private String alias;
    
    //Referencia a la clase pdf (titulo)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPdfTitulo", nullable = true)
    private Pdf pdfTitulo;
    
    //Referencia a la clase pdf (CedulaIdent)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPdfCedulaIdent", nullable = true)
    private Pdf pdfCedulaIdent;
    
    //Referencia a la clase pdf (VtoRuta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPdfVtoRuta", nullable = true)
    private Pdf pdfVtoRuta;
    
    //Referencia a la clase pdf (VtoInspTecnica)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPdfVtoInspTecnica", nullable = true)
    private Pdf pdfVtoInspTecnica;
    
    //Referencia a la clase pdf (VtoSenasa)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPdfVtoSenasa", nullable = true)
    private Pdf pdfVtoSenasa;
    
    //Referencia a la clase pdf (HabBromat)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPdfHabBromat", nullable = true)
    private Pdf pdfHabBromat;
    
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

    public Date getVtoRTO() {
        return vtoRTO;
    }

    public void setVtoRTO(Date vtoRTO) {
        this.vtoRTO = vtoRTO;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Pdf getPdfTitulo() {
        return pdfTitulo;
    }

    public void setPdfTitulo(Pdf pdfTitulo) {
        this.pdfTitulo = pdfTitulo;
    }

    public Pdf getPdfCedulaIdent() {
        return pdfCedulaIdent;
    }

    public void setPdfCedulaIdent(Pdf pdfCedulaIdent) {
        this.pdfCedulaIdent = pdfCedulaIdent;
    }

    public Pdf getPdfVtoRuta() {
        return pdfVtoRuta;
    }

    public void setPdfVtoRuta(Pdf pdfVtoRuta) {
        this.pdfVtoRuta = pdfVtoRuta;
    }

    public Pdf getPdfVtoInspTecnica() {
        return pdfVtoInspTecnica;
    }

    public void setPdfVtoInspTecnica(Pdf pdfVtoInspTecnica) {
        this.pdfVtoInspTecnica = pdfVtoInspTecnica;
    }

    public Pdf getPdfVtoSenasa() {
        return pdfVtoSenasa;
    }

    public void setPdfVtoSenasa(Pdf pdfVtoSenasa) {
        this.pdfVtoSenasa = pdfVtoSenasa;
    }

    public Pdf getPdfHabBromat() {
        return pdfHabBromat;
    }

    public void setPdfHabBromat(Pdf pdfHabBromat) {
        this.pdfHabBromat = pdfHabBromat;
    }
    
}
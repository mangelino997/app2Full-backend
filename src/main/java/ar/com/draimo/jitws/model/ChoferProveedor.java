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
 * Clase Chofer Proveedor
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "choferproveedor")
public class ChoferProveedor extends ObjetoGenerico {
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false)
    private String nombre;
    
    //Define el domicilio
    @Column(name = "domicilio",length = 60, nullable = false)
    private String domicilio;
    
    //Referencia a la clase Proveedor
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;
    
    //Referencia a la clase Barrio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBarrio", nullable = true)
    private Barrio barrio;
    
    //Referencia a la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidad", nullable = false)
    private Localidad localidad;
    
    //Referencia a la clase Tipo de documento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;
    
    //Define el numero de documento
    @Column(name = "numeroDocumento", nullable = false)
    private String numeroDocumento;
    
    //Define la fecha de nacimiento
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fechaNacimiento", nullable = false)
    private Date fechaNacimiento;
    
    //Define el telefono fijo
    @Column(name = "telefonoFijo", nullable = true)
    private String telefonoFijo;
    
    //Define el telefono movil
    @Column(name = "telefonoMovil", nullable = true)
    private String telefonoMovil;
    
    //Define el vencimiento del carnet
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "vtoCarnet", nullable = false)
    private Date vtoCarnet;
    
    //Define el vencimiento del curso de un chofer
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "vtoCurso", nullable = false)
    private Date vtoCurso;
    
    //Define el vencimiento de LNH
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "vtoLNH", nullable = false)
    private Date vtoLNH;
    
    //Define el vencimiento de la libreta de sanidad
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "vtoLibretaSanidad", nullable = true)
    private Date vtoLibretaSanidad;
    
    //Referencia a la clase Usuario (Alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Define la fecha de alta
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Referencia a la clase Usuario (Modificacion)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Define la fecha de modificacion
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fechaUltimaMod", nullable = true)
    private Date fechaUltimaMod;
    
    //Referencia a la clase Usuario (Baja)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioBaja", nullable = true)
    private Usuario usuarioBaja;
    
    //Define la fecha de baja
    //Define la fecha
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "ART")
    @Column(name = "fechaBaja", nullable = true)
    private Date fechaBaja;
    
    //Define el alias para las busquedas
    @Column(name = "alias", nullable = true)
    private String alias;
    
    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public Date getVtoCarnet() {
        return vtoCarnet;
    }

    public void setVtoCarnet(Date vtoCarnet) {
        this.vtoCarnet = vtoCarnet;
    }

    public Date getVtoCurso() {
        return vtoCurso;
    }

    public void setVtoCurso(Date vtoCurso) {
        this.vtoCurso = vtoCurso;
    }

    public Date getVtoLNH() {
        return vtoLNH;
    }

    public void setVtoLNH(Date vtoLNH) {
        this.vtoLNH = vtoLNH;
    }

    public Date getVtoLibretaSanidad() {
        return vtoLibretaSanidad;
    }

    public void setVtoLibretaSanidad(Date vtoLibretaSanidad) {
        this.vtoLibretaSanidad = vtoLibretaSanidad;
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
    
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
}

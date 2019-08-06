//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Banco
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "personalfamiliar")
public class PersonalFamiliar extends ObjetoGenerico {
    
    //Define la referencia a la clase personal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idPersonal",nullable = false)
    private Personal personal;
    
    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false)
    private String nombre;
    
    //Define el apellido
    @Column(name = "apellido",length = 45, nullable = false)
    private String apellido;
    
    //Define la referencia a la clase tipofamiliar
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoFamiliar",nullable = false)
    private TipoFamiliar tipoFamiliar;
    
    //Define la referencia a la clase tipoDocumento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumento",nullable = false)
    private TipoDocumento tipoDocumento;
    
    //Define el numero de documento
    @Column(name = "numeroDocumento",length = 11, nullable = false)
    private String numeroDocumento;
    
    //Define el numero de cuil
    @Column(name = "cuil",length = 11, nullable = false, unique = true)
    private String cuil;
    
    //Define el numero de cuil
    @Column(name = "alias",length = 150, nullable = false)
    private String alias;
    
    //Define la fecha de nacimiento
    @Column(name = "fechaNacimiento", nullable = false)
    private Date fechaNacimiento;
    
    //Define la referencia a la clase localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidadNacimiento",nullable = false)
    private Localidad localidadNacimiento;
    
    //Define la referencia a la clase sexo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSexo",nullable = false)
    private Sexo sexo;
    
    //Define el anoi de alta
    @Column(name = "anioAltaImpGan",length = 4, nullable = true)
    private short anioAltaImpGan;
    
    //Define la referencia a la tabla mes de alta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMesAltaImpGan", nullable = true)
    private Mes mesAltaImpGan;
    
    //Define el anoi de baja
    @Column(name = "anioBajaImpGan",length = 4, nullable = true)
    private short anioBajaImpGan;
    
    //Define la referencia a la tabla mes de baja
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMesBajaImpGan", nullable = true)
    private Mes mesBajaImpGan;
    
    //Getters y Setters de la clase

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoFamiliar getTipoFamiliar() {
        return tipoFamiliar;
    }

    public void setTipoFamiliar(TipoFamiliar tipoFamiliar) {
        this.tipoFamiliar = tipoFamiliar;
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

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Localidad getLocalidadNacimiento() {
        return localidadNacimiento;
    }

    public void setLocalidadNacimiento(Localidad localidadNacimiento) {
        this.localidadNacimiento = localidadNacimiento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public short getAnioAltaImpGan() {
        return anioAltaImpGan;
    }

    public void setAnioAltaImpGan(short anioAltaImpGan) {
        this.anioAltaImpGan = anioAltaImpGan;
    }

    public Mes getMesAltaImpGan() {
        return mesAltaImpGan;
    }

    public void setMesAltaImpGan(Mes mesAltaImpGan) {
        this.mesAltaImpGan = mesAltaImpGan;
    }

    public short getAnioBajaImpGan() {
        return anioBajaImpGan;
    }

    public void setAnioBajaImpGan(short anioBajaImpGan) {
        this.anioBajaImpGan = anioBajaImpGan;
    }

    public Mes getMesBajaImpGan() {
        return mesBajaImpGan;
    }

    public void setMesBajaImpGan(Mes mesBajaImpGan) {
        this.mesBajaImpGan = mesBajaImpGan;
    }
    
}


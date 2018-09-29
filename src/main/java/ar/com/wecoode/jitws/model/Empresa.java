//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import java.sql.Blob;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Empresa
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "empresa")
public class Empresa extends ObjetoGenerico {
    
    //Define la razon social
    @Column(name = "razonSocial", nullable = false, unique = true)
    private String razonSocial;
    
    //Define el domicilio
    @Column(name = "domicilio", nullable = false)
    private String domicilio;
    
    //Referencia a la clase Barrio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBarrio", nullable = true)
    private Barrio barrio;
    
    //Referencia a la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidad", nullable = false)
    private Localidad localidad;
    
    //Referencia a la clase CondicionIva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCondicionIva", nullable = false)
    private CondicionIva condicionIva;
    
    //Define el cuit
    @Column(name = "cuit", nullable = false, unique = true)
    private String cuit;
    
    //Define el numero de ingresos brutos
    @Column(name = "numeroIIBB", nullable = true)
    private String numeroIIBB;
    
    //Define la abreviatura
    @Column(name = "abreviatura", nullable = false, unique = true)
    private String abreviatura;
    
    //Define el certificado digital
    @Lob
    @Column(name = "feCertificadoDigitalBin", nullable = true)
    private Blob feCertificadoDigitalBin;
    
    //Define el logo
    @Lob
    @Column(name = "logoBin", nullable = true)
    private Blob logoBin;
    
    //Define el caea
    @Column(name = "feCaea", nullable = false)
    private boolean feCAEA;
    
    //Define si esta activa
    @Column(name = "estaActiva", nullable = false)
    private boolean estaActiva;
    
    //Define el inicio de actividad
    @Column(name = "inicioActividad", nullable = true)
    private LocalDate inicioActividad;
    
    //Getters y Setters de la clase

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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

    public CondicionIva getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(CondicionIva condicionIva) {
        this.condicionIva = condicionIva;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNumeroIIBB() {
        return numeroIIBB;
    }

    public void setNumeroIIBB(String numeroIIBB) {
        this.numeroIIBB = numeroIIBB;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Blob getFeCertificadoDigitalBin() {
        return feCertificadoDigitalBin;
    }

    public void setFeCertificadoDigitalBin(Blob feCertificadoDigitalBin) {
        this.feCertificadoDigitalBin = feCertificadoDigitalBin;
    }

    public Blob getLogoBin() {
        return logoBin;
    }

    public void setLogoBin(Blob logoBin) {
        this.logoBin = logoBin;
    }

    public boolean getFeCAEA() {
        return feCAEA;
    }

    public void setFeCAEA(boolean feCAEA) {
        this.feCAEA = feCAEA;
    }

    public boolean getEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }

    public LocalDate getInicioActividad() {
        return inicioActividad;
    }

    public void setInicioActividad(LocalDate inicioActividad) {
        this.inicioActividad = inicioActividad;
    }
    
}

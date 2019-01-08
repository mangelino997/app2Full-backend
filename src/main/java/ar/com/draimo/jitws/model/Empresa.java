//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

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
    
    //Referencia a la clase AfipCondicionIva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipCondicionIva", nullable = false)
    private AfipCondicionIva afipCondicionIva;
    
    //Define el cuit
    @Column(name = "cuit", nullable = false, unique = true)
    private String cuit;
    
    //Define el numero de ingresos brutos
    @Column(name = "numeroIIBB", nullable = true)
    private String numeroIIBB;
    
    //Define la abreviatura
    @Column(name = "abreviatura", nullable = false, unique = true)
    private String abreviatura;
    
    //Define el logo
    @Lob
    @Column(name = "logoBin", nullable = true)
    private Blob logoBin;
    
    //Define si esta activa
    @Column(name = "estaActiva", nullable = false)
    private boolean estaActiva;
    
    //Define el inicio de actividad
    @Column(name = "inicioActividad", nullable = true)
    private LocalDate inicioActividad;
    
    //Define el caea
    @Column(name = "feCaea", nullable = false)
    private boolean feCAEA;
    
    //Define el fe modo
    @Column(name = "feModo", nullable = false)
    private boolean feModo;
    
    //Define el certificado real
    @Lob
    @Column(name = "certificadoReal", nullable = true)
    private Blob certificadoReal;
    
    //Define el certificado prueba
    @Lob
    @Column(name = "certificadoPrueba", nullable = true)
    private Blob certificadoPrueba;
    
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

    public AfipCondicionIva getAfipCondicionIva() {
        return afipCondicionIva;
    }

    public void setAfipCondicionIva(AfipCondicionIva afipCondicionIva) {
        this.afipCondicionIva = afipCondicionIva;
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

    public Blob getLogoBin() {
        return logoBin;
    }

    public void setLogoBin(Blob logoBin) {
        this.logoBin = logoBin;
    }

    public boolean isEstaActiva() {
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

    public boolean isFeCAEA() {
        return feCAEA;
    }

    public void setFeCAEA(boolean feCAEA) {
        this.feCAEA = feCAEA;
    }

    public boolean isFeModo() {
        return feModo;
    }

    public void setFeModo(boolean feModo) {
        this.feModo = feModo;
    }

    public Blob getCertificadoReal() {
        return certificadoReal;
    }

    public void setCertificadoReal(Blob certificadoReal) {
        this.certificadoReal = certificadoReal;
    }

    public Blob getCertificadoPrueba() {
        return certificadoPrueba;
    }

    public void setCertificadoPrueba(Blob certificadoPrueba) {
        this.certificadoPrueba = certificadoPrueba;
    }
    
}

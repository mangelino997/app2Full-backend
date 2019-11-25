//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Viaje Tramo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "viajetramo")
@JsonFilter("viajetramofiltro")
public class ViajeTramo extends ObjetoGenerico {

    //Referencia a la clase Viaje
    @JsonIgnoreProperties(value ={"viajeTramos","viajeCombustibles",
        "viajeEfectivos","viajeInsumos","viajeGastos","viajePeajes"})
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViaje", nullable = false)
    private Viaje viaje;
    
    //Referencia a la clase Tramo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTramo", nullable = false)
    private Tramo tramo;
    
    //Define un numero de orden (ordenamiento de tramos)
    @Column(name = "numeroOrden", nullable = false)
    private short numeroOrden;
    
    //Define una fecha tramo
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaTramo", nullable = false)    
    private Date fechaTramo;
    
    //Define una fecha alta
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Define un km
    @Column(name = "km", nullable = false)
    private short km;
    
    //Define observaciones
    @Column(name = "observaciones", length = 100, nullable = true)
    private String observaciones;
    
    //Referencia a la clase Viaje Tipo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTipo", nullable = false)
    private ViajeTipo viajeTipo;
    
    //Referencia a la clase Viaje Tipo Carga
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTipoCarga", nullable = false)
    private ViajeTipoCarga viajeTipoCarga;
    
    //Referencia a la clase Viaje Tarifa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeTarifa", nullable = true)
    private ViajeTarifa viajeTarifa;
    
    //Referencia a la clase Viaje Unidad Negocio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idViajeUnidadNegocio", nullable = false)
    private ViajeUnidadNegocio viajeUnidadNegocio;
    
    //Define el costo km
    @Column(name = "costoKm", nullable = true)
    private BigDecimal costoKm;
    
    //Define el importe cosot
    @Column(name = "importeCosto", nullable = true)
    private BigDecimal importeCosto;
    
    //Referencia a la clase Usuario (alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Referencia a la clase Usuario (mod)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Define un km100
    @Column(name = "km100", nullable = true)
    private short km100;
    
    //Define observaciones km 100
    @Column(name = "observacionesKm100", length = 100, nullable = true)
    private String observacionesKm100;
    
    //Define estadia
    @Column(name = "estadia", nullable = true)
    private short estadia;
    
    //Getters y Setters de la clase

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Tramo getTramo() {
        return tramo;
    }

    public void setTramo(Tramo tramo) {
        this.tramo = tramo;
    }

    public short getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(short numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFechaTramo() {
        return fechaTramo;
    }

    public void setFechaTramo(Date fechaTramo) {
        this.fechaTramo = fechaTramo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public short getKm() {
        return km;
    }

    public void setKm(short km) {
        this.km = km;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ViajeTipo getViajeTipo() {
        return viajeTipo;
    }

    public void setViajeTipo(ViajeTipo viajeTipo) {
        this.viajeTipo = viajeTipo;
    }

    public ViajeTipoCarga getViajeTipoCarga() {
        return viajeTipoCarga;
    }

    public void setViajeTipoCarga(ViajeTipoCarga viajeTipoCarga) {
        this.viajeTipoCarga = viajeTipoCarga;
    }

    public ViajeTarifa getViajeTarifa() {
        return viajeTarifa;
    }

    public void setViajeTarifa(ViajeTarifa viajeTarifa) {
        this.viajeTarifa = viajeTarifa;
    }

    public ViajeUnidadNegocio getViajeUnidadNegocio() {
        return viajeUnidadNegocio;
    }

    public void setViajeUnidadNegocio(ViajeUnidadNegocio viajeUnidadNegocio) {
        this.viajeUnidadNegocio = viajeUnidadNegocio;
    }

    public BigDecimal getCostoKm() {
        return costoKm;
    }

    public void setCostoKm(BigDecimal costoKm) {
        this.costoKm = costoKm;
    }

    public BigDecimal getImporteCosto() {
        return importeCosto;
    }

    public void setImporteCosto(BigDecimal importeCosto) {
        this.importeCosto = importeCosto;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public short getKm100() {
        return km100;
    }

    public void setKm100(short km100) {
        this.km100 = km100;
    }

    public String getObservacionesKm100() {
        return observacionesKm100;
    }

    public void setObservacionesKm100(String observacionesKm100) {
        this.observacionesKm100 = observacionesKm100;
    }

    public short getEstadia() {
        return estadia;
    }

    public void setEstadia(short estadia) {
        this.estadia = estadia;
    }

}
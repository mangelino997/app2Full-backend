//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Configuracion Vehiculo 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "configuracionvehiculo")
public class ConfiguracionVehiculo extends ObjetoGenerico {

    //Referencia a la clase TipoVehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoVehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;

    //Referencia a la clase MarcaVehiculo
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMarcaVehiculo", nullable = false)
    private MarcaVehiculo marcaVehiculo;

    //Define el modelo
    @Column(name = "modelo", length = 45, nullable = false)
    private String modelo;

    //Define la descripcion
    @Column(name = "descripcion", length = 100, nullable = true)
    private String descripcion;

    //Define la cantidad de ejes
    @Column(name = "cantidadEjes", nullable = false)
    private short cantidadEjes;

    //Define la capacidad de carga
    @Column(name = "capacidadCarga", nullable = false)
    private BigDecimal capacidadCarga;

    //Define la tara
    @Column(name = "tara", nullable = true)
    private BigDecimal tara;

    //Define la altura
    @Column(name = "altura", nullable = true)
    private BigDecimal altura;

    //Define el largo
    @Column(name = "largo", nullable = true)
    private BigDecimal largo;

    //Define el ancho
    @Column(name = "ancho", nullable = true)
    private BigDecimal ancho;

    //Define los m3
    @Column(name = "m3", nullable = true)
    private BigDecimal m3;

    //Getters y Setters de la clase
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getCantidadEjes() {
        return cantidadEjes;
    }

    public void setCantidadEjes(short cantidadEjes) {
        this.cantidadEjes = cantidadEjes;
    }

    public BigDecimal getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(BigDecimal capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public BigDecimal getTara() {
        return tara;
    }

    public void setTara(BigDecimal tara) {
        this.tara = tara;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getM3() {
        return m3;
    }

    public void setM3(BigDecimal m3) {
        this.m3 = m3;
    }

}

//Paquete al que pertenece la clase
package ar.com.wecode.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Configuracion Vehiculo
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "configuracionvehiculo")
public class ConfiguracionVehiculo extends ObjetoGenerico {

    //Referencia a la clase TipoVehiculo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoVehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;
    
    //Referencia a la clase MarcaVehiculo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMarcaVehiculo", nullable = false)
    private MarcaVehiculo marcaVehiculo;
    
    //Define el modelo
    @Column(name = "modelo", nullable = false)
    private String modelo;
    
    //Define la descripcion
    @Column(name = "descripcion", nullable = true)
    private String descripcion;
    
    //Define la cantidad de ejes
    @Column(name = "cantidadEjes", nullable = false)
    private short cantidadEjes;
    
    //Define la capacidad de carga
    @Column(name = "capacidadCarga", nullable = false)
    private short capacidadCarga;
    
    //Define la tara
    @Column(name = "tara", nullable = true)
    private short tara;
    
    //Define la altura
    @Column(name = "altura", nullable = true)
    private short altura;
    
    //Define el largo
    @Column(name = "largo", nullable = true)
    private short largo;
    
    //Define el ancho
    @Column(name = "ancho", nullable = true)
    private short ancho;
    
    //Define los m3
    @Column(name = "m3", nullable = true)
    private short m3;

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

    public short getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(short capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public short getTara() {
        return tara;
    }

    public void setTara(short tara) {
        this.tara = tara;
    }

    public short getAltura() {
        return altura;
    }

    public void setAltura(short altura) {
        this.altura = altura;
    }

    public short getLargo() {
        return largo;
    }

    public void setLargo(short largo) {
        this.largo = largo;
    }

    public short getAncho() {
        return ancho;
    }

    public void setAncho(short ancho) {
        this.ancho = ancho;
    }

    public short getM3() {
        return m3;
    }

    public void setM3(short m3) {
        this.m3 = m3;
    }
    
}
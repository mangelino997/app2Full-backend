//Paquete al que pertenece la clase
package ar.com.wecoode.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Punto Venta
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "puntoventa")
public class PuntoVenta extends ObjetoGenerico {

    //Referencia a la clase Sucursal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    
    //Referencia a la clase Empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Define el punto de venta
    @Column(name = "puntoVenta", nullable = false)
    private int puntoVenta;
    
    //Define el fe
    @Column(name = "fe", nullable = false)
    private boolean fe;
    
    //Define el fe en linea
    @Column(name = "feEnLinea", nullable = false)
    private boolean feEnLinea;
    
    //Define el fe caea
    @Column(name = "feCAEA", nullable = false)
    private boolean feCAEA;
    
    //Define si es cuenta y orden
    @Column(name = "esCuentaOrden", nullable = false)
    private boolean esCuentaOrden;
    
    //Define el ultimo numero
    @Column(name = "ultimoNumero", nullable = false)
    private int ultimoNumero;
    
    //Define si es copia
    @Column(name = "copias", nullable = false)
    private short copias;
    
    //Define si imprime
    @Column(name = "imprime", nullable = false)
    private boolean imprime;
    
    //Define si esta habilitado
    @Column(name = "estaHabilitado", nullable = false)
    private boolean estaHabilitado;
    
    //Define si valor por defecto
    @Column(name = "porDefecto", nullable = false)
    private boolean porDefecto;
    
    //Getters y Setters de la clase

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(int puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public boolean getFe() {
        return fe;
    }

    public void setFe(boolean fe) {
        this.fe = fe;
    }

    public boolean getFeEnLinea() {
        return feEnLinea;
    }

    public void setFeEnLinea(boolean feEnLinea) {
        this.feEnLinea = feEnLinea;
    }

    public boolean getFeCAEA() {
        return feCAEA;
    }

    public void setFeCAEA(boolean feCAEA) {
        this.feCAEA = feCAEA;
    }

    public boolean getEsCuentaOrden() {
        return esCuentaOrden;
    }

    public void setEsCuentaOrden(boolean esCuentaOrden) {
        this.esCuentaOrden = esCuentaOrden;
    }

    public int getUltimoNumero() {
        return ultimoNumero;
    }

    public void setUltimoNumero(int ultimoNumero) {
        this.ultimoNumero = ultimoNumero;
    }

    public short getCopias() {
        return copias;
    }

    public void setCopias(short copias) {
        this.copias = copias;
    }

    public boolean getImprime() {
        return imprime;
    }

    public void setImprime(boolean imprime) {
        this.imprime = imprime;
    }

    public boolean getEstaHabilitado() {
        return estaHabilitado;
    }

    public void setEstaHabilitado(boolean estaHabilitado) {
        this.estaHabilitado = estaHabilitado;
    }

    public boolean isPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(boolean porDefecto) {
        this.porDefecto = porDefecto;
    }
    
}
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
 * Clase InsumoProducto
 Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "insumoproducto")
public class InsumoProducto extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre",length = 45, nullable = false)
    private String nombre;
    
    //Referenica a la clase Rubro Producto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRubroProducto", nullable = false)
    private RubroProducto rubroProducto;
    
    //Referenica a la clase Marca Producto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idMarcaProducto", nullable = false)
    private MarcaProducto marcaProducto;
    
    //Referenica a la clase Unidad Medida
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUnidadMedida", nullable = false)
    private UnidadMedida unidadMedida;
    
    //Define el modelo
    @Column(name = "modelo", length = 30, nullable = true)
    private String modelo;
    
    //Define es asignable
    @Column(name = "esAsignable", nullable = false)
    private boolean esAsignable;
    
    //Define es serializable
    @Column(name = "esSerializable", nullable = false)
    private boolean esSerializable;
    
    //Define es critico
    @Column(name = "esCritico", nullable = false)
    private boolean esCritico;
    
    //Define el stock minimo
    @Column(name = "stockMinimo", nullable = true)
    private int stockMinimo;
    
    //Define el precio unitario viaje
    @Column(name = "precioUnitarioViaje", nullable = true)
    private BigDecimal precioUnitarioViaje;
    
    //Define el precio unitario venta
    @Column(name = "precioUnitarioVenta", nullable = true)
    private BigDecimal precioUnitarioVenta;
    
    //Define el coeficiente ITC
    @Column(name = "coeficienteITC", nullable = true)
    private BigDecimal coeficienteITC;
    
    //Referencia a la clase Usuario
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    //Getters y Setters de la clase

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RubroProducto getRubroProducto() {
        return rubroProducto;
    }

    public void setRubroProducto(RubroProducto rubroProducto) {
        this.rubroProducto = rubroProducto;
    }

    public MarcaProducto getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(MarcaProducto marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isEsAsignable() {
        return esAsignable;
    }

    public void setEsAsignable(boolean esAsignable) {
        this.esAsignable = esAsignable;
    }

    public boolean isEsSerializable() {
        return esSerializable;
    }

    public void setEsSerializable(boolean esSerializable) {
        this.esSerializable = esSerializable;
    }

    public boolean isEsCritico() {
        return esCritico;
    }

    public void setEsCritico(boolean esCritico) {
        this.esCritico = esCritico;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public BigDecimal getPrecioUnitarioViaje() {
        return precioUnitarioViaje;
    }

    public void setPrecioUnitarioViaje(BigDecimal precioUnitarioViaje) {
        this.precioUnitarioViaje = precioUnitarioViaje;
    }
    
    public BigDecimal getPrecioUnitarioVenta() {
        return precioUnitarioVenta;
    }

    public void setPrecioUnitarioVenta(BigDecimal precioUnitarioVenta) {
        this.precioUnitarioVenta = precioUnitarioVenta;
    }

    public BigDecimal getCoeficienteITC() {
        return coeficienteITC;
    }

    public void setCoeficienteITC(BigDecimal coeficienteITC) {
        this.coeficienteITC = coeficienteITC;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Clase TipoTarifa
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "tipotarifa")
public class TipoTarifa extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;
    
    //Define si es por escala
    @Column(name = "porEscala", nullable = false)
    private boolean porEscala;
    
    //Define si es por porcentaje
    @Column(name = "porPorcentaje", nullable = false)
    private boolean porPorcentaje;
    
    //Referencia al a clase ordenVenta
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "ordenventatarifa",
            joinColumns = @JoinColumn(name = "idTipoTarifa"),
            inverseJoinColumns = @JoinColumn(name = "idOrdenVenta"))
    @JsonIgnoreProperties(value = { "empresas", "tipoTarifas"})
    private List<OrdenVenta> ordenesVentas;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getPorEscala() {
        return porEscala;
    }

    public void setPorEscala(boolean porEscala) {
        this.porEscala = porEscala;
    }

    public boolean getPorPorcentaje() {
        return porPorcentaje;
    }

    public void setPorPorcentaje(boolean porPorcentaje) {
        this.porPorcentaje = porPorcentaje;
    }

    public List<OrdenVenta> getOrdenesVentas() {
        return ordenesVentas;
    }

    public void setOrdenesVentas(List<OrdenVenta> ordenesVentas) {
        this.ordenesVentas = ordenesVentas;
    }
    
}
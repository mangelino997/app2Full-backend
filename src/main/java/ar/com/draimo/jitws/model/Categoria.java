//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase Categoria 
 * Define el modelo (columnas) de la base de datos.
 *
 * @author blas
 */
@Entity
@Table(name = "categoria")
public class Categoria extends ObjetoGenerico {

    //Define el nombre
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    //Define el adicional basico por vacaciones
    @Column(name = "adicionalBasicoVacaciones", length = 45, nullable = false)
    private BigDecimal adicionalBasicoVacaciones;

    //Define el tope basico de adelantos
    @Column(name = "topeBasicoAdelantos", nullable = false)
    private BigDecimal topeBasicoAdelantos;

    //Define los dias laborables
    @Column(name = "diasLaborables", length = 2, nullable = false)
    private short diasLaborables;

    //Define las horas laborables
    @Column(name = "horasLaborables", length = 2, nullable = false)
    private short horasLaborables;

    //Getters y Setters de la clase
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getAdicionalBasicoVacaciones() {
        return adicionalBasicoVacaciones;
    }

    public void setAdicionalBasicoVacaciones(BigDecimal adicionalBasicoVacaciones) {
        this.adicionalBasicoVacaciones = adicionalBasicoVacaciones;
    }

    public BigDecimal getTopeBasicoAdelantos() {
        return topeBasicoAdelantos;
    }

    public void setTopeBasicoAdelantos(BigDecimal topeBasicoAdelantos) {
        this.topeBasicoAdelantos = topeBasicoAdelantos;
    }

    public short getDiasLaborables() {
        return diasLaborables;
    }

    public void setDiasLaborables(short diasLaborables) {
        this.diasLaborables = diasLaborables;
    }

    public short getHorasLaborables() {
        return horasLaborables;
    }

    public void setHorasLaborables(short horasLaborables) {
        this.horasLaborables = horasLaborables;
    }

}

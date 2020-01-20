/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase ConceptoSueldo
 * Define el modelo (columnas) de la base de datos.
 * @author marina
 */
@Entity
@Table(name = "conceptosueldo")
public class ConceptoSueldo extends ObjetoGenerico {
    
    @Column(name = "nombre", length = 60, nullable = false, unique = true)
    private String nombre;
    
    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn (name = "idAfipConceptoSueldo", nullable = false)
    private AfipConceptoSueldo afipConceptoSueldo;
    
    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn (name = "idUnidadMedidaSueldo", nullable = false)
    private UnidadMedidaSueldo unidadMedidaSueldo;
    
    @Column(name = "ingresaCantidad", nullable = false)
    private boolean ingresaCantidad;
    
    @Column(name = "ingresaValorUnitario", nullable = false)
    private boolean ingresaValorUnitario;
    
    @Column(name = "ingresaImporte", nullable = false)
    private boolean ingresaImporte;
    
    @Column(name = "esRepetible", nullable = false)
    private boolean esRepetible;
    
    @Column(name = "imprimeValorUnitario", nullable = false)
    private boolean imprimeValorUnitario;
    
    @Column(name = "codigoEmpleador", length = 10, nullable = false, unique = true)
    private String codigoEmpleador;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AfipConceptoSueldo getAfipConceptoSueldo() {
        return afipConceptoSueldo;
    }

    public void setAfipConceptoSueldo(AfipConceptoSueldo afipConceptoSueldo) {
        this.afipConceptoSueldo = afipConceptoSueldo;
    }

    public UnidadMedidaSueldo getUnidadMedidaSueldo() {
        return unidadMedidaSueldo;
    }

    public void setUnidadMedidaSueldo(UnidadMedidaSueldo unidadMedidaSueldo) {
        this.unidadMedidaSueldo = unidadMedidaSueldo;
    }

    public boolean isIngresaCantidad() {
        return ingresaCantidad;
    }

    public void setIngresaCantidad(boolean ingresaCantidad) {
        this.ingresaCantidad = ingresaCantidad;
    }

    public boolean isIngresaValorUnitario() {
        return ingresaValorUnitario;
    }

    public void setIngresaValorUnitario(boolean ingresaValorUnitario) {
        this.ingresaValorUnitario = ingresaValorUnitario;
    }

    public boolean isIngresaImporte() {
        return ingresaImporte;
    }

    public void setIngresaImporte(boolean ingresaImporte) {
        this.ingresaImporte = ingresaImporte;
    }

    public boolean isEsRepetible() {
        return esRepetible;
    }

    public void setEsRepetible(boolean esRepetible) {
        this.esRepetible = esRepetible;
    }

    public boolean isImprimeValorUnitario() {
        return imprimeValorUnitario;
    }

    public void setImprimeValorUnitario(boolean imprimeValorUnitario) {
        this.imprimeValorUnitario = imprimeValorUnitario;
    }

    public String getCodigoEmpleador() {
        return codigoEmpleador;
    }

    public void setCodigoEmpleador(String codigoEmpleador) {
        this.codigoEmpleador = codigoEmpleador;
    }
    
}
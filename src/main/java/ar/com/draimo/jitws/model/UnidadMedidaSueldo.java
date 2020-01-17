/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *Clase UnidadMedidaSueldo
 * Define el modelo (columnas) de la base de datos.
 * @author marina
 */
@Entity
@Table(name = "unidadmedidasueldo")
class UnidadMedidaSueldo extends ObjetoGenerico {
    
    @Column(name = "nombre", length = 45, nullable = false, unique = true)
    private String nombre;
    
    @Column(name = "codigoAfip", length = 3)
    private String codigoAfip;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(String codigoAfip) {
        this.codigoAfip = codigoAfip;
    }
    
    
}

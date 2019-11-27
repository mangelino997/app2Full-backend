/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Cobrador;
import ar.com.draimo.jitws.model.CondicionVenta;
import ar.com.draimo.jitws.model.Localidad;

/**
 *
 * @author marcio
 */
public class ClienteDTO {

    //Define Localidad
    private String idLocalidad;

    //Define Cobrador
    private String idCobrador;

    //Define condición venta
    private String idCondicionVenta;
    
    //Define esSeguroPropio
    private int esSeguroPropio;

    public String getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(String idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getIdCobrador() {
        return idCobrador;
    }

    public void setIdCobrador(String idCobrador) {
        this.idCobrador = idCobrador;
    }

    public String getIdCondicionVenta() {
        return idCondicionVenta;
    }

    public void setIdCondicionVenta(String idCondicionVenta) {
        this.idCondicionVenta = idCondicionVenta;
    }

    public int getEsSeguroPropio() {
        return esSeguroPropio;
    }

    public void setEsSeguroPropio(int esSeguroPropio) {
        this.esSeguroPropio = esSeguroPropio;
    }

    

}

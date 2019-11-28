/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.dto;

/**
 *
 * @author marcio
 */
public class ProveedorDTO {
    
    //Define Localidad
    private String idLocalidad;
    
    //Define el tipo de proveedor
    private String idTipoProveedor;

    //Define condición compra
    private String idCondicionCompra;

    public String getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(String idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getIdTipoProveedor() {
        return idTipoProveedor;
    }

    public void setIdTipoProveedor(String idTipoProveedor) {
        this.idTipoProveedor = idTipoProveedor;
    }

    public String getIdCondicionCompra() {
        return idCondicionCompra;
    }

    public void setIdCondicionCompra(String idCondicionCompra) {
        this.idCondicionCompra = idCondicionCompra;
    }
    
    
}

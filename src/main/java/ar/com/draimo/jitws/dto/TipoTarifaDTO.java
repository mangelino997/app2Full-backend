/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.dto;


/**
 *
 * @author Marcio
 */
public class TipoTarifaDTO {

    //Define el id de tarifa
    private int id;

    //Define la version
    private int version;

    //Define el nombre
    private String nombre;

    //Define si es por escala
    private boolean porEscala;

    //Define si es por porcentaje
    private boolean porPorcentaje;

    //Define el id de la orden venta tarifa
    private int idOrdenVentaTarifa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isPorEscala() {
        return porEscala;
    }

    public void setPorEscala(boolean porEscala) {
        this.porEscala = porEscala;
    }

    public boolean isPorPorcentaje() {
        return porPorcentaje;
    }

    public void setPorPorcentaje(boolean porPorcentaje) {
        this.porPorcentaje = porPorcentaje;
    }

    public int getIdOrdenVentaTarifa() {
        return idOrdenVentaTarifa;
    }

    public void setIdOrdenVentaTarifa(int idOrdenVentaTarifa) {
        this.idOrdenVentaTarifa = idOrdenVentaTarifa;
    }

}

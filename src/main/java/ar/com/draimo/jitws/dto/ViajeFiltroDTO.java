package ar.com.draimo.jitws.dto;

import java.sql.Date;

/**
 * Define DTO para busquedas de viajes
 * @author blas
 */
public class ViajeFiltroDTO {
    
    //Define el id viaje
    private String idViaje;
    
    //Define la fecha desde
    private Date fechaDesde;
    
    //Define la fecha hasta
    private Date fechaHasta;
    
    //Define el id personal
    private String idPersonal;
    
    //Define el id proveedor
    private String idProveedor;

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(String idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }
    
}
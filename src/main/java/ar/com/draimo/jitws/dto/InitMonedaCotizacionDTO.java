package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Moneda;
import java.sql.Date;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitMonedaCotizacionDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Define la fecha actual
    private Date fecha;
    
    //Lista de condicion de iva
    private List<Moneda> monedas; 

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

}
package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Moneda;
import ar.com.draimo.jitws.model.MonedaCuentaContable;
import ar.com.draimo.jitws.model.Pestania;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitMonedaCuentaContableDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de sucursales
    private List<MonedaCuentaContable> monedaCuentaContables;  
    
    //Lista de monedas
    private List<Moneda> monedas;  
    
    //Lista de pestanias
    private List<Pestania> pestanias;

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<MonedaCuentaContable> getMonedaCuentaContables() {
        return monedaCuentaContables;
    }

    public void setMonedaCuentaContables(List<MonedaCuentaContable> monedaCuentaContables) {
        this.monedaCuentaContables = monedaCuentaContables;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }

}
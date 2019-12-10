package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Moneda;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.TipoCuentaBancaria;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitCuentaBancariaDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de tipos de docs
    private List<TipoCuentaBancaria> tipoCuentaBancarias; 
    
    //Lista de sucursales
    private List<Moneda> monedas;  
    
    //Lista de empresas
    private List<Pestania> pestanias;

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<TipoCuentaBancaria> getTipoCuentaBancarias() {
        return tipoCuentaBancarias;
    }

    public void setTipoCuentaBancarias(List<TipoCuentaBancaria> tipoCuentaBancarias) {
        this.tipoCuentaBancarias = tipoCuentaBancarias;
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
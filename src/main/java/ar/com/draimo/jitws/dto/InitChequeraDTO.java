package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Chequera;
import ar.com.draimo.jitws.model.CuentaBancaria;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.TipoChequera;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitChequeraDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Define tipoChequeras
    private List<TipoChequera> tipoChequeras;
    
    //Define cuentasBancarias
    private List<Chequera> chequeras;
    
    //Define cuentasBancarias
    private List<CuentaBancaria> cuentaBancariaConsultas;
    
    //Define cuentasBancarias
    private List<CuentaBancaria> cuentaBancariaConCheques;
    
    //Define pestanias
    private List<Pestania> pestanias;

    public int getUltimoId() {
        return ultimoId;
    }

//Getters and setters de la clase
    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<TipoChequera> getTipoChequeras() {
        return tipoChequeras;
    }

    public void setTipoChequeras(List<TipoChequera> tipoChequeras) {
        this.tipoChequeras = tipoChequeras;
    }

    public List<Chequera> getChequeras() {
        return chequeras;
    }

    public void setChequeras(List<Chequera> chequeras) {
        this.chequeras = chequeras;
    }
    
    public List<CuentaBancaria> getCuentaBancariaConsultas() {
        return cuentaBancariaConsultas;
    }

    public void setCuentaBancariaConsultas(List<CuentaBancaria> cuentaBancariaConsultas) {
        this.cuentaBancariaConsultas = cuentaBancariaConsultas;
    }

    public List<CuentaBancaria> getCuentaBancariaConCheques() {
        return cuentaBancariaConCheques;
    }

    public void setCuentaBancariaConCheques(List<CuentaBancaria> cuentaBancariaConCheques) {
        this.cuentaBancariaConCheques = cuentaBancariaConCheques;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }

}

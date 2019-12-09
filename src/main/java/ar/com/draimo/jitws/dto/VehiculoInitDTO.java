package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.MarcaVehiculo;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.TipoVehiculo;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class VehiculoInitDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de tipoVehiculos
    private List<TipoVehiculo> tipoVehiculos; 
    
    //Lista de marcaVehiculos
    private List<MarcaVehiculo> marcaVehiculos; 
    
    //Lista de empresa
    private List<Empresa> empresas; 
    
    //Lista de pestanias
    private List<Pestania> pestanias;
    
    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<TipoVehiculo> getTipoVehiculos() {
        return tipoVehiculos;
    }

    public void setTipoVehiculos(List<TipoVehiculo> tipoVehiculos) {
        this.tipoVehiculos = tipoVehiculos;
    }

    public List<MarcaVehiculo> getMarcaVehiculos() {
        return marcaVehiculos;
    }

    public void setMarcaVehiculos(List<MarcaVehiculo> marcaVehiculos) {
        this.marcaVehiculos = marcaVehiculos;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }

}
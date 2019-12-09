package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.MarcaProducto;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.RubroProducto;
import ar.com.draimo.jitws.model.UnidadMedida;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitProductoDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de afipCondicionesIva
    private List<RubroProducto> rubroProductos;  
    
    //Lista de condicionCompras
    private List<MarcaProducto>  marcaProductos; 
    
    //Lista de tipoCuentaBancarias
    private List<UnidadMedida> unidadMedidas;  
    
    //Lista de pestanias
    private List<Pestania> pestanias;

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<RubroProducto> getRubroProductos() {
        return rubroProductos;
    }

    public void setRubroProductos(List<RubroProducto> rubroProductos) {
        this.rubroProductos = rubroProductos;
    }

    public List<MarcaProducto> getMarcaProductos() {
        return marcaProductos;
    }

    public void setMarcaProductos(List<MarcaProducto> marcaProductos) {
        this.marcaProductos = marcaProductos;
    }

    public List<UnidadMedida> getUnidadMedidas() {
        return unidadMedidas;
    }

    public void setUnidadMedidas(List<UnidadMedida> unidadMedidas) {
        this.unidadMedidas = unidadMedidas;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }

}
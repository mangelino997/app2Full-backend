package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.AfipAlicuotaIva;
import ar.com.draimo.jitws.model.CondicionCompra;
import ar.com.draimo.jitws.model.DepositoInsumoProducto;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitCompraComprobanteDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de tipos de docs
    private List<TipoComprobante> tipoComprobantes; 
    
    //Lista de afipAlicuotaIvas
    private List<AfipAlicuotaIva> afipAlicuotaIvas;
    
    //Lista de depositoInsumoProductos
    private List<DepositoInsumoProducto> depositoInsumoProductos;
    
    //Lista de condicionCompras
    private List<CondicionCompra> condicionCompras;
    
    //Lista de letras
    private List<String> letras;
    
    //Lista de pestanias
    private List<Pestania> pestanias;

    //Define Getters y Setters

    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<TipoComprobante> getTipoComprobantes() {
        return tipoComprobantes;
    }

    public void setTipoComprobantes(List<TipoComprobante> tipoComprobantes) {
        this.tipoComprobantes = tipoComprobantes;
    }

    public List<AfipAlicuotaIva> getAfipAlicuotaIvas() {
        return afipAlicuotaIvas;
    }

    public void setAfipAlicuotaIvas(List<AfipAlicuotaIva> afipAlicuotaIvas) {
        this.afipAlicuotaIvas = afipAlicuotaIvas;
    }

    public List<DepositoInsumoProducto> getDepositoInsumoProductos() {
        return depositoInsumoProductos;
    }

    public void setDepositoInsumoProductos(List<DepositoInsumoProducto> depositoInsumoProductos) {
        this.depositoInsumoProductos = depositoInsumoProductos;
    }

    public List<CondicionCompra> getCondicionCompras() {
        return condicionCompras;
    }

    public void setCondicionCompras(List<CondicionCompra> condicionCompras) {
        this.condicionCompras = condicionCompras;
    }

    public List<String> getLetras() {
        return letras;
    }

    public void setLetras(List<String> letras) {
        this.letras = letras;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}
package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.AfipAlicuotaIva;
import ar.com.draimo.jitws.model.EmpresaOrdenVenta;
import ar.com.draimo.jitws.model.PuntoVenta;
import ar.com.draimo.jitws.model.TipoComprobante;
import ar.com.draimo.jitws.model.VentaTipoItem;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitFacturaDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de tipos de docs
    private List<TipoComprobante> tipoComprobantes; 
    
    //Lista de sucursales
    private List<PuntoVenta> puntoVentas;  
    
    //Lista de rubros
    private List<VentaTipoItem> ventaTipoItems;
    
    //Lista de empresas
    private List<AfipAlicuotaIva> afipAlicuotaIvas;
    
    //Lista de empresas
    private List<EmpresaOrdenVenta> empresaOrdenVentas;

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

    public List<PuntoVenta> getPuntoVentas() {
        return puntoVentas;
    }

    public void setPuntoVentas(List<PuntoVenta> puntoVentas) {
        this.puntoVentas = puntoVentas;
    }

    public List<VentaTipoItem> getVentaTipoItems() {
        return ventaTipoItems;
    }

    public void setVentaTipoItems(List<VentaTipoItem> ventaTipoItems) {
        this.ventaTipoItems = ventaTipoItems;
    }

    public List<AfipAlicuotaIva> getAfipAlicuotaIvas() {
        return afipAlicuotaIvas;
    }

    public void setAfipAlicuotaIvas(List<AfipAlicuotaIva> afipAlicuotaIvas) {
        this.afipAlicuotaIvas = afipAlicuotaIvas;
    }

    public List<EmpresaOrdenVenta> getEmpresaOrdenVentas() {
        return empresaOrdenVentas;
    }

    public void setEmpresaOrdenVentas(List<EmpresaOrdenVenta> empresaOrdenVentas) {
        this.empresaOrdenVentas = empresaOrdenVentas;
    }
    
}
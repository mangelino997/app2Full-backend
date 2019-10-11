package ar.com.draimo.jitws.dto;

import java.sql.Date;

/**
 * Define DTO para busquedas de reparto
 * @author blas
 */
public class elementoDTO {
    
    //Define el id reparto
    private int idReparto;
    
    //Define el id empresa
    private int idEmpresa;
    
    //Define el idSucursal
    private int idSucursal;
    
    //Define es reparto propio
    private boolean esRepartoPropio;
    
    //Define la fecha desde
    private Date fechaDesde;
    
    //Define la fecha hasta
    private Date fechaHasta;
    
    //Define el id chofer
    private int idChofer;
    
    //Define esta cerrada
    private boolean estaCerrada;

    public int getIdReparto() {
        return idReparto;
    }

    public void setIdReparto(int idReparto) {
        this.idReparto = idReparto;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public boolean isEsRepartoPropio() {
        return esRepartoPropio;
    }

    public void setEsRepartoPropio(boolean esRepartoPropio) {
        this.esRepartoPropio = esRepartoPropio;
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

    public int getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(int idChofer) {
        this.idChofer = idChofer;
    }

    public boolean isEstaCerrada() {
        return estaCerrada;
    }

    public void setEstaCerrada(boolean estaCerrada) {
        this.estaCerrada = estaCerrada;
    }

}
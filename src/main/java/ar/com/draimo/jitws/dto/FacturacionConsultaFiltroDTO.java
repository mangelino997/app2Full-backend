/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author marcio
 */
public class FacturacionConsultaFiltroDTO {

    //Define idEmpresa
    private int idSucursal;

    //Define idCliente
    private int idCliente;

    //Define fechaTipo
    private int fechaTipo;

    //Define fecha pago desde
    private Date fechaDesde;

    //Define fecha pago hasta
    private Date fechaHasta;

    //Define fecha pago desde
    private int idTipoComprobante;

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getFechaTipo() {
        return fechaTipo;
    }

    public void setFechaTipo(int fechaTipo) {
        this.fechaTipo = fechaTipo;
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


    public int getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(int idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }
    
    
}

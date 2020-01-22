/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.PuntoVenta;
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

    //Define punto de venta
    private PuntoVenta puntoVenta;

    //Define la letra
    private String letra;

    //Define el numero
    private String numero;

    //Define el numeroDesde
    private String numeroDesde;

    //Define el numeroHasta
    private String numeroHasta;

    //Define el importeDesde
    private BigDecimal importeDesde;

    //Define el importeHasta
    private BigDecimal importeHasta;

    //Define el tipo de filtro
    private int tipoFiltro;

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

    public PuntoVenta getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(PuntoVenta puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroDesde() {
        return numeroDesde;
    }

    public void setNumeroDesde(String numeroDesde) {
        this.numeroDesde = numeroDesde;
    }

    public String getNumeroHasta() {
        return numeroHasta;
    }

    public void setNumeroHasta(String numeroHasta) {
        this.numeroHasta = numeroHasta;
    }

    public BigDecimal getImporteDesde() {
        return importeDesde;
    }

    public void setImporteDesde(BigDecimal importeDesde) {
        this.importeDesde = importeDesde;
    }

    public BigDecimal getImporteHasta() {
        return importeHasta;
    }

    public void setImporteHasta(BigDecimal importeHasta) {
        this.importeHasta = importeHasta;
    }

    public int getTipoFiltro() {
        return tipoFiltro;
    }

    public void setTipoFiltro(int tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }

}

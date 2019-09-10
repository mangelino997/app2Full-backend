package ar.com.draimo.jitws.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

/**
 *
 * @author blas
 */
public class PersonalAdelantoLoteDTO {
    
    //Define empresa
    private int idEmpresa;
    
    //Define sucursal
    private int idSucursal;
    
    //Define categoria
    private int idCategoria;
    
    //Define usuarioAlta
    private int idUsuarioAlta;
    
    //Define observaciones
    private String observaciones;
    
    //Define el importe
    private BigDecimal importe;
    
    //Define el importe total
    private BigDecimal importeTotal;

    //Define el total de legajos
    private BigInteger legajos;

    //Define el numero de lote
    private int numeroLote;

    //Define la fecha de emision
    private Date fechaEmision;

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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(int idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigInteger getLegajos() {
        return legajos;
    }

    public void setLegajos(BigInteger legajos) {
        this.legajos = legajos;
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(int numeroLote) {
        this.numeroLote = numeroLote;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    
}

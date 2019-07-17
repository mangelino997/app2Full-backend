package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase CompraComprobante
 * Mapea con la tabla en la base de datos
 * @author blas
 */

@Entity
@Table(name = "compracomprobanteitem")
public class CompraComprobanteItem extends ObjetoGenerico {
    
    //Referencia a la clase CompraComprobante
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompraComprobante", nullable = false)
    private CompraComprobante compraComprobante;
    
    //Referencia a la clase InsumoProducto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idInsumoProducto", nullable = true)
    private InsumoProducto insumoProducto;
    
    //Referencia a la clase DepositoInsumoProducto
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idDepositoInsumoProducto", nullable = true)
    private DepositoInsumoProducto depositoInsumoProducto;
    
    //Define Cantidad
    @Column(name = "cantidad", nullable = true)
    private BigDecimal cantidad;
    
    //Define precioUnitario
    @Column(name = "precioUnitario", nullable = true)
    private BigDecimal precioUnitario;
    
    //Define importeNetoGravado
    @Column(name = "importeNetoGravado", nullable = true)
    private BigDecimal importeNetoGravado;
    
    //Referencia a la clase AfipAlicuotaIva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idAfipAlicuotaIva", nullable = true)
    private AfipAlicuotaIva afipAlicuotaIva;
    
    //Define alicuotaIVA
    @Column(name = "alicuotaIVA", nullable = true)
    private BigDecimal alicuotaIVA;
    
    //Define importeIva
    @Column(name = "importeIva", nullable = true)
    private BigDecimal importeIva;
    
    //Define importeNoGravado
    @Column(name = "importeNoGravado", nullable = true)
    private BigDecimal importeNoGravado;
    
    //Define importeExento
    @Column(name = "importeExento", nullable = true)
    private BigDecimal importeExento;
    
    //Define importeImpuestoInterno
    @Column(name = "importeImpuestoInterno", nullable = true)
    private BigDecimal importeImpuestoInterno;
    
    //Referencia a la clase PlanCuenta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCuentaContable", nullable = true)
    private PlanCuenta cuentaContable;
    
    //Define itcPorLitro
    @Column(name = "itcPorLitro", nullable = true)
    private BigDecimal itcPorLitro;
    
    //Define importeITC
    @Column(name = "importeITC", nullable = true)
    private BigDecimal importeITC;
    
    //Define observaciones
    @Column(name = "observaciones",length = 45, nullable = true)
    private String observaciones;
    
    //Getters y Setters de la clase

    public CompraComprobante getCompraComprobante() {
        return compraComprobante;
    }

    public void setCompraComprobante(CompraComprobante compraComprobante) {
        this.compraComprobante = compraComprobante;
    }

    public InsumoProducto getInsumoProducto() {
        return insumoProducto;
    }

    public void setInsumoProducto(InsumoProducto insumoProducto) {
        this.insumoProducto = insumoProducto;
    }

    public DepositoInsumoProducto getDepositoInsumoProducto() {
        return depositoInsumoProducto;
    }

    public void setDepositoInsumoProducto(DepositoInsumoProducto depositoInsumoProducto) {
        this.depositoInsumoProducto = depositoInsumoProducto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getImporteNetoGravado() {
        return importeNetoGravado;
    }

    public void setImporteNetoGravado(BigDecimal importeNetoGravado) {
        this.importeNetoGravado = importeNetoGravado;
    }

    public AfipAlicuotaIva getAfipAlicuotaIva() {
        return afipAlicuotaIva;
    }

    public void setAfipAlicuotaIva(AfipAlicuotaIva afipAlicuotaIva) {
        this.afipAlicuotaIva = afipAlicuotaIva;
    }

    public BigDecimal getAlicuotaIVA() {
        return alicuotaIVA;
    }

    public void setAlicuotaIVA(BigDecimal alicuotaIVA) {
        this.alicuotaIVA = alicuotaIVA;
    }

    public BigDecimal getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(BigDecimal importeIva) {
        this.importeIva = importeIva;
    }

    public BigDecimal getImporteNoGravado() {
        return importeNoGravado;
    }

    public void setImporteNoGravado(BigDecimal importeNoGravado) {
        this.importeNoGravado = importeNoGravado;
    }

    public BigDecimal getImporteExento() {
        return importeExento;
    }

    public void setImporteExento(BigDecimal importeExento) {
        this.importeExento = importeExento;
    }

    public BigDecimal getImporteImpuestoInterno() {
        return importeImpuestoInterno;
    }

    public void setImporteImpuestoInterno(BigDecimal importeImpuestoInterno) {
        this.importeImpuestoInterno = importeImpuestoInterno;
    }

    public PlanCuenta getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(PlanCuenta cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public BigDecimal getItcPorLitro() {
        return itcPorLitro;
    }

    public void setItcPorLitro(BigDecimal itcPorLitro) {
        this.itcPorLitro = itcPorLitro;
    }

    public BigDecimal getImporteITC() {
        return importeITC;
    }

    public void setImporteITC(BigDecimal importeITC) {
        this.importeITC = importeITC;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}

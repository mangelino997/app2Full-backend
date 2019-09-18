//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase ClienteVtoPago
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "clientevtopago")
public class ClienteVtoPago extends ObjetoGenerico {

    //Referencia a la clase cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    
    //Referencia a la clase empresa
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
    
    //Define diasFechaFactura
    @Column(name = "diasFechaFactura", nullable = true)
    private short diasFechaFactura;
    
    //Define enero
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "enero", nullable = true)
    private Date enero;
    
    //Define febrero
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "febrero", nullable = true)
    private Date febrero;
    
    //Define marzo
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "marzo", nullable = true)
    private Date marzo;
    
    //Define abril
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "abril", nullable = true)
    private Date abril;
    
    //Define mayo
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "mayo", nullable = true)
    private Date mayo;
    
    //Define junio
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "junio", nullable = true)
    private Date junio;
    
    //Define julio
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "julio", nullable = true)
    private Date julio;
    
    //Define agosto
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "agosto", nullable = true)
    private Date agosto;
    
    //Define septiembre
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "septiembre", nullable = true)
    private Date septiembre;
    
    //Define octubre
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "octubre", nullable = true)
    private Date octubre;
    
    //Define noviembre
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "noviembre", nullable = true)
    private Date noviembre;
    
    //Define diciembre
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    @Column(name = "diciembre", nullable = true)
    private Date diciembre;
    
    //Getters y Setters de la clase

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public short getDiasFechaFactura() {
        return diasFechaFactura;
    }

    public void setDiasFechaFactura(short diasFechaFactura) {
        this.diasFechaFactura = diasFechaFactura;
    }

    public Date getEnero() {
        return enero;
    }

    public void setEnero(Date enero) {
        this.enero = enero;
    }

    public Date getFebrero() {
        return febrero;
    }

    public void setFebrero(Date febrero) {
        this.febrero = febrero;
    }

    public Date getMarzo() {
        return marzo;
    }

    public void setMarzo(Date marzo) {
        this.marzo = marzo;
    }

    public Date getAbril() {
        return abril;
    }

    public void setAbril(Date abril) {
        this.abril = abril;
    }

    public Date getMayo() {
        return mayo;
    }

    public void setMayo(Date mayo) {
        this.mayo = mayo;
    }

    public Date getJunio() {
        return junio;
    }

    public void setJunio(Date junio) {
        this.junio = junio;
    }

    public Date getJulio() {
        return julio;
    }

    public void setJulio(Date julio) {
        this.julio = julio;
    }

    public Date getAgosto() {
        return agosto;
    }

    public void setAgosto(Date agosto) {
        this.agosto = agosto;
    }

    public Date getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(Date septiembre) {
        this.septiembre = septiembre;
    }

    public Date getOctubre() {
        return octubre;
    }

    public void setOctubre(Date octubre) {
        this.octubre = octubre;
    }

    public Date getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(Date noviembre) {
        this.noviembre = noviembre;
    }

    public Date getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(Date diciembre) {
        this.diciembre = diciembre;
    }

}
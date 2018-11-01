//Paquete al que pertenece la clase
package ar.com.draimo.jitws.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Cliente
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

@Entity
@Table(name = "cliente")
public class Cliente extends ObjetoGenerico {
    
    //Define la razon social
    @Column(name = "razonSocial", nullable = false)
    private String razonSocial;
    
    //Define el nombre de fantasia
    @Column(name = "nombreFantasia", nullable = true)
    private String nombreFantasia;
    
    //Referencia a la clase Cuenta principal
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCuentaPrincipal", nullable = true)
    private Cliente cuentaPrincipal;
    
    //Define el domicilio
    @Column(name = "domicilio", nullable = false)
    private String domicilio;
    
    //Referencia a la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLocalidad", nullable = false)
    private Localidad localidad;
    
    //Referencia a la clase Barrio
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBarrio", nullable = true)
    private Barrio barrio;
    
    //Define el telefono
    @Column(name = "telefono", nullable = true)
    private String telefono;
    
    //Define el sitio web
    @Column(name = "sitioWeb", nullable = true)
    private String sitioWeb;
    
    //Referencia a la clase Zona
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idZona", nullable = false)
    private Zona zona;
    
    //Referencia a la clase Rubro
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idRubro", nullable = false)
    private Rubro rubro;
    
    //Referencia a la clase Cobrador
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCobrador", nullable = false)
    private Cobrador cobrador;
    
    //Referencia a la clase Cobrador
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idVendedor", nullable = true)
    private Vendedor vendedor;
    
    //Referencia a la clase Condicion de iva
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCondicionIva", nullable = false)
    private CondicionIva condicionIva;
    
    //Referencia a la clase Tipo de documento
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;
    
    //Define el numeroDocumento
    @Column(name = "numeroDocumento", nullable = false)
    private String numeroDocumento;
    
    //Define el codigo de ingresos brutos
    @Column(name = "numeroIIBB", nullable = true)
    private String numeroIIBB;
    
    //Define si es cuenta corriente
    @Column(name = "esCuentaCorriente", nullable = false)
    private boolean esCuentaCorriente;
    
    //Referencia a la clase Resumen Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idResumenCliente", nullable = true)
    private ResumenCliente resumenCliente;
    
    //Referencia a la clase Situacion Cliente
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSituacionCliente", nullable = true)
    private SituacionCliente situacionCliente;
    
    //Referencia a la clase Orden de venta
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idOrdenVenta", nullable = true)
    private OrdenVenta ordenVenta;
    
    //Referencia a la clase Sucursal lugar de pago
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idSucursalLugarPago", nullable = true)
    private Sucursal sucursalLugarPago;
    
    //Define el codigo de ingresos brutos
    @Column(name = "creditoLimite", nullable = true)
    private BigDecimal creditoLimite;
    
    //Define el descuento flete
    @Column(name = "descuentoFlete", nullable = true)
    private BigDecimal descuentoFlete;
    
    //Define el descuento subtotal
    @Column(name = "descuentoSubtotal", nullable = true)
    private BigDecimal descuentoSubtotal;
    
    //Define el seguro propio
    @Column(name = "esSeguroPropio", nullable = true)
    private boolean esSeguroPropio;
    
    //Referencia a la clase Compañia seguro
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCompaniaSeguro", nullable = true)
    private CompaniaSeguro companiaSeguro;
    
    //Define el numero de poliza del seguro
    @Column(name = "numeroPolizaSeguro", nullable = true)
    private String numeroPolizaSeguro;
    
    //Define el vencimiento de la poliza del seguro
    @Column(name = "vencimientoPolizaSeguro", nullable = true)
    private LocalDate vencimientoPolizaSeguro;
    
    //Define las observaciones
    @Column(name = "observaciones", nullable = true)
    private String observaciones;
    
    //Define la nota emision comprobante
    @Column(name = "notaEmisionComprobante", nullable = true)
    private String notaEmisionComprobante;
    
    //Define la nota impresion comprobante
    @Column(name = "notaImpresionComprobante", nullable = true)
    private String notaImpresionComprobante;
    
    //Define la nota impresion remite
    @Column(name = "notaImpresionRemito", nullable = true)
    private String notaImpresionRemito;
    
    //Define imprimir control de deuda
    @Column(name = "imprimirControlDeuda", nullable = false)
    private boolean imprimirControlDeuda;
    
    //Referencia a la clase Usuario (Alta)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioAlta", nullable = false)
    private Usuario usuarioAlta;
    
    //Referencia a la clase Usuario (Baja)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioBaja", nullable = true)
    private Usuario usuarioBaja;
    
    //Define la fecha de baja del cliente
    @Column(name = "fechaBaja", nullable = true)
    private LocalDate fechaBaja;
    
    //Referencia a la clase Usuario (Modificacion)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuarioMod", nullable = true)
    private Usuario usuarioMod;
    
    //Define la fecha de ultima modificacion
    @Column(name = "fechaUltimaMod", nullable = true)
    private LocalDate fechaUltimaMod;
    
    //Define el alias para las busquedas
    @Column(name = "alias", nullable = true)
    private String alias;
    
    //Getters y Setters de la clase

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public Cliente getCuentaPrincipal() {
        return cuentaPrincipal;
    }

    public void setCuentaPrincipal(Cliente cuentaPrincipal) {
        this.cuentaPrincipal = cuentaPrincipal;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public Cobrador getCobrador() {
        return cobrador;
    }

    public void setCobrador(Cobrador cobrador) {
        this.cobrador = cobrador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public CondicionIva getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(CondicionIva condicionIva) {
        this.condicionIva = condicionIva;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroIIBB() {
        return numeroIIBB;
    }

    public void setNumeroIIBB(String numeroIIBB) {
        this.numeroIIBB = numeroIIBB;
    }

    public boolean getEsCuentaCorriente() {
        return esCuentaCorriente;
    }

    public void setEsCuentaCorriente(boolean esCuentaCorriente) {
        this.esCuentaCorriente = esCuentaCorriente;
    }

    public ResumenCliente getResumenCliente() {
        return resumenCliente;
    }

    public void setResumenCliente(ResumenCliente resumenCliente) {
        this.resumenCliente = resumenCliente;
    }

    public SituacionCliente getSituacionCliente() {
        return situacionCliente;
    }

    public void setSituacionCliente(SituacionCliente situacionCliente) {
        this.situacionCliente = situacionCliente;
    }

    public OrdenVenta getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
    }
    
    public Sucursal getSucursalLugarPago() {
        return sucursalLugarPago;
    }

    public void setSucursalLugarPago(Sucursal sucursalLugarPago) {
        this.sucursalLugarPago = sucursalLugarPago;
    }

    public BigDecimal getCreditoLimite() {
        return creditoLimite;
    }

    public void setCreditoLimite(BigDecimal creditoLimite) {
        this.creditoLimite = creditoLimite;
    }

    public BigDecimal getDescuentoFlete() {
        return descuentoFlete;
    }

    public void setDescuentoFlete(BigDecimal descuentoFlete) {
        this.descuentoFlete = descuentoFlete;
    }

    public BigDecimal getDescuentoSubtotal() {
        return descuentoSubtotal;
    }

    public void setDescuentoSubtotal(BigDecimal descuentoSubtotal) {
        this.descuentoSubtotal = descuentoSubtotal;
    }

    public boolean getEsSeguroPropio() {
        return esSeguroPropio;
    }

    public void setEsSeguroPropio(boolean esSeguroPropio) {
        this.esSeguroPropio = esSeguroPropio;
    }

    public CompaniaSeguro getCompaniaSeguro() {
        return companiaSeguro;
    }

    public void setCompaniaSeguro(CompaniaSeguro companiaSeguro) {
        this.companiaSeguro = companiaSeguro;
    }

    public String getNumeroPolizaSeguro() {
        return numeroPolizaSeguro;
    }

    public void setNumeroPolizaSeguro(String numeroPolizaSeguro) {
        this.numeroPolizaSeguro = numeroPolizaSeguro;
    }

    public LocalDate getVencimientoPolizaSeguro() {
        return vencimientoPolizaSeguro;
    }

    public void setVencimientoPolizaSeguro(LocalDate vencimientoPolizaSeguro) {
        this.vencimientoPolizaSeguro = vencimientoPolizaSeguro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNotaEmisionComprobante() {
        return notaEmisionComprobante;
    }

    public void setNotaEmisionComprobante(String notaEmisionComprobante) {
        this.notaEmisionComprobante = notaEmisionComprobante;
    }

    public String getNotaImpresionComprobante() {
        return notaImpresionComprobante;
    }

    public void setNotaImpresionComprobante(String notaImpresionComprobante) {
        this.notaImpresionComprobante = notaImpresionComprobante;
    }

    public String getNotaImpresionRemito() {
        return notaImpresionRemito;
    }

    public void setNotaImpresionRemito(String notaImpresionRemito) {
        this.notaImpresionRemito = notaImpresionRemito;
    }

    public boolean getImprimirControlDeuda() {
        return imprimirControlDeuda;
    }

    public void setImprimirControlDeuda(boolean imprimirControlDeuda) {
        this.imprimirControlDeuda = imprimirControlDeuda;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public Usuario getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(Usuario usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Usuario getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(Usuario usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public LocalDate getFechaUltimaMod() {
        return fechaUltimaMod;
    }

    public void setFechaUltimaMod(LocalDate fechaUltimaMod) {
        this.fechaUltimaMod = fechaUltimaMod;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
}

package ar.com.draimo.jitws.dto;

/**
 * Define un RolDTO
 * @author blas
 */
public class ViajeRemitoDTO {
    
    //Define id del remito
    private int idRemito;
    
    //Define id del viaje
    private int idViaje;
    
    //Define la fecha
    private String fechaDesde;
    
    //Define la fecha
    private String fechaHasta;
    
     //Referencia a la clase Sucursal
    private int idSucursalIngreso;
    
    //Referencia a la clase Sucursal
    private int idSucursalDestino;
    
    //Referencia a la clase Cliente
    private int idClienteRemitente;
    
    //Referencia a la clase Cliente
    private int idClienteDestinatario;
    
    //Define el numero de camion
    private short numeroCamion;
    
    //Define si esta facturado
    private boolean estaFacturado;

    //Getters y setters del DTO
    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        this.idRemito = idRemito;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }
    
    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getIdSucursalIngreso() {
        return idSucursalIngreso;
    }

    public void setIdSucursalIngreso(int idSucursalIngreso) {
        this.idSucursalIngreso = idSucursalIngreso;
    }

    public int getIdSucursalDestino() {
        return idSucursalDestino;
    }

    public void setIdSucursalDestino(int idSucursalDestino) {
        this.idSucursalDestino = idSucursalDestino;
    }

    public int getIdClienteRemitente() {
        return idClienteRemitente;
    }

    public void setIdClienteRemitente(int idClienteRemitente) {
        this.idClienteRemitente = idClienteRemitente;
    }

    public int getIdClienteDestinatario() {
        return idClienteDestinatario;
    }

    public void setIdClienteDestinatario(int idClienteDestinatario) {
        this.idClienteDestinatario = idClienteDestinatario;
    }

    public short getNumeroCamion() {
        return numeroCamion;
    }

    public void setNumeroCamion(short numeroCamion) {
        this.numeroCamion = numeroCamion;
    }

    public boolean isEstaFacturado() {
        return estaFacturado;
    }

    public void setEstaFacturado(boolean estaFacturado) {
        this.estaFacturado = estaFacturado;
    }
    
}
package ar.com.draimo.jitws.dto;

/**
 * Define un RolDTO
 * @author blas
 */
public class ViajeRemitoDTO {
    
     //Referencia a la clase Sucursal
    private int idSucursalIngreso;
    
    //Define la fecha
    private String fechaDesde;
    
    //Define la fecha
    private String fechaHasta;
    
    //Define el numero de camion
    private short numeroCamion;
    
    //Referencia a la clase Sucursal
    private int idSucursalDestino;
    
    //Referencia a la clase Cliente
    private int idClienteRemitente;
    
    //Referencia a la clase Cliente
    private int idClienteDestinatario;
    
    //Referencia a la clase Viaje
    private int idViaje;

    public int getIdSucursalIngreso() {
        return idSucursalIngreso;
    }

    public void setIdSucursalIngreso(int idSucursalIngreso) {
        this.idSucursalIngreso = idSucursalIngreso;
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

    public short getNumeroCamion() {
        return numeroCamion;
    }

    public void setNumeroCamion(short numeroCamion) {
        this.numeroCamion = numeroCamion;
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

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }
    
}

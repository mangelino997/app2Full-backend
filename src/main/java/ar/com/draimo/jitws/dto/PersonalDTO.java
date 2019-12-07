package ar.com.draimo.jitws.dto;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class PersonalDTO {
    
    //Define sucursal
    private int idSucursal;
    
    //Define area
    private int idArea;
    
    //Define categoria
    private int idCategoria;
    
    //Define modContratacion
    private int idModContratacion;
    
    //Define tipoEmpleado
    /*
    * 0 - Todos
    * 1 - Choferes Corta Distancia
    * 2 - Choferes larga Distancia
    * 3 - No choferes
    */
    private int tipoEmpleado;

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdModContratacion() {
        return idModContratacion;
    }

    public void setIdModContratacion(int idModContratacion) {
        this.idModContratacion = idModContratacion;
    }

    public int getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(int tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

}

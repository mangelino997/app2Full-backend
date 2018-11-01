package ar.com.draimo.jitws.dto;

import java.util.List;

/**
 * DTO OpcionPestania
 * Define la estructura JSON para la transferencia de datos.
 * @author blas
 */

public class OpcionPestaniaDTO {
    
    //Define el id
    private int id;
    
    //Define la version
    private int version;
    
    //Define el id rol
    private int idRol;
    
    //Define el id de la opcion
    private int idOpcion;
    
    //Define una lista de idPestanias
    private List<Integer> idPestanias;
    
    //Getters y Setters de la clase

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public List<Integer> getIdPestanias() {
        return idPestanias;
    }

    public void setIdPestanias(List<Integer> idPestanias) {
        this.idPestanias = idPestanias;
    }
    
}
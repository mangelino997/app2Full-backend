package ar.com.draimo.jitws.dto;

/**
 * Subopcion Menu DTO
 * @author blas
 */

public class SubopcionMenuDTO {

    //Define el id
    private int id;
    
    //Define una subopcion
    private String subopcion;

    //Getters y Setters de la clase
    
    public int getId() {
        return id;
    }

    public void setId(int id) {    
        this.id = id;
    }

    public String getSubopcion() {
        return subopcion;
    }

    public void setSubopcion(String subopcion) {
        this.subopcion = subopcion;
    }
    
}

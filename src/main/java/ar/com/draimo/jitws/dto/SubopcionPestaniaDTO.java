//Paquete al que pertenece la clase
package ar.com.draimo.jitws.dto;

import java.util.List;

/**
 * DTO SubopcionPestania
 * Define la estructura JSON para la transferencia de datos
 * @author blas
 */

public class SubopcionPestaniaDTO {
    
    //Define el id
    private int id;
    
    //Define la version
    private int version;
    
    //Define el rol
    private RolDTO rol;
    
    //Define la subopcion
    private SubopcionDTO subopcion;
    
    //Define una lista de pestanias
    private List<PestaniaDTO> pestanias;
    
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

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }

    public SubopcionDTO getSubopcion() {
        return subopcion;
    }

    public void setSubopcion(SubopcionDTO subopcion) {
        this.subopcion = subopcion;
    }

    public List<PestaniaDTO> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<PestaniaDTO> pestanias) {
        this.pestanias = pestanias;
    }
    
}
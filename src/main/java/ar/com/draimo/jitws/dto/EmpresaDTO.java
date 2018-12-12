package ar.com.draimo.jitws.dto;

/**
 * Define una EmpresaDTO
 * @author blas
 */
public class EmpresaDTO {
    
    //Define el id
    private int id;
    
    //Define la version
    private int version;
    
    //Define estado mostrar
    private boolean mostrar;

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

    public boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
    
}

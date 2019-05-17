package ar.com.draimo.jitws.dto;

/**
 * Define un RolDTO
 * @author blas
 */
public class RolDTO {
    
    //Define el id
    private int id;
    
    //Define la version
    private int version;
    
    //Define el nombre
    private String nombre;
    
    //Define el rol secundario
    private int idRolSecundario;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdRolSecundario() {
        return idRolSecundario;
    }

    public void setIdRolSecundario(int idRolSecundario) {
        this.idRolSecundario = idRolSecundario;
    }
    
}

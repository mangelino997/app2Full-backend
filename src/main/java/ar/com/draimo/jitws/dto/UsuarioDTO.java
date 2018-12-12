package ar.com.draimo.jitws.dto;

import java.util.List;

/**
 * Define un UsuarioDTO
 * @author blas
 */
public class UsuarioDTO {
    
    //Define el id
    private int id;
    
    //Define la version
    private int version;
    
    //Define una lista de empresas dto
    private List<EmpresaDTO> empresas;

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

    public List<EmpresaDTO> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<EmpresaDTO> empresas) {
        this.empresas = empresas;
    }
    
}

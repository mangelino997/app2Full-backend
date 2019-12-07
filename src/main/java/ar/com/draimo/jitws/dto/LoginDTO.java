package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Usuario;
import java.util.List;

/**
 * Defeine Login DTO
 * Se utiliza para retornar el usuario, sus empresa activas y
 * el menu completo de ese usuario.
 * @author blas
 */
public class LoginDTO {
    
    //Define el usuario
    private Usuario usuario;
    
    //Define la empresas del usuario
    private List<Empresa> empresas;
    
    //Define el menu principal
    private MenuDTO menuPrincipalDTO;
    
    //Define el menu secundario
    private MenuDTO menuSecundarioDTO;
    
    //Getters y Setters de la clase

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public MenuDTO getMenuPrincipalDTO() {
        return menuPrincipalDTO;
    }

    public void setMenuPrincipalDTO(MenuDTO menuPrincipalDTO) {
        this.menuPrincipalDTO = menuPrincipalDTO;
    }

    public MenuDTO getMenuSecundarioDTO() {
        return menuSecundarioDTO;
    }

    public void setMenuSecundarioDTO(MenuDTO menuSecundarioDTO) {
        this.menuSecundarioDTO = menuSecundarioDTO;
    }
    
}
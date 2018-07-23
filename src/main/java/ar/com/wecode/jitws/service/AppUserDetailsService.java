package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author blas
 */

@Service
public class AppUserDetailsService implements UserDetailsService {
    
    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) 
            throws UsernameNotFoundException {
        Usuario usuario = usuarioService.find(nombreUsuario);
        return  usuario;
    }
    
}

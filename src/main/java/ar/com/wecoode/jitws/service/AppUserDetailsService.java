package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.model.Usuario;
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
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {
        Usuario usuario = usuarioService.obtenerPorUsername(username);
        return  usuario;
    }
    
}

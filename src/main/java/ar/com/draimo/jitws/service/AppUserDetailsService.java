package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.exception.CuentaNoHabilitadaException;
import ar.com.draimo.jitws.model.Usuario;
import static java.util.Collections.emptyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author blas
 */
/* este servicio de Usuario implementa la interfaz 'UserDetailsService' se SPRING y nos obliga
 a sobreescribir la clase 'UsuarioService' */
@Service
public class AppUserDetailsService implements UserDetailsService {
    
    /* Autowired significa que automaticamente va a inyectar algunos atributos (no todos) y que los 
    restantes atributos de la clase se van a inicializar por defecto */
    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, CuentaNoHabilitadaException {
        Usuario usuario = usuarioService.obtenerPorUsername(username);

        /* busca al usuario en la DB y si no lo encuentra lanza la excepcion 'UsernameNotFoundException' */
        if(usuario != null) {
            if(!usuario.getCuentaHabilitada()) {
                throw new CuentaNoHabilitadaException("Cuenta NO Habilitada");
            }
        } else {
            throw new UsernameNotFoundException(username);
        }
        return new User(usuario.getUsername(), usuario.getPassword(), emptyList());
    }
    
}
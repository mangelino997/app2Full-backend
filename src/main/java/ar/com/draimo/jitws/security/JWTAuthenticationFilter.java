package ar.com.draimo.jitws.security;

import ar.com.draimo.jitws.model.Usuario;
import static ar.com.draimo.jitws.security.SecurityConstants.EXPIRATION_TIME;
import static ar.com.draimo.jitws.security.SecurityConstants.HEADER_STRING;
import static ar.com.draimo.jitws.security.SecurityConstants.SECRET;
import static ar.com.draimo.jitws.security.SecurityConstants.TOKEN_PREFIX;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase Filtro de Autenticacion
 * @author blas
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override //metodos sobreescritos
    //La autenticacion recibe el requerimiento (datos) en el Body
    public Authentication attemptAuthentication(HttpServletRequest req, 
            HttpServletResponse res) throws AuthenticationException {
        try {
            //lo que llega en la cabecera de la peticion http se convierte a un string que luego
            // se mapea a la clase Usuario, es decir que debe recibir las mismas propiedades que tiene
            // la clase Usuario (pass, username, etc)
            Usuario creds = new ObjectMapper()
                    .readValue(req.getInputStream(), Usuario.class);
            
            //luego retornamos una atenticacion
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    //si la autenticacion (metodo anterior) fue exitoso
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, 
            FilterChain chain, Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername()) //define el usuario
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //define el tiempo Exp.
                .sign(HMAC512(SECRET.getBytes())); //crea el HASH firmando el Token
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token); //agrega todo al header
    }
    
}

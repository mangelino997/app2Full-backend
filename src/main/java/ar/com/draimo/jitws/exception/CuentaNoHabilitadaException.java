package ar.com.draimo.jitws.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Define Cuenta No Habilitada Exception
 * @author blas
 */
public class CuentaNoHabilitadaException extends AuthenticationException {
    
    public CuentaNoHabilitadaException(String mensaje) {
        super(mensaje);
    }
    
}

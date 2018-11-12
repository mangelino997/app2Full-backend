//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipComprobante;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipComprobanteDAO extends JpaRepository<AfipComprobante, Integer> {
    
    //Obtiene el siguiente id
    public AfipComprobante findTopByOrderByIdDesc();
    
}

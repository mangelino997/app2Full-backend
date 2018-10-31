//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.AfipComprobante;
import java.util.List;
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

//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RetiroDepositoComprobante;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RepartoPropioComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRetiroDepositoComprobanteDAO extends JpaRepository<RetiroDepositoComprobante, Integer> {
    
    //Obtiene el siguiente id
    public RetiroDepositoComprobante findTopByOrderByIdDesc();
    
}

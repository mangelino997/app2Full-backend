//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RetiroDeposito;
import ar.com.draimo.jitws.model.RetiroDepositoComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RepartoPropioComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRetiroDepositoComprobanteDAO extends JpaRepository<RetiroDepositoComprobante, Integer> {
    
    //Obtiene el siguiente id
    public RetiroDepositoComprobante findTopByOrderByIdDesc();
    
    //Obtiene un listado de RepartoTerceroComprobante por idRepartoTercero
    public List<RetiroDepositoComprobante> findByRetiroDeposito(RetiroDeposito retiroDeposito);
    
}

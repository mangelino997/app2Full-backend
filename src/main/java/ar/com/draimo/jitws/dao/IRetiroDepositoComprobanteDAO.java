//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RetiroDeposito;
import ar.com.draimo.jitws.model.RetiroDepositoComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Retiro Deposito Comprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRetiroDepositoComprobanteDAO extends JpaRepository<RetiroDepositoComprobante, Integer> {
    
    //Obtiene el ultimo registro
    public RetiroDepositoComprobante findTopByOrderByIdDesc();
    
    //Obtiene un listado de RetiroDepositoComprobante por RetiroDeposito
    public List<RetiroDepositoComprobante> findByRetiroDeposito(RetiroDeposito retiroDeposito);
    
    //Elimina un listado por retiroDeposito
    public void deleteByRetiroDeposito(RetiroDeposito retiroDeposito);
    
}
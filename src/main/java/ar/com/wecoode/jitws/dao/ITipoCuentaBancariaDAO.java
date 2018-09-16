//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.TipoCuentaBancaria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tipo cuenta bancaria
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoCuentaBancariaDAO extends JpaRepository<TipoCuentaBancaria, Integer> {
    
    //Obtiene el siguiente id
    public TipoCuentaBancaria findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoCuentaBancaria> findByNombreContaining(String nombre);
    
}

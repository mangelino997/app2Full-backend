//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoCuentaContable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tipo cuenta Contable
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoCuentaContableDAO extends JpaRepository<TipoCuentaContable, Integer> {
    
    //Obtiene el ultimo registro
    public TipoCuentaContable findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoCuentaContable> findByNombreContaining(String nombre);
    
}
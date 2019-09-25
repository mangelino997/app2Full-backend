//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipSituacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipSituacion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipSituacionDAO extends JpaRepository<AfipSituacion, Integer> {
    
    //Obtiene el ultimo registro
    public AfipSituacion findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<AfipSituacion> findByAliasContaining(String alias);
    
    //Obtiene una lista ordenada por codigoAfip
    public List<AfipSituacion> findByOrderByCodigoAfipAsc();
    
}

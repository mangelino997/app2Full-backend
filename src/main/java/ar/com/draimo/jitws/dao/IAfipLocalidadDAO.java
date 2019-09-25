//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipLocalidad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipLocalidad
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipLocalidadDAO extends JpaRepository<AfipLocalidad, Integer> {
    
    //Obtiene el ultimo registro
    public AfipLocalidad findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<AfipLocalidad> findByAliasContaining(String alias);
    
    //Obtiene una lista ordenada por codigoAfip
    public List<AfipLocalidad> findByOrderByCodigoAfipAsc();
    
}

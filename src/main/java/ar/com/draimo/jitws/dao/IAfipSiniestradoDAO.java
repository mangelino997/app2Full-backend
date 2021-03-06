//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipSiniestrado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipSiniestrado
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipSiniestradoDAO extends JpaRepository<AfipSiniestrado, Integer> {
    
    //Obtiene el ultimo registro
    public AfipSiniestrado findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<AfipSiniestrado> findByAliasContaining(String alias);
    
    //Obtiene una lista ordenada por codigoAfip
    public List<AfipSiniestrado> findByOrderByCodigoAfipAsc();
    
}

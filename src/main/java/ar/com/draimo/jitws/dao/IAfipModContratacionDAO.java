//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipModContratacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Afip Modalidad contratacion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipModContratacionDAO extends JpaRepository<AfipModContratacion, Integer> {
    
    //Obtiene el ultimo registro
    public AfipModContratacion findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<AfipModContratacion> findByAliasContaining(String alias);
    
    //Obtiene una lista ordenada por codigoAfip
    public List<AfipModContratacion> findByOrderByCodigoAfipAsc();
    
}

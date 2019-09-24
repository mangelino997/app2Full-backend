//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipCondicion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipCondicion 
 * Define los metodos particulares contra la base de
 * datos
 *
 * @author blas
 */
public interface IAfipCondicionDAO extends JpaRepository<AfipCondicion, Integer> {

    //Obtiene el ultimo registro
    public AfipCondicion findTopByOrderByIdDesc();

    //Obtiene una lista por alias
    public List<AfipCondicion> findByAliasContaining(String alias);

    //Obtiene una lista ordenada por codigoAfip
    public List<AfipCondicion> findByOrderByCodigoAfipAsc();

}

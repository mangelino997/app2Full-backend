//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoFamiliar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz tipoFamiliar DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ITipoFamiliarDAO extends JpaRepository<TipoFamiliar, Integer> {
    
    //Obtiene el ultimo registro
    public TipoFamiliar findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<TipoFamiliar> findByNombreContaining(String nombre);
    
}
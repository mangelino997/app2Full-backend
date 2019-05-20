//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.UnidadMedida;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO UnidadMedida
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IUnidadMedidaDAO extends JpaRepository<UnidadMedida, Integer> {
    
    //Obtiene el siguiente id
    public UnidadMedida findTopByOrderByIdDesc();
    
    //Obtiene la lista de registros ordenada por nombre desc
    public List<UnidadMedida> findAllByOrderByNombreAsc();
    
    //Obtiene una lista por nombre
    public List<UnidadMedida> findByNombreContaining(String nombre);
    
}

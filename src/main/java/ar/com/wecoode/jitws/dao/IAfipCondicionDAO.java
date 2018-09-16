//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.AfipCondicion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipCondicion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipCondicionDAO extends JpaRepository<AfipCondicion, Integer> {
    
    //Obtiene el siguiente id
    public AfipCondicion findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<AfipCondicion> findByNombreContaining(String nombre);
    
}

//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipActividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipActividad
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipActividadDAO extends JpaRepository<AfipActividad, Integer> {
    
    //Obtiene el siguiente id
    public AfipActividad findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<AfipActividad> findByNombreContaining(String nombre);
    
}

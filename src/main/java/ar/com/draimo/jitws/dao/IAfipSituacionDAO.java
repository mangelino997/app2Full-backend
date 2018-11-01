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
    
    //Obtiene el siguiente id
    public AfipSituacion findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<AfipSituacion> findByNombreContaining(String nombre);
    
}

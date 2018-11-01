//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.EstadoCivil;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO EstadoCivil
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IEstadoCivilDAO extends JpaRepository<EstadoCivil, Integer> {
    
    //Obtiene el siguiente id
    public EstadoCivil findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<EstadoCivil> findByNombreContaining(String nombre);
    
}

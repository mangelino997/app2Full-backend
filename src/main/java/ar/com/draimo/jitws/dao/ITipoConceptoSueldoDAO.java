//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoConceptoSueldo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO TipoConceptoSueldo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoConceptoSueldoDAO extends JpaRepository<TipoConceptoSueldo, Integer> {
    
    //Obtiene el ultimo registro
    public TipoConceptoSueldo findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<TipoConceptoSueldo> findByNombreContaining(String nombre);
    
}

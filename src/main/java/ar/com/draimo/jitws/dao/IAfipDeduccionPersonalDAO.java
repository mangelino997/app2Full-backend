//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipDeduccionPersonal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipDeduccionPersonal
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipDeduccionPersonalDAO extends JpaRepository<AfipDeduccionPersonal, Integer> {
    
    //Obtiene el ultimo registro
    public AfipDeduccionPersonal findTopByOrderByIdDesc();
    
    //Obtiene una lista por descripcion
    public List<AfipDeduccionPersonal> findByDescripcionContaining(String descripcion);
    
}
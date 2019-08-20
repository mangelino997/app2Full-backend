//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipDeduccionGeneralTope;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipDeduccionGeneralTope
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipDeduccionGeneralTopeDAO extends JpaRepository<AfipDeduccionGeneralTope, Integer> {
    
    //Obtiene el siguiente id
    public AfipDeduccionGeneralTope findTopByOrderByIdDesc();
    
    //Obtiene una lista por descripcion
    public List<AfipDeduccionGeneralTope> findByDescripcionContaining(String descripcion);
    
}

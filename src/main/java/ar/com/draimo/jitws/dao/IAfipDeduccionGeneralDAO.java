//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipDeduccionGeneral;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipDeduccionGeneral
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipDeduccionGeneralDAO extends JpaRepository<AfipDeduccionGeneral, Integer> {
    
    //Obtiene el siguiente id
    public AfipDeduccionGeneral findTopByOrderByIdDesc();
    
    //Obtiene una lista por descripcion
    public List<AfipDeduccionGeneral> findByDescripcionContaining(String descripcion);
    
}
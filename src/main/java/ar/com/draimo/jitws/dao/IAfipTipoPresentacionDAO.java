//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipTipoPresentacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipTipoPresentacion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipTipoPresentacionDAO extends JpaRepository<AfipTipoPresentacion, Integer> {
    
    //Obtiene el siguiente id
    public AfipTipoPresentacion findTopByOrderByIdDesc();
    
    //Obtiene una lista por descripcion
    public List<AfipTipoPresentacion> findByDescripcionContaining(String descripcion);
    
}

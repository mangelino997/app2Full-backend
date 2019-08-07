//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipTipoBeneficio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipTipoBeneficio
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipTipoBeneficioDAO extends JpaRepository<AfipTipoBeneficio, Integer> {
    
    //Obtiene el siguiente id
    public AfipTipoBeneficio findTopByOrderByIdDesc();
    
    //Obtiene una lista por descripcion
    public List<AfipTipoBeneficio> findByDescripcionContaining(String descripcion);
    
}

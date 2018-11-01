//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Caea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Caea
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICaeaDAO extends JpaRepository<Caea, Integer> {
    
    //Obtiene el siguiente id
    public Caea findTopByOrderByIdDesc();
    
}

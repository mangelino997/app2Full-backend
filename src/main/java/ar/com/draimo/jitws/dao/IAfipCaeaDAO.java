//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipCaea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipCaea
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipCaeaDAO extends JpaRepository<AfipCaea, Integer> {
    
    //Obtiene el siguiente id
    public AfipCaea findTopByOrderByIdDesc();
    
}

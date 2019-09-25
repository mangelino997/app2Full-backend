//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipWS;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Banco
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipWSDAO extends JpaRepository<AfipWS, Integer> {
    
    //Obtiene el ultimo registro
    public AfipWS findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<AfipWS> findByNombreContaining(String nombre);
    
}
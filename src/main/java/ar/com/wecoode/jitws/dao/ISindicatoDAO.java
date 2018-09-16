//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Sindicato;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Sindicato
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISindicatoDAO extends JpaRepository<Sindicato, Integer> {
    
    //Obtiene el siguiente id
    public Sindicato findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Sindicato> findByNombreContaining(String nombre);
    
}

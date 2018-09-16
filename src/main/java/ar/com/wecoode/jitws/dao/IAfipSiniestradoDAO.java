//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.AfipSiniestrado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipSiniestrado
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipSiniestradoDAO extends JpaRepository<AfipSiniestrado, Integer> {
    
    //Obtiene el siguiente id
    public AfipSiniestrado findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<AfipSiniestrado> findByNombreContaining(String nombre);
    
}

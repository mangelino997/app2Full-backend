//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Modulo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Modulo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IModuloDAO extends JpaRepository<Modulo, Integer> {
    
    //Obtiene el siguiente id
    public Modulo findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<Modulo> findByNombreContaining(String nombre);
    
}

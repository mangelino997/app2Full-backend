//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Tramo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tramo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITramoDAO extends JpaRepository<Tramo, Integer> {
    
    //Obtiene el siguiente id
    public Tramo findTopByOrderByIdDesc();
    
    //Obtiene un listado por origen
    public List<Tramo> findByOrigen_Nombre(String nombre);
    
}

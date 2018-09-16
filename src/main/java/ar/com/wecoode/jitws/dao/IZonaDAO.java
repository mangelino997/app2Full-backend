//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Zona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Zona
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IZonaDAO extends JpaRepository<Zona, Integer> {
    
    //Obtiene el siguiente id
    public Zona findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Zona> findByNombreContaining(String nombre);
    
}

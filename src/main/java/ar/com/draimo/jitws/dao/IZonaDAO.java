//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Zona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Zona
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IZonaDAO extends JpaRepository<Zona, Integer> {
    
    //Obtiene el ultimo registro
    public Zona findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Zona> findByNombreContaining(String nombre);
    
    //Obtiene una lista ordenada por id
    public List<Zona> findByOrderByIdAsc();
    
    //Obtiene un listado completo ordenado 
    public List<Zona> findAllByOrderByNombreAsc();
    
}
//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Rubro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Rubro
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRubroDAO extends JpaRepository<Rubro, Integer> {
    
    //Obtiene el siguiente id
    public Rubro findTopByOrderByIdDesc();
    
    //Obtiene la lista de registros ordenados por nombre asc
    public List<Rubro> findAllByOrderByNombreAsc();
    
    //Obtiene una lista por nombre
    public List<Rubro> findByNombreContaining(String nombre);
    
}

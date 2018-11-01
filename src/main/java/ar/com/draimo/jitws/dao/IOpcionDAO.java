//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Opcion;
import ar.com.draimo.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Opcion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOpcionDAO extends JpaRepository<Opcion, Integer> {
    
    //Obtiene el siguiente id
    public Opcion findTopByOrderByIdDesc();
    
    //Obtiene la lista de opciones de una subopcion
    public List<Opcion> findBySubopcion(Optional<Subopcion> subopcion);
    
    //Obtiene un listado por nombre
    public List<Opcion> findByNombreContaining(String nombre);
    
}

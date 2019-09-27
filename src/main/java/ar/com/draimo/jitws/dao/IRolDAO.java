//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Rol
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRolDAO extends JpaRepository<Rol, Integer> {
    
    //Obtiene el ultimo registro
    public Rol findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre y que no es desarrollador
    public List<Rol> findByNombreContainingAndEsDesarrolladorFalse(String nombre);
    
    //Obtiene todos los registros no desarrolladores
    public List<Rol> findAllByEsDesarrolladorFalse();
    
}
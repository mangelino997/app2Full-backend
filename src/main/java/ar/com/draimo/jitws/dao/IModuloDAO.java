//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Modulo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Modulo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IModuloDAO extends JpaRepository<Modulo, Integer> {
    
    //Obtiene el ultimo registro
    public Modulo findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<Modulo> findByNombreContaining(String nombre);
    
}
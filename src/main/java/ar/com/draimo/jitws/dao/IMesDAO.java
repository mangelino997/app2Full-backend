//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Mes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Mes
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMesDAO extends JpaRepository<Mes, Integer> {
    
    //Obtiene el siguiente id
    public Mes findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Mes> findByNombreContaining(String nombre);
    
}

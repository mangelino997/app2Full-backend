//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CondicionIva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Condicion iva
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICondicionIvaDAO extends JpaRepository<CondicionIva, Integer> {
    
    //Obtiene el siguiente id
    public CondicionIva findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<CondicionIva> findByNombreContaining(String nombre);
    
}

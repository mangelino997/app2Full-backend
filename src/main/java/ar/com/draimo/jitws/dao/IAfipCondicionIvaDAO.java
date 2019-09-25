//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipCondicionIva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Afip Condicion Iva
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipCondicionIvaDAO extends JpaRepository<AfipCondicionIva, Integer> {
    
    //Obtiene el ultimo registro
    public AfipCondicionIva findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<AfipCondicionIva> findByNombreContaining(String nombre);
    
}

//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;
import ar.com.draimo.jitws.model.OrdenRecoleccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OrdenRecoleccion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenRecoleccionDAO extends JpaRepository<OrdenRecoleccion, Integer> {
    
    //Obtiene el siguiente id
    public OrdenRecoleccion findTopByOrderByIdDesc();
    
    //Obtiene por alias
    public List<OrdenRecoleccion> findByAliasContaining(String alias);
    
}

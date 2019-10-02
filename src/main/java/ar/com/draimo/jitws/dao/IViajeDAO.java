//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Viaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Viaje 
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeDAO extends JpaRepository<Viaje, Integer> {
    
    //Obtiene el ultimo registro
    public Viaje findTopByOrderByIdDesc();
    
    //Obtiene una lista de registros por alias
    public List<Viaje> findByAliasContaining(String alias);
}
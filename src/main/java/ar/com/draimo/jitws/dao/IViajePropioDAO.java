//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajePropio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Viaje Propio
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioDAO extends JpaRepository<ViajePropio, Integer> {
    
    //Obtiene el siguiente id
    public ViajePropio findTopByOrderByIdDesc();
    
    //Obtiene un registro por id
    @Query(value = "SELECT * FROM viajepropio WHERE id=:id", nativeQuery = true)
    public ViajePropio obtenerPorId(@Param("id") int id);
    
    //Obtiene una lista de registros por alias
    public List<ViajePropio> findByAliasContaining(String alias);
    
}

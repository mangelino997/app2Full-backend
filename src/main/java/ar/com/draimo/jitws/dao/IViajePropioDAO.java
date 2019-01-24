//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajePropio;
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
    
    //Obtiene por id
    @Query(value = "SELECT * FROM viajepropio WHERE id=:id", nativeQuery = true)
    public ViajePropio obtenerPorId(@Param("id") int id);
    
}

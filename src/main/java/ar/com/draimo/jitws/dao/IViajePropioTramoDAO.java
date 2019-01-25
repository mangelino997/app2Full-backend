//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajePropio;
import ar.com.draimo.jitws.model.ViajePropioTramo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Viaje Propio Tramo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioTramoDAO extends JpaRepository<ViajePropioTramo, Integer> {
    
    //Obtiene el siguiente id
    public ViajePropioTramo findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajePropioTramo> findByViajePropio(ViajePropio viajePropio);
    
    //Obtiene por viaje propio y tramo
    @Query(value = "SELECT * FROM viajepropiotramo WHERE idViajePropio=:idViajePropio AND idTramo=:idTramo ",nativeQuery = true)
    public ViajePropioTramo findByViajePropioAndTramo(@Param("idViajePropio") int idViajePropio, @Param("idTramo") int idTramo);
    
}

//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.Tramo;
import ar.com.wecode.jitws.model.ViajePropio;
import ar.com.wecode.jitws.model.ViajePropioTramo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Viaje Propio Tramo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioTramoDAO extends JpaRepository<ViajePropioTramo, Integer> {
    
    public final String NOMBRE_TABLA = "viajepropiotramo";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por viaje propio
    public List<ViajePropioTramo> findByViajePropio(Optional<ViajePropio> viajePropio);
    
    //Obtiene por viaje propio y tramo
    public ViajePropioTramo findByViajePropioAndTramo(Optional<ViajePropio> viajePropio, Optional<Tramo> tramo);
    
}

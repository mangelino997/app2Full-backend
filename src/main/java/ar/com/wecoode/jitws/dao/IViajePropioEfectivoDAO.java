//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.ViajePropio;
import ar.com.wecoode.jitws.model.ViajePropioEfectivo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Viaje Propio Efectivo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioEfectivoDAO extends JpaRepository<ViajePropioEfectivo, Integer> {
    
    public final String NOMBRE_TABLA = "viajepropioefectivo";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por viaje propio
    public List<ViajePropioEfectivo> findByViajePropio(Optional<ViajePropio> viajePropio);
    
}

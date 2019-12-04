//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeCierreDocumentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO ViajeCierreDocumentacion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeCierreDocumentacionDAO extends JpaRepository<ViajeCierreDocumentacion, Integer> {
    
    //Obtiene el ultimo registro
    public ViajeCierreDocumentacion findTopByOrderByIdDesc();
    
    //Obtiene el ultimo cierre de un vehiculo
    @Query(value = "SELECT vcd.* FROM viajecierredocumentacion vcd "
            + "INNER JOIN viaje v on vcd.id=v.idViajeCierreDocumentacion "
            + "WHERE v.idVehiculo=:idVehiculo ORDER BY vcd.fecha DESC LIMIT 1", nativeQuery = true)
    public ViajeCierreDocumentacion obtenerUltimoCierreDeVehiculo(@Param("idVehiculo") int idVehiculo);
    
}
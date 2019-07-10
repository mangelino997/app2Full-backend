//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Viaje;
import ar.com.draimo.jitws.model.ViajeTramo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Viaje Tramo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTramoDAO extends JpaRepository<ViajeTramo, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTramo findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio
    public List<ViajeTramo> findByViaje(Viaje viaje);
    
    //Obtiene por viaje propio y tramo
    @Query(value = "SELECT * FROM viajetramo WHERE idViaje=:idViaje AND idTramo=:idTramo ",nativeQuery = true)
    public ViajeTramo findByViajeAndTramo(@Param("idViaje") int idViaje, @Param("idTramo") int idTramo);
    
}

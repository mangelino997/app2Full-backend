//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.RepartoPropio;
import ar.com.draimo.jitws.model.RepartoPropioComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO RepartoPropioComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRepartoPropioComprobanteDAO extends JpaRepository<RepartoPropioComprobante, Integer> {
    
    //Obtiene el siguiente id
    public RepartoPropioComprobante findTopByOrderByIdDesc();
    
    //Obtiene un listado de RepartoPropioComprobante por idRepartoPropio
    public List<RepartoPropioComprobante> findByRepartoPropio(RepartoPropio repartoPropio);
    
    //Obtiene si un registro ya existe o no
    @Query(value = "SELECT * FROM repartopropiocomprobante WHERE "
            + "(:idVentaComprobante IS NULL OR idVentaComprobante=:idVentaComprobante) "
            + "AND (:idViajeRemito IS NULL OR idViajeRemito=:idViajeRemito) "
            + "AND (:idOrdenRecoleccion IS NULL OR idOrdenRecoleccion=:idOrdenRecoleccion) "
            + "AND idRepartoPropio=:idRepartoPropio", nativeQuery = true)
    public RepartoPropioComprobante obtenerExsistente(@Param("idVentaComprobante") int idVentaComprobante,
            @Param("idViajeRemito") int idViajeRemito, @Param("idOrdenRecoleccion") int idOrdenRecoleccion,
            @Param("idRepartoPropio") int idRepartoPropio);
    
}

//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.dto.TipoTarifaDTO;
import ar.com.draimo.jitws.model.TipoTarifa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO TipoTarifa Define los metodos particulares contra la base de
 * datos
 *
 * @author blas
 */
public interface ITipoTarifaDAO extends JpaRepository<TipoTarifa, Integer> {

    //Obtiene el ultimo registro
    public TipoTarifa findTopByOrderByIdDesc();

    //Obtiene una lista por nombre
    public List<TipoTarifa> findByNombreContaining(String nombre);

    //Obtiene una lista por escala true
    public List<TipoTarifa> findByPorEscalaTrue();

    //Obtiene una lista por escala false
    public List<TipoTarifa> findByPorEscalaFalse();

    //Obtiene un listado de tipos tarifas pertenecientes a una ordenVenta
    @Query(value = "SELECT t.*, ordenventatarifa.id FROM tipotarifa t, ordenventatarifa INNER JOIN ordenventatarifa v ON "
            + "t.id=v.idTipoTarifa INNER JOIN ordenventa o ON o.id=v.idOrdenVenta "
            + "WHERE o.id=:idOrdenVenta ORDER BY t.nombre ASC", nativeQuery = true)
    public List<TipoTarifa> listarPorOrdenVenta(@Param("idOrdenVenta") int idOrdenVenta);

    //Obtiene un listado de tipos tarifas pertenecientes a una ordenVenta
    @Query(value = "SELECT t.*, ov.id as idOrdenVentaTarifa FROM tipotarifa t, ordenventatarifa ov where ov.idOrdenVenta=:idOrdenVenta and "
            + "ov.idTipoTarifa = t.id ORDER BY t.nombre ASC", nativeQuery = true)
    public List<TipoTarifaDTO> listarTarifasPorOrdenVenta(@Param("idOrdenVenta") int idOrdenVenta);

}

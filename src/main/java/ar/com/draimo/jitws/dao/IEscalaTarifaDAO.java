//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.EscalaTarifa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO EscalaTarifa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IEscalaTarifaDAO extends JpaRepository<EscalaTarifa, Integer> {
    
    //Obtiene el ultimo registro
    public EscalaTarifa findTopByOrderByIdDesc();
    
    //Obtiene dos escala tarifas por ordenVenta para buscar el promedio de valores entre cada escala
    @Query(value = "SELECT t.* FROM ordenventaescala e inner join escalatarifa t"
            + " on t.id=e.idEscalaTarifa inner join ordenventatarifa v on v.id=e.idOrdenVentaTarifa "
            + "where v.idOrdenVenta=:idOrdenVenta order by t.valor "
            + "desc limit 2", nativeQuery = true)
    public List<EscalaTarifa> obtenerDosEscalasporIdOrdenVenta(
            @Param("idOrdenVenta") int idOrdenVenta);
    
    //Obtiene una lista de escala tarifas por ordenVenta y tipoTarifa para buscar el promedio de valores entre cada escala
    @Query(value = "SELECT t.* FROM ordenventaescala e inner join escalatarifa t"
            + " on t.id=e.idEscalaTarifa inner join ordenventatarifa v on v.id=e.idOrdenVentaTarifa "
            + "where v.idOrdenVenta=:idOrdenVenta and v.idTipoTarifa =:idTipoTarifa order by t.valor ", nativeQuery = true)
    public List<EscalaTarifa> obtenerEscalasporIdOrdenVentaYIdTipoTarifa(
            @Param("idOrdenVenta") int idOrdenVenta, @Param("idTipoTarifa") int idTipoTarifa);
    
    //Obtiene un listado pordenado por valor
    @Query(value = "SELECT * FROM escalatarifa order by valor", nativeQuery = true)
    public List<EscalaTarifa> listarOrdenadoPorValor();
    
}
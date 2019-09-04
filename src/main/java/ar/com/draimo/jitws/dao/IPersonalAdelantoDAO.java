//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.PersonalAdelanto;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO PersonalAdelanto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPersonalAdelantoDAO extends JpaRepository<PersonalAdelanto, Integer> {
    
    //Obtiene el siguiente id
    public PersonalAdelanto findTopByOrderByIdDesc();
    
    //Obtiene el id viaje del personal adelanto
    @Query(value = "SELECT idViaje FROM personaladelanto WHERE id=:idPersonalAdelanto", nativeQuery = true)
    public String obtenerIdViaje(@Param("idPersonalAdelanto") int idPersonalAdelanto);
    
    //Obtiene el id reparto del personal adelanto
    @Query(value = "SELECT idReparto FROM personaladelanto WHERE id=:idPersonalAdelanto", nativeQuery = true)
    public String obtenerIdReparto(@Param("idPersonalAdelanto") int idPersonalAdelanto);
    
    //Obtiene un listado por lote y/o fechaEmision
    @Query(value = "SELECT numeroLote, importe, observaciones, idUsuarioAlta FROM "
            + "personaladelanto WHERE (:numeroLote=0 OR numeroLote=:numeroLote) AND "
            + "(:fechaEmision IS NULL OR fechaEmision=:fechaEmision) GROUP BY numeroLote", nativeQuery = true)
    public Object listarPorNumeroLote(@Param("numeroLote") int numeroLote, @Param("fechaEmision") Date fechaEmision);
    
    //Obtiene un listado por filtros
    @Query(value = "SELECT * FROM personaladelanto a INNER JOIN personal p ON "
            + "p.id=a.idPersonal WHERE idEmpresa=:idEmpresa AND idSucursal=:idSucursal "
            + "AND fechaEmision BETWEEN :fechaDesde AND :fechaHasta AND estaAnulado=:estaAnulado "
            + "AND (estado>:estado OR estado=1)AND alias LIKE %:alias%", nativeQuery = true)
    public List<PersonalAdelanto> listarPorFiltros(@Param("idEmpresa") int idEmpresa,
            @Param("idSucursal") int idSucursal, @Param("fechaDesde") Date fechaDesde,
            @Param("fechaHasta") Date fechaHasta, @Param("estaAnulado") boolean estaAnulado,
            @Param("alias") String alias, @Param("estado") int estado);
    
}
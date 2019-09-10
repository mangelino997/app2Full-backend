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
    
    //Obtiene el siguiente numero de lote
    public PersonalAdelanto findTopByOrderByNumeroLoteDesc();
    
    //Obtiene el id viaje del personal adelanto
    @Query(value = "SELECT idViaje FROM personaladelanto WHERE id=:idPersonalAdelanto", nativeQuery = true)
    public String obtenerIdViaje(@Param("idPersonalAdelanto") int idPersonalAdelanto);
    
    //Obtiene el id reparto del personal adelanto
    @Query(value = "SELECT idReparto FROM personaladelanto WHERE id=:idPersonalAdelanto", nativeQuery = true)
    public String obtenerIdReparto(@Param("idPersonalAdelanto") int idPersonalAdelanto);
    
    
    /*Obtiene un listado de adelantos por empresa, sucursal, una fecha de emision que se
    encuentre entre una fecha (fechaDesde) y otra (FechaHasta), esta anulado, que depende
    de dos variables (anulado) y (estaAnulado): Si la variable anulado llega en false, lista
    por todos los registros anulados y no anulados. si llega en true lista por anulado o no
    segun lo que la varioable estaAnulado contenga. Estado es para saber si es o no un prestamo
    si llega cero lista todo, si llega mayor 2 lista prestamos y si llega 1 lista adelantos normales.
    Tambien lista por alias del personal que recibe el prestamo. */
    @Query(value = "SELECT * FROM personaladelanto a INNER JOIN personal p ON "
            + "p.id=a.idPersonal WHERE (:idEmpresa = 0 OR a.idEmpresa=:idEmpresa) AND "
            + "(:idSucursal =0 OR a.idSucursal=:idSucursal) AND a.fechaEmision BETWEEN"
            + " :fechaDesde AND :fechaHasta AND (:anulado=false OR a.estaAnulado=:estaAnulado) "
            + "AND (:estado=0 OR (a.totalCuotas>:estado OR a.totalCuotas=1))AND p.alias LIKE %:alias%", nativeQuery = true)
    public List<PersonalAdelanto> listarPorFiltros(@Param("idEmpresa") int idEmpresa,
            @Param("idSucursal") int idSucursal, @Param("fechaDesde") Date fechaDesde,
            @Param("fechaHasta") Date fechaHasta, @Param("estaAnulado") boolean estaAnulado,
            @Param("alias") String alias, @Param("estado") int estado, @Param("anulado") boolean anulado);
    
    /*Obtiene un listado agrupado por lotes de: empresa, sucursal, cantidad de legajos, 
    usuario alta, observaciones y fecha de emision*/
    @Query(value = "SELECT numeroLote, count(id) as legajos, idEmpresa, idSucursal, "
            + "importe, observaciones, fechaEmision,idUsuarioAlta FROM personaladelanto "
            + "where numeroLote > 0 and fechaEmision between :fechaDesde and :fechaHasta "
            + "and idEmpresa=:idEmpresa and estaAnulado= false group by numeroLote, idEmpresa, idSucursal, importe, "
            + "observaciones, fechaEmision, idUsuarioAlta", nativeQuery = true)
    public Object[] listarLotes(@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta")
            Date fechaHasta, @Param("idEmpresa") int idEmpresa);
    
    //Anula los adelantos por lote
    @Query(value = "select * from personaladelanto  where numeroLote =:numeroLote", nativeQuery = true)
    public List<PersonalAdelanto> anularPorLote(@Param("numeroLote") int numeroLote);
    
    }
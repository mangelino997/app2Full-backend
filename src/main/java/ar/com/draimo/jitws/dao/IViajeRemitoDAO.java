//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.ViajeRemito;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO ViajeRemito 
 * Define los metodos particulares contra la base de datos
 *
 * @author blas
 */
public interface IViajeRemitoDAO extends JpaRepository<ViajeRemito, Integer> {

    //Obtiene el ultimo registro
    public ViajeRemito findTopByOrderByIdDesc();

    //Obtiene una lista por alias
    public List<ViajeRemito> findByAliasContaining(String alias);

    //Obtiene la lista por numero
    public List<ViajeRemito> findByNumero(int numero);

    //Obtiene la lista por id
    @Query(value = "SELECT * FROM viajeremito WHERE id=:id", nativeQuery = true)
    public ViajeRemito obtenerPorId(@Param("id") int id);

    //Obtiene una lista de no pendientes por sucursal ingreso
    public List<ViajeRemito> findBySucursalIngresoAndEstaPendienteFalse(Optional<Sucursal> sucursal);

    //Obtiene una lista de pendientes por filtro (sucursalIngreso, sucursalDestino y numero camion)
    
    public List<ViajeRemito> findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndEstaPendienteTrue(
            Sucursal sucursal, Sucursal sucursalDestino, short numeroCamion);
    
    //Obtiene una lista de asignados por filtros(sucursalIngreso, sucursalDestino y numero camion)
    public List<ViajeRemito> findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndEstaPendienteFalse(
            Sucursal sucursal, Sucursal sucursalDestino, short numeroCamion);

    //Obtiene una lista de pendientes por filtro (sucursalIngreso, sucursalDestino,
    //numero camion y viajePropioTramo)
    @Query(value = "SELECT * FROM viajeremito where ((:fechaDesde IS NULL and :fechaHasta "
            + "IS  NULL) OR (fecha between :fechaDesde and :fechaHasta)) and (:idSucursalIngreso "
            + "= 0 or idSucursalIngreso=:idSucursalIngreso) and (:idSucursalDestino =0 or "
            + "idSucursalDestino=:idSucursalDestino) and (:idClienteRemitente =0 or "
            + "idClienteRemitente=:idClienteRemitente) and (:idClienteDestinatario=0 or "
            + "idClienteDestinatario=:idClienteDestinatario) and (:numeroCamion=0 or "
            + "numeroCamion=:numeroCamion)", nativeQuery = true)
    public List<ViajeRemito> listarPorFiltros(@Param("fechaDesde") String fechaDesde,
            @Param("fechaHasta") String fechaHasta, @Param("idSucursalIngreso") int idSucursalIngreso,
            @Param("idSucursalDestino") int idSucursalDestino, @Param("idClienteRemitente") 
            int idClienteRemitente,@Param("idClienteDestinatario") int idClienteDestinatario, 
            @Param("numeroCamion") short numeroCamion);

    //Obtiene un registro por puntoVenta, letra y nroComprobante
    @Query(value = "SELECT * FROM viajeremito rpuntoVenta=:puntoVenta AND letra="
            + ":letra AND numero=:numero", nativeQuery = true)
    public ViajeRemito obtenerPorPuntoVentaLetraYNumero(@Param("numero") int numero, 
            @Param("puntoVenta") int puntoVenta, @Param("letra") String letra);
    
    //Obtiene un listado por estaPendienteFalse y idViajeRemito de la tabla viajeTramoRemito
    @Query(value = "SELECT v.* FROM viajeremito v INNER JOIN viajetramoremito t ON t.idViajeRemito=v.id"
            + " WHERE t.idViajeTramo=:idViajeTramo AND v.estaPendiente=false", nativeQuery = true)
    public List<ViajeRemito> listarAsignadosPorViajeTramo(@Param("idViajeTramo") int idViajeRemito);
    
    //Obtiene un listado por registros que no estan asignados a un reparto
    @Query(value = "SELECT * FROM viajeremito WHERE id!=(SELECT s.idViajeRemito "
            + "FROM seguimientoviajeremito s, seguimientoestado e, viajeremito v "
            + "WHERE s.idViajeRemito =v.id and e.id=s.idSeguimientoEstado and "
            + "e.esEntregado=false);", nativeQuery = true)
    public List<ViajeRemito> listarRemitosDisponibles();
    
    
    //Obtiene un listado de letras que corresponden a los comprobantes de viaje remitos cargados
    @Query(value = "SELECT letra FROM viajeremito group by letra", nativeQuery = true)
    public List<String> listarLetras();

    //Obtiene un registro por remitente, destinatario , puntoVenta, letra, numero y tipoComprobante
    @Query(value = "SELECT * FROM viajeremito WHERE idClienteRemitente=:idClienteRemitente"
            + " AND idClienteDestinatario=:idClienteDestinatario AND puntoVenta=:puntoVenta"
            + " AND letra=:letra AND numero=:numero AND idTipoComprobante=:idTipoComprobante", nativeQuery = true)
    public ViajeRemito obtenerComprobanteUnicoParaRemitenteDestinatario(@Param("idClienteRemitente") 
            int idClienteRemitente,@Param("idClienteDestinatario") int idClienteDestinatario,
            @Param("puntoVenta") int puntoVenta, @Param("letra") String letra, 
            @Param("numero") int numero, @Param("idTipoComprobante") int idTipoComprobante);

    //Obtiene un registro por viaje y estado
    @Query(value = "SELECT * FROM viajeremito WHERE (:idViaje = 0 OR idViaje=:idViaje)"
            + " AND (:idViajeRemito = 0 OR idViajeRemito=:idViajeRemito) AND "
            + "estaFacturado=:estaFacturado", nativeQuery = true)
    public List<ViajeRemito> listarPorViajeYEstaFacturado(@Param("idViaje") int idViaje, 
            @Param("idViajeRemito") int idViajeRemito, @Param("estaFacturado") boolean estaFacturado);

    //Obtiene un registro por letra, punto venta y numero que no este en una venta comprobante
    @Query(value = "SELECT r.* FROM viajeremito r WHERE r.id NOT IN (SELECT v.idViajeRemito "
            + "FROM  ventacomprobanteitemFA v ) and r.letra=:letra and r.puntoVenta=:puntoVenta"
            + " and r.numero =:numero;", nativeQuery = true)
    public ViajeRemito obtenerParaReparto(@Param("numero") int numero, 
            @Param("puntoVenta") int puntoVenta, @Param("letra") String letra);
    
}
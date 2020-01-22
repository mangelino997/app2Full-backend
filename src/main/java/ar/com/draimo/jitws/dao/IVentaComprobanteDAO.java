//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.TipoComprobante;
import ar.com.draimo.jitws.model.VentaComprobante;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO VentaComprobante Define los metodos particulares contra la base
 * de datos
 *
 * @author blas
 */
public interface IVentaComprobanteDAO extends JpaRepository<VentaComprobante, Integer> {

    //Obtiene el ultimo registro
    public VentaComprobante findTopByOrderByIdDesc();

    //Obtiene un registro por puntoVenta, letra, nroComprobante y tipoComprobante
    public VentaComprobante findByPuntoVentaAndLetraAndNumeroAndTipoComprobante(int puntoVenta, String letra, int numero, TipoComprobante tc);

    //Obtiene una lista por tipos de comprobantes
    public List<VentaComprobante> findByTipoComprobante(TipoComprobante tipoComprobante);

    //Obtiene una lista por cliente y empresa
    public List<VentaComprobante> findByClienteAndEmpresa(Cliente cliente, Empresa empresa);

    //Obtiene un listado de letras que corresponden a los comprobantes de ventas cargados
    @Query(value = "SELECT letra FROM ventacomprobante group by letra", nativeQuery = true)
    public List<String> listarLetras();

    //Obtiene un listado de registros que no estan en reparto
    @Query(value = "SELECT * FROM ventacomprobante WHERE id!=(SELECT s.idVentaComprobante"
            + " FROM seguimientoventacomprobante s, seguimientoestado e, ventacomprobante v"
            + " WHERE s.idVentaComprobante =v.id and e.id=s.idSeguimientoEstado and "
            + "e.esEntregado=false)", nativeQuery = true)
    public List<VentaComprobante> listarComprobantesDisponibles();

    //Obtiene un listado de comprobantes pendientes (con saldo mayor a 0)
//    @Query(value = "select * from ventacomprobante where idTipoComprobante!=2 or"
//            + " idTipoComprobante!=3 or idTipoComprobante!=27 or idTipoComprobante!=28 "
//            + "and idCliente=:idCliente and idEmpresa=:idEmpresa ", nativeQuery = true)
    @Query(value = "select * from ventacomprobante where importeSaldo > 0"
            + " and idCliente=:idCliente and idEmpresa=:idEmpresa ", nativeQuery = true)
    public List<VentaComprobante> listarParaNotasDeCredito(@Param("idCliente") int idCliente,
            @Param("idEmpresa") int idEmpresa);

    //Obtiene la lista de comprobantes por cliente
    @Query(value = "SELECT v.* FROM cliente c inner join  ventacomprobante v on "
            + "c.id=v.idCliente or  (v.idCliente is null and c.id=v.idCliente) "
            + "where v.importeSaldo>0 and c.id=:idCliente and v.idEmpresa =:idEmpresa", nativeQuery = true)
    public List<VentaComprobante> listarComprobantesPorClienteYEmpresa(@Param("idCliente") int idCliente, @Param("idEmpresa") int idEmpresa);

    /*Obtiene un listado de registros por idEmpresa, idCliente, rango de fecha, tipo de comprobante
    * y el tipo de fecha por la que se quiere listar
    * Si fechaTipo es igual 0 se obtiene por fecha registracion, si es igual a 1 se obtiene 
    * por fecha emision y si es igual a 2 se obtiene por fecha vencimiento pago
    * Si idEmpresa es igual a 0 obtiene todas. Si idCliente es igual a cero obtiene todos.
    * Si idTipoComprobante es igual a 0 obtiene todos.
     */
    @Query(value = "SELECT * FROM ventacomprobante where Case :fechaTipo when 1 then "
            + "(:idSucursal=0 OR idSucursal=:idSucursal) and (:idCliente=0 OR idCliente=:idCliente)"
            + " and fechaEmision between :fechaDesde and :fechaHasta and "
            + "(:idTipoComprobante=0 OR idTipoComprobante=:idTipoComprobante) when 2 "
            + "then (:idSucursal=0 OR idSucursal=:idSucursal) and (:idCliente=0 OR "
            + "idCliente=:idCliente) and fechaVtoPago between :fechaDesde and "
            + ":fechaHasta and (:idTipoComprobante=0 OR idTipoComprobante=:idTipoComprobante) "
            + "when 0 then (:idSucursal=0 OR idSucursal =:idSucursal) and (:idCliente=0 "
            + "OR idCliente=:idCliente) and fechaRegistracion between :fechaDesde "
            + "and :fechaHasta and (:idTipoComprobante=0 OR idTipoComprobante=:idTipoComprobante) end", nativeQuery = true)
    public List<VentaComprobante> listarPorFiltros(@Param("idSucursal") int idSucursal,
            @Param("idCliente") int idCliente, @Param("fechaDesde") Date fechaDesde,
            @Param("fechaHasta") Date fechaHasta, @Param("idTipoComprobante") int idTipoComprobante,
            @Param("fechaTipo") int fechaTipo);

    /*Obtiene un listado de registros por idTipoCpte, puntoVenta, letra, numero*/
    @Query(value = "SELECT * FROM ventacomprobante where "
            + "(:idTipoComprobante=0 OR idTipoComprobante=:idTipoComprobante) and "
            + "puntoVenta=:puntoVenta and letra=:letra and numero=:numero", nativeQuery = true)
    public List<VentaComprobante> listarPorComprobante(@Param("idTipoComprobante") int idTipoComprobante,
            @Param("puntoVenta") int puntoVenta, @Param("letra") String letra,
            @Param("numero") String numero);
    
    /*Obtiene un listado de registros por idTipoCpte, puntoVenta, letra, numero*/
    @Query(value = "SELECT * FROM ventacomprobante where "
            + "(:idTipoComprobante=0 OR idTipoComprobante=:idTipoComprobante) and "
            + "puntoVenta=:puntoVenta and letra=:letra and numero between :numeroDesde and :numeroHasta",
            nativeQuery = true)
    public List<VentaComprobante> listarPorRangoComprobantes(@Param("idTipoComprobante") int idTipoComprobante,
            @Param("puntoVenta") int puntoVenta, @Param("letra") String letra,
            @Param("numeroDesde") String numeroDesde, @Param("numeroHasta") String numeroHasta);
    
    /*Obtiene un listado de registros por idTipoCpte, puntoVenta, letra, numero*/
    @Query(value = "SELECT * FROM ventacomprobante where "
            + "importeTotal between :importeDesde and :importeHasta "
            + "and fechaEmision between :fechaDesde and :fechaHasta",
            nativeQuery = true)
    public List<VentaComprobante> listarPorRangoImportes(
            @Param("importeDesde") BigDecimal importeDesde, @Param("importeHasta") BigDecimal importeHasta,
            @Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);
}

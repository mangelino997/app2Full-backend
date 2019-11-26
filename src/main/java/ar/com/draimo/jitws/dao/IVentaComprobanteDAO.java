//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.TipoComprobante;
import ar.com.draimo.jitws.model.VentaComprobante;
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

    //Obtiene un listado de registros que no son notas de credito/debito
    @Query(value = "select * from ventacomprobante where idTipoComprobante!=2 or"
            + " idTipoComprobante!=3 or idTipoComprobante!=27 or idTipoComprobante!=28 "
            + "and idCliente=:idCliente and idEmpresa=:idEmpresa ", nativeQuery = true)
    public List<VentaComprobante> listarParaNotasDeCredito(@Param("idCliente") int idCliente,
            @Param("idEmpresa") int idEmpresa);

    //Obtiene la lista de comprobantes por cliente
    @Query(value = "SELECT v.* FROM cliente c inner join  ventacomprobante v on "
            + "c.id=v.idClienteGrupo or  (v.idClienteGrupo is null and c.id=v.idCliente) "
            + "where v.importeSaldo>0 and c.id=:idCliente and v.idEmpresa =:idEmpresa", nativeQuery = true)
    public List<VentaComprobante> listarComprobantesPorClienteYEMpresa(@Param("idCliente") 
            int idCliente, @Param("idEmpresa") int idEmpresa);
    
}

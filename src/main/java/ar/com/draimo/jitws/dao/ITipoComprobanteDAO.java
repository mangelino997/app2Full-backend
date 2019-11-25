//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO TipoComprobante Define los metodos particulares contra la base
 * de datos
 *
 * @author blas
 */
public interface ITipoComprobanteDAO extends JpaRepository<TipoComprobante, Integer> {

    //Obtiene el ultimo registro
    public TipoComprobante findTopByOrderByIdDesc();

    //Obtiene un listado por nombre
    public List<TipoComprobante> findByNombreContaining(String nombre);

    //Obtiene una lista por activo reparto
    public List<TipoComprobante> findByEstaActivoRepartoTrue();

    //Obtiene una lista por estaActivoCompraCarga
    public List<TipoComprobante> findByEstaActivoCompraCargaTrue();

    //Obtiene una lista por estaActivoIngresoCarga
    public List<TipoComprobante> findByEstaActivoIngresoCargaTrue();

    //Obtiene una lista por punto de venta
    public List<TipoComprobante> findByNumeracionPuntoVentaTrue();

    //Obtiene los tipos de comprobante para emitir factura
    @Query(value = "SELECT * FROM tipocomprobante where nombre like 'Factura%'"
            + " and id!=10", nativeQuery = true)
    public List<TipoComprobante> listarParaFactura();

    //Obtiene los tipos de comprobante para emitir nota de credito
    @Query(value = "SELECT * FROM tipocomprobante where nombre like 'nota de credito%'",
            nativeQuery = true)
    public List<TipoComprobante> listarParaNotaCredito();

}

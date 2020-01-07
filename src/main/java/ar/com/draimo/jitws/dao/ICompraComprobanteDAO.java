//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompraComprobante;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz CompraComprobante DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ICompraComprobanteDAO extends JpaRepository<CompraComprobante, Integer> {
    
    //Obtiene el ultimo registro
    public CompraComprobante findTopByOrderByIdDesc();
    
    //Obtiene un registro por puntoVenta, codigoAfip, numero y proveedor
    @Query(value = "SELECT * FROM compracomprobante WHERE idProveedor=:idProveedor AND "
            + "codigoAfip=:codigoAfip AND puntoVenta=:puntoVenta AND numero=:numero", nativeQuery = true)
    public List<CompraComprobante> verificarUnicidad(@Param("idProveedor") int idProveedor,
            @Param("codigoAfip") String codigoAfip,@Param("puntoVenta") int puntoVenta,
            @Param("numero") int numero);
    
    /*Obtiene un listado de registros por idEmpresa, idProveedor, rango de fecha, tipo de comprobante
    * y el tipo de fecha por la que se quiere listar
    * Si fechaTipo es igual 0 se obtiene por fecha registracion, si es igual a 1 se obtiene 
    * por fecha emision y si es igual a 2 se obtiene por fechaContable
    * Si idEmpresa es igual a 0 obtiene todas. Si idProveedor es igual a cero obtiene todos.
    * Si idTipoComprobante es igual a 0 obtiene todos.
    */
    @Query(value = "SELECT * FROM compracomprobante where Case :fechaTipo when 1 then "
            + "(:idEmpresa=0 OR idEmpresa=:idEmpresa) and (:idProveedor=0 OR idProveedor=:idProveedor)"
            + " and fechaEmision between :fechaDesde and :fechaHasta and "
            + "(:idTipoComprobante=0 OR idTipoComprobante=:idTipoComprobante) when 2 "
            + "then (:idEmpresa=0 OR idEmpresa=:idEmpresa) and (:idProveedor=0 OR "
            + "idProveedor=:idProveedor) and fechaContable between :fechaDesde and "
            + ":fechaHasta and (:idTipoComprobante=0 OR idTipoComprobante=:idTipoComprobante) "
            + "when 0 then (:idEmpresa=0 OR idEmpresa =:idEmpresa) and (:idProveedor=0 "
            + "OR idProveedor=:idProveedor) and fechaRegistracion between :fechaDesde "
            + "and :fechaHasta and (:idTipoComprobante=0 OR idTipoComprobante=:idTipoComprobante) end", nativeQuery = true)
    public List<CompraComprobante> listarPorFiltros(@Param("idEmpresa") int idEmpresa,
            @Param("idProveedor") int idProveedor, @Param("fechaDesde") Date fechaDesde,
            @Param("fechaHasta") Date fechaHasta, @Param("idTipoComprobante") int idTipoComprobante,
            @Param("fechaTipo") int fechaTipo);
    
    //Obtiene un listadoPorProveedor y Empresa
    @Query(value = "SELECT * FROM compracomprobante where idEmpresa=:idEmpresa and "
            + "idProveedor=:idProveedor and importeSaldo>0", nativeQuery = true)
    public List<CompraComprobante> listarPorEmpresaYProveedor(@Param("idEmpresa") int idEmpresa,
            @Param("idProveedor") int idProveedor);

}
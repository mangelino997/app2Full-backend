//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.PuntoVenta;
import ar.com.draimo.jitws.model.Sucursal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO PuntoVenta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPuntoVentaDAO extends JpaRepository<PuntoVenta, Integer> {
    
    //Obtiene el siguiente id
    public PuntoVenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por sucursal
    public List<PuntoVenta> findBySucursal(Optional<Sucursal> sucursal);
    
    //Obtiene una lista por sucursal y empresa
    @Query(value = "SELECT * FROM puntoventa WHERE idSucursal=:idSucursal AND idEmpresa=:idEmpresa", nativeQuery = true)
    public List<PuntoVenta> listarPorSucursalYEmpresa(@Param("idSucursal") int idSucursal, @Param("idEmpresa") int idEmpresa);
    
    //Obtiene una lista por empresa, sucursal
    @Query(value = "SELECT distinct puntoVenta FROM (SELECT p.puntoVenta, p.porDefecto FROM puntoventa p " +
        "INNER JOIN afipcomprobante a ON p.codigoAfip=a.codigoAfip " +
        "where p.idEmpresa=:idEmpresa and p.idSucursal=:idSucursal and p.fe=1 and p.estaHabilitado=1 AND a.idTipoComprobante=:idTipoComprobante " +
        "group by puntoVenta, porDefecto order by porDefecto desc) b;", nativeQuery = true)
    public List<Object> listarPorEmpresaYSucursal(@Param("idSucursal") int idSucursal, @Param("idEmpresa") int idEmpresa, 
            @Param("idTipoComprobante") int idTipoComprobante);
     
    //Obtiene un registro por puntoVenta y codigoAfip
    public PuntoVenta findByPuntoVentaAndSucursalAndEmpresaAndAfipComprobante_CodigoAfip(int puntoVenta, Sucursal sucursal, Empresa empresa, String codigoAfip);
     
}

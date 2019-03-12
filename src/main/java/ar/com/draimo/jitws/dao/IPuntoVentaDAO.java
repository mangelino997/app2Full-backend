//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.PuntoVenta;
import ar.com.draimo.jitws.model.Sucursal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    public List<PuntoVenta> findBySucursalAndEmpresa(Sucursal sucursal, Empresa empresa);
    
    //Obtiene una lista por empresa, sucursal
    @Query(value = "SELECT distinct puntoVenta FROM (SELECT p.puntoVenta, p.porDefecto FROM puntoventa p " +
        "INNER JOIN afipcomprobante a ON p.codigoAfip=a.codigoAfip " +
        "where p.idEmpresa=1 and p.idSucursal=1 and p.fe=1 and p.estaHabilitado=1 AND a.idTipoComprobante=1 " +
        "group by puntoVenta, porDefecto order by porDefecto desc) b;", nativeQuery = true)
    public List<Object> listarPorEmpresaYSucursal(Sucursal sucursal, Empresa empresa);
     
    //Obtiene un registro por puntoVenta y codigoAfip
    public PuntoVenta findByPuntoVentaAndCodigoAfipAndSucursalAndEmpresa(int puntoVenta, String codigoAfip, Sucursal sucursal, Empresa empresa);
     
}

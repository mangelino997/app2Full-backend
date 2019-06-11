//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.ViajePropioTramo;
import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.model.ViajeTerceroTramo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO ViajeRemito
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeRemitoDAO extends JpaRepository<ViajeRemito, Integer> {
    
    //Obtiene el siguiente id
    public ViajeRemito findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<ViajeRemito> findByAliasContaining(String alias);
    
    //Obtiene la lista por numero
    public List<ViajeRemito> findByNumero(int numero);
    
    //Obtiene una lista de pendientes por sucursal ingreso
    public List<ViajeRemito> findBySucursalIngresoAndEstaPendienteFalse(Optional<Sucursal> sucursal);
    
    //Obtiene una lista de pendientes por filtro (sucursalIngreso, sucursalDestino y numero camion)
    public List<ViajeRemito> findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndEstaPendienteTrue(
            Optional<Sucursal> sucursal, Optional<Sucursal> sucursalDestino, short numeroCamion);
    
//    //Obtiene una lista de pendientes por filtro (sucursalIngreso, sucursalDestino,
//    //numero camion y viajePropioTramo)
//    public List<ViajeRemito> 
//        findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndViajePropioTramoAndEstaPendienteFalse(
//            Optional<Sucursal> sucursal, Optional<Sucursal> sucursalDestino, short numeroCamion,
//                Optional<ViajePropioTramo> viajePropioTramo);
    
    //Obtiene una lista de pendientes por filtro (sucursalIngreso, sucursalDestino,
    //numero camion y viajePropioTramo)
        @Query(value = "SELECT * FROM viajeremito where ((:fechaDesde IS NULL and :fechaHasta IS  NULL) OR (fecha between "
                + ":fechaDesde and :fechaHasta)) and (:idSucursalIngreso = 0 "
                + "or idSucursalIngreso=:idSucursalIngreso) and (:idSucursalDestino "
                + "=0 or idSucursalDestino=:idSucursalDestino) and (:idClienteRemitente "
                + "=0 or idClienteRemitente=:idClienteRemitente) and (:idClienteDestinatario "
                + "=0 or idClienteDestinatario=:idClienteDestinatario) and (:numeroCamion "
                + "=0 or numeroCamion=:numeroCamion)", nativeQuery = true)
    public List<ViajeRemito> listarPorFiltros(@Param("fechaDesde") String fechaDesde,
            @Param("fechaHasta") String fechaHasta,@Param("idSucursalIngreso") int idSucursalIngreso,
            @Param("idSucursalDestino") int idSucursalDestino,@Param("idClienteRemitente") int idClienteRemitente,
            @Param("idClienteDestinatario") int idClienteDestinatario,@Param("numeroCamion") short numeroCamion);
    
//    //Obtiene un listado de remitos por viaje propio
//    public List<ViajeRemito> findByViajePropioTramoAndEstaFacturadoFalse(
//            Optional<ViajePropioTramo> viajePropioTramo);
//    
//    //Obtiene un listado de remitos por viaje tercero
//    public List<ViajeRemito> findByViajeTerceroTramoAndEstaFacturadoFalse(
//            Optional<ViajeTerceroTramo> viajeTerceroTramo);
    
    //Obtiene un registro por puntoVenta, letra y nroComprobante
    public ViajeRemito findByPuntoVentaAndLetraAndNumero(int puntoVenta,String letra,int numero);
    
}

//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.Sucursal;
import ar.com.wecoode.jitws.model.ViajePropioTramo;
import ar.com.wecoode.jitws.model.ViajeRemito;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO ViajeRemito
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeRemitoDAO extends JpaRepository<ViajeRemito, Integer> {
    
    public final String NOMBRE_TABLA = "viajeremito";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene la lista por numero
    public List<ViajeRemito> findByNumeroComprobanteContaining(int numeroComprobante);
    
    //Obtiene una lista de pendientes por sucursal
    public List<ViajeRemito> findBySucursalAndEstaPendienteTrue(Optional<Sucursal> sucursal);
    
    //Obtiene una lista de pendientes por filtro (sucursal, sucursalDestino y numero camion)
    public List<ViajeRemito> findBySucursalAndSucursalDestinoAndNumeroCamionAndEstaPendienteTrue(
            Optional<Sucursal> sucursal, Optional<Sucursal> sucursalDestino, short numeroCamion);
    
    //Obtiene una lista de pendientes por filtro (sucursal, sucursalDestino, numero camion y viajePropioTramo)
    public List<ViajeRemito> findBySucursalAndSucursalDestinoAndNumeroCamionAndViajePropioTramoAndEstaPendienteFalse(
            Optional<Sucursal> sucursal, Optional<Sucursal> sucursalDestino, short numeroCamion, Optional<ViajePropioTramo> viajePropioTramo);
    
}

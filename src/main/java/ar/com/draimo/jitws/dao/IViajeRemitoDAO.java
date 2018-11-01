//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.ViajePropioTramo;
import ar.com.draimo.jitws.model.ViajeRemito;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ViajeRemito
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeRemitoDAO extends JpaRepository<ViajeRemito, Integer> {
    
    //Obtiene el siguiente id
    public ViajeRemito findTopByOrderByIdDesc();
    
    //Obtiene la lista por numero
    public List<ViajeRemito> findByNumeroContaining(int numero);
    
    //Obtiene una lista de pendientes por sucursal emision
    public List<ViajeRemito> findBySucursalEmisionAndEstaPendienteTrue(Optional<Sucursal> sucursal);
    
    //Obtiene una lista de pendientes por filtro (sucursalEmision, sucursalDestino y numero camion)
    public List<ViajeRemito> findBySucursalEmisionAndSucursalDestinoAndNumeroCamionAndEstaPendienteTrue(
            Optional<Sucursal> sucursal, Optional<Sucursal> sucursalDestino, short numeroCamion);
    
    //Obtiene una lista de pendientes por filtro (sucursalEmision, sucursalDestino, numero camion y viajePropioTramo)
    public List<ViajeRemito> findBySucursalEmisionAndSucursalDestinoAndNumeroCamionAndViajePropioTramoAndEstaPendienteFalse(
            Optional<Sucursal> sucursal, Optional<Sucursal> sucursalDestino, short numeroCamion, Optional<ViajePropioTramo> viajePropioTramo);
    
}

//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Sucursal;
import ar.com.draimo.jitws.model.ViajePropioTramo;
import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.model.ViajeTerceroTramo;
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
    
    //Obtiene una lista por alias
    public List<ViajeRemito> findByAliasContaining(String alias);
    
    //Obtiene la lista por numero
    public List<ViajeRemito> findByNumero(int numero);
    
    //Obtiene una lista de pendientes por sucursal ingreso
    public List<ViajeRemito> findBySucursalIngresoAndEstaPendienteFalse(Optional<Sucursal> sucursal);
    
    //Obtiene una lista de pendientes por filtro (sucursalIngreso, sucursalDestino y numero camion)
    public List<ViajeRemito> findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndEstaPendienteTrue(
            Optional<Sucursal> sucursal, Optional<Sucursal> sucursalDestino, short numeroCamion);
    
    //Obtiene una lista de pendientes por filtro (sucursalIngreso, sucursalDestino,
    //numero camion y viajePropioTramo)
    public List<ViajeRemito> 
        findBySucursalIngresoAndSucursalDestinoAndNumeroCamionAndViajePropioTramoAndEstaPendienteFalse(
            Optional<Sucursal> sucursal, Optional<Sucursal> sucursalDestino, short numeroCamion,
                Optional<ViajePropioTramo> viajePropioTramo);
    
    //Obtiene un listado de remitos por viaje propio
    public List<ViajeRemito> findByViajePropioTramoAndEstaFacturadoFalse(
            Optional<ViajePropioTramo> viajePropioTramo);
    
    //Obtiene un listado de remitos por viaje propio
    public List<ViajeRemito> findByViajeTerceroTramoAndEstaFacturadoFalse(
            Optional<ViajeTerceroTramo> viajeTerceroTramo);
    
    //Obtiene un registro por puntoVenta, letra y nroComprobante
    public ViajeRemito findByPuntoVentaAndLetraAndNumero(int puntoVenta,String letra,int numero);
    
}

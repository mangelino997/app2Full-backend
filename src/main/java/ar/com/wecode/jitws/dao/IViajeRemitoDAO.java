//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.ViajeRemito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query(value = "SELECT * FROM viajeremito b WHERE b.numeroComprobante like %:numero%", nativeQuery = true)
    public List<ViajeRemito> listarPorNumero(@Param("numero") String numero);
    
    //Obtiene una lista de pendientes por sucursal
    //public List<ViajeRemito> listarPendientesPorSucursal(int idSucursal);
    
    //Obtiene una lista de pendientes por filtro (sucursal, sucursalDestino y numero camion)
    //public List<ViajeRemito> listarPendientesPorFiltro(int idSucursal, int idSucursalDestino, String numeroCamion);
    
    //Obtiene una lista de pendientes por filtro (sucursal, sucursalDestino, numero camion y viajePropioTramo)
    //public List<ViajeRemito> listarAsignadosPorFiltro(int idSucursal, int idSucursalDestino, String numeroCamion, int idViajePropioTramo);
    
}

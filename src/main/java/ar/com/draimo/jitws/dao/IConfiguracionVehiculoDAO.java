//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ConfiguracionVehiculo;
import ar.com.draimo.jitws.model.MarcaVehiculo;
import ar.com.draimo.jitws.model.TipoVehiculo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO ConfiguracionVehiculo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IConfiguracionVehiculoDAO extends JpaRepository<ConfiguracionVehiculo, Integer> {
    
    //Obtiene el ultimo registro
    public ConfiguracionVehiculo findTopByOrderByIdDesc();
    
    //Obtiene una lista por TipoVehiculo y MarcaVehiculo
    public List<ConfiguracionVehiculo> findByTipoVehiculoAndMarcaVehiculo(Optional<TipoVehiculo> TipoVehiculo, Optional<MarcaVehiculo> marcaVehiculo);
    
    //Obtiene por filtro
    @Query(value = "SELECT * FROM configuracionvehiculo WHERE ((:idTipoVehiculo = 0 or idTipoVehiculo=:idTipoVehiculo) "
            + "and (:idMarcaVehiculo=0 or idMarcaVehiculo=:idMarcaVehiculo))", nativeQuery = true)
    public List<ConfiguracionVehiculo> listarPorFiltros(@Param("idTipoVehiculo") int idTipoVehiculo, @Param("idMarcaVehiculo") int idMarcaVehiculo);
    
}

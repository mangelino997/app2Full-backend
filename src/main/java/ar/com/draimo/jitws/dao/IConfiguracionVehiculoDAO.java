//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ConfiguracionVehiculo;
import ar.com.draimo.jitws.model.MarcaVehiculo;
import ar.com.draimo.jitws.model.TipoVehiculo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ConfiguracionVehiculo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IConfiguracionVehiculoDAO extends JpaRepository<ConfiguracionVehiculo, Integer> {
    
    //Obtiene el siguiente id
    public ConfiguracionVehiculo findTopByOrderByIdDesc();
    
    //Obtiene una lista por id tipo vehiculo y id marca vehiculo
    public List<ConfiguracionVehiculo> findByTipoVehiculoAndMarcaVehiculo(Optional<TipoVehiculo> TipoVehiculo, Optional<MarcaVehiculo> marcaVehiculo);
    
}
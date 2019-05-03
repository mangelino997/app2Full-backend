//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoVehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Tipo de vehiculo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoVehiculoDAO extends JpaRepository<TipoVehiculo, Integer> {
    
    //Obtiene el siguiente id
    public TipoVehiculo findTopByOrderByIdDesc();
    
    //Obtiene la lista de registros ordenada por nombre desc
    public List<TipoVehiculo> findAllByOrderByNombreAsc();
    
    //Obtiene una lista por nombre
    public List<TipoVehiculo> findByNombreContaining(String nombre);
    
}

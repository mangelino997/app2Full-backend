//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.MarcaVehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Marca de vehiculo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMarcaVehiculoDAO extends JpaRepository<MarcaVehiculo, Integer> {
    
    //Obtiene el siguiente id
    public MarcaVehiculo findTopByOrderByIdDesc();
    
    //Obtiene la lista de registro ordenada por nombre desc
    public List<MarcaVehiculo> findAllByOrderByNombreAsc();
    
    //Obtiene una lista por nombre
    public List<MarcaVehiculo> findByNombreContaining(String nombre);
    
}

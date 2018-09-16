//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.MarcaVehiculo;
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
    
    //Obtiene una lista por nombre
    public List<MarcaVehiculo> findByNombreContaining(String nombre);
    
}

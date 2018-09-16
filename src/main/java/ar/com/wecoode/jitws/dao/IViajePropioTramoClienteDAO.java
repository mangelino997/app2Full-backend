//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.ViajePropioTramo;
import ar.com.wecoode.jitws.model.ViajePropioTramoCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Propio Tramo Cliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioTramoClienteDAO extends JpaRepository<ViajePropioTramoCliente, Integer> {
    
    //Obtiene el siguiente id
    public ViajePropioTramoCliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por viaje propio tramo
    public List<ViajePropioTramoCliente> findByViajePropioTramo(Optional<ViajePropioTramo> viajePropioTramo);
    
}

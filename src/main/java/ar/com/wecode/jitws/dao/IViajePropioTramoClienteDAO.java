//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.ViajePropioTramo;
import ar.com.wecode.jitws.model.ViajePropioTramoCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Viaje Propio Tramo Cliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePropioTramoClienteDAO extends JpaRepository<ViajePropioTramoCliente, Integer> {
    
    public final String NOMBRE_TABLA = "viajepropiotramocliente";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por viaje propio tramo
    public List<ViajePropioTramoCliente> findByViajePropioTramo(Optional<ViajePropioTramo> viajePropioTramo);
    
}

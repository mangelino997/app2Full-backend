//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.ViajeTercero;
import ar.com.wecoode.jitws.model.ViajeTerceroCombustible;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Viaje Tercero Combustible
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTerceroCombustibleDAO extends JpaRepository<ViajeTerceroCombustible, Integer> {
    
    public final String NOMBRE_TABLA = "viajetercerocombustible";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene un listado por viaje tercero
    public List<ViajeTerceroCombustible> findByViajeTercero(Optional<ViajeTercero> viajeTercero);
    
}

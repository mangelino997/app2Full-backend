//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.Localidad;
import ar.com.wecode.jitws.model.Provincia;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Interfaz DAO Localidad
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ILocalidadDAO extends JpaRepository<Localidad, Integer> {
    
    public final String NOMBRE_TABLA = "localidad";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por nombre //findByNombreContaining
    public List<Localidad> findByNombreContaining(String nombre);
    
    //Obtiene una lista por id provincia
    public List<Localidad> findByProvincia(Optional<Provincia> elemento);
    
}

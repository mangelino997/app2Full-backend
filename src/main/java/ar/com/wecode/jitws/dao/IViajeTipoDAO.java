//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.ViajeTipo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO ViajeTipo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTipoDAO extends JpaRepository<ViajeTipo, Integer> {
    
    public final String NOMBRE_TABLA = "viajetipo";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por nombre
    @Query(value = "SELECT * FROM viajetipo b WHERE b.nombre like %:nom%", nativeQuery = true)
    public List<ViajeTipo> listarPorNombre(@Param("nom") String nom);
    
}

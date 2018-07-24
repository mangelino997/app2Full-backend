//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.Pais;
import ar.com.wecode.jitws.model.Provincia;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Provincia
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IProvinciaDAO extends JpaRepository<Provincia, Integer> {
    
    public final String NOMBRE_TABLA = "provincia";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por nombre
    @Query(value = "SELECT * FROM provincia b WHERE b.nombre like %:nom%", nativeQuery = true)
    public List<Provincia> listarPorNombre(@Param("nom") String nom);
    
    //Obtiene una provincia por pais
    public List<Provincia> findByPais(Optional<Pais> elemento);
    
}

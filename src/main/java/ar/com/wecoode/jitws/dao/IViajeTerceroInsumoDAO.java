//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.ViajeTercero;
import ar.com.wecoode.jitws.model.ViajeTerceroInsumo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Viaje Tercero Insumo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTerceroInsumoDAO extends JpaRepository<ViajeTerceroInsumo, Integer> {
    
    public final String NOMBRE_TABLA = "viajeterceroinsumo";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene un listado por viaje tercero
    public List<ViajeTerceroInsumo> findByViajeTercero(Optional<ViajeTercero> viajeTercero);
    
}

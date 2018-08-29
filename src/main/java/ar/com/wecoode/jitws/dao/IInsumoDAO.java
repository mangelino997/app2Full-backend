//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.Insumo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Insumo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IInsumoDAO extends JpaRepository<Insumo, Integer> {
    
    public final String NOMBRE_TABLA = "insumo";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por nombre
    public List<Insumo> findByNombreContaining(String nombre);
    
    //Obtiene una lista por opcion (EsCombustible=0 o EsCombustible=1) 
    public List<Insumo> findByEsCombustible(int esCombustible);
    
}

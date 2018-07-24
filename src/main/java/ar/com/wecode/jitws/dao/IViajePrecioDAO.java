//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.ViajePrecio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO ViajePrecio
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajePrecioDAO extends JpaRepository<ViajePrecio, Integer> {
    
    public final String NOMBRE_TABLA = "viajeprecio";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene el costo por idViajeTipo y idViajeTarifa
    //public ViajePrecio obtenerCosto(int idViajeTipo, int idViajeTarifa);
    
}

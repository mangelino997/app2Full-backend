//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeUnidadNegocio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ViajeUnidadNegocio
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeUnidadNegocioDAO extends JpaRepository<ViajeUnidadNegocio, Integer> {
    
    //Obtiene el ultimo registro
    public ViajeUnidadNegocio findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ViajeUnidadNegocio> findByNombreContaining(String nombre);
    
}
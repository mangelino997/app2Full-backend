//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeCierreDocumentacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ViajeCierreDocumentacion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeCierreDocumentacionDAO extends JpaRepository<ViajeCierreDocumentacion, Integer> {
    
    //Obtiene el ultimo registro
    public ViajeCierreDocumentacion findTopByOrderByIdDesc();
    
}
//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.SueldoCFG;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SueldoCFG
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISueldoCFGDAO extends JpaRepository<SueldoCFG, Integer> {
    
    //Obtiene el ultimo registro
    public SueldoCFG findTopByOrderByIdDesc();
    
}
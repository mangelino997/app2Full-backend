//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.SueldoCFG;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SueldoCFG
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISueldoCFGDAO extends JpaRepository<SueldoCFG, Integer> {
    
    //Obtiene el siguiente id
    public SueldoCFG findTopByOrderByIdDesc();
    
}

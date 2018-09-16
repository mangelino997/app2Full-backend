//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.AfipModContratacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Afip Modalidad contratacion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipModContratacionDAO extends JpaRepository<AfipModContratacion, Integer> {
    
    //Obtiene el siguiente id
    public AfipModContratacion findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<AfipModContratacion> findByNombreContaining(String nombre);
    
}

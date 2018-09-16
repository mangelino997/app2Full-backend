//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.ViajeTipoCarga;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ViajeTipoCarga
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTipoCargaDAO extends JpaRepository<ViajeTipoCarga, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTipoCarga findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ViajeTipoCarga> findByNombreContaining(String nombre);
    
}

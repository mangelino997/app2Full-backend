//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Moneda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Moneda
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMonedaDAO extends JpaRepository<Moneda, Integer> {
    
    //Obtiene el siguiente id
    public Moneda findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Moneda> findByNombreContaining(String nombre);
    
}

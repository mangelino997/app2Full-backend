//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Sucursal
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISucursalDAO extends JpaRepository<Sucursal, Integer> {
    
    //Obtiene el siguiente id
    public Sucursal findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Sucursal> findByNombreContaining(String nombre);
    
}

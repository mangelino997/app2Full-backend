//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Vendedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Vendedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVendedorDAO extends JpaRepository<Vendedor, Integer> {
    
    //Obtiene el siguiente id
    public Vendedor findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Vendedor> findByNombreContaining(String nombre);
    
}

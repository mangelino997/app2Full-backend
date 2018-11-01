//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CondicionCompra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO CondicionCompra
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICondicionCompraDAO extends JpaRepository<CondicionCompra, Integer> {
    
    //Obtiene el siguiente id
    public CondicionCompra findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<CondicionCompra> findByNombreContaining(String nombre);
    
}

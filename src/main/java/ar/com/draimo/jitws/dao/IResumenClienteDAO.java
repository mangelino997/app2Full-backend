//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ResumenCliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Resumen cliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IResumenClienteDAO extends JpaRepository<ResumenCliente, Integer> {
    
    //Obtiene el ultimo registro
    public ResumenCliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ResumenCliente> findByNombreContaining(String nombre);
    
}
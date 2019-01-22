//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.PlandeCuenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO PlandeCuenta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPlandeCuentaDAO extends JpaRepository<PlandeCuenta, Integer> {
    
    //Obtiene el siguiente id
    public PlandeCuenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<PlandeCuenta> findByNombreContaining(String nombre);
    
}

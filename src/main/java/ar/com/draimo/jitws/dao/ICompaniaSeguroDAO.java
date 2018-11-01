//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompaniaSeguro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Compañia de seguro
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICompaniaSeguroDAO extends JpaRepository<CompaniaSeguro, Integer> {
    
    //Obtiene el siguiente id
    public CompaniaSeguro findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<CompaniaSeguro> findByNombreContaining(String nombre);
    
}

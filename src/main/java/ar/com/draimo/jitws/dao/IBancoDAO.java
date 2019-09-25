//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Banco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Banco
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IBancoDAO extends JpaRepository<Banco, Integer> {
    
    //Obtiene el ultimo registro
    public Banco findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Banco> findByNombreContaining(String nombre);
    
}
//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Banco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Banco
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IBancoDAO extends JpaRepository<Banco, Integer> {
    
    //Obtiene el siguiente id
    public Banco findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Banco> findByNombreContaining(String nombre);
    
}

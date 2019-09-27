//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoChequera;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz TipoChequera DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ITipoChequeraDAO extends JpaRepository<TipoChequera, Integer> {
    
    //Obtiene el ultimo registro
    public TipoChequera findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<TipoChequera> findByNombreContaining(String nombre);
    
}
//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Insumo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Insumo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IInsumoDAO extends JpaRepository<Insumo, Integer> {
    
    //Obtiene el siguiente id
    public Insumo findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Insumo> findByNombreContaining(String nombre);
    
    //Obtiene una lista por opcion (EsCombustible=0 o EsCombustible=1) 
    public List<Insumo> findByEsCombustible(int esCombustible);
    
}

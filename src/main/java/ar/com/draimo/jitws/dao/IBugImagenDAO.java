package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.BugImagen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz BugImagen DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IBugImagenDAO extends JpaRepository<BugImagen, Integer> {
    
    //Obtiene el siguiente id
    public BugImagen findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<BugImagen> findByNombreContaining(String nombre);
    
}
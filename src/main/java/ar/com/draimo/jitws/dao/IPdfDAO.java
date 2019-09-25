package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Pdf;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Pdf DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IPdfDAO extends JpaRepository<Pdf, Integer> {
    
    //Obtiene el ultimo registro
    public Pdf findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<Pdf> findByNombreContaining(String nombre);
    
}
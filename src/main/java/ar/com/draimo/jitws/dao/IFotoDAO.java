package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Foto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Foto DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IFotoDAO extends JpaRepository<Foto, Integer> {
    
    //Obtiene el siguiente id
    public Foto findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<Foto> findByNombreContaining(String nombre);
    
}

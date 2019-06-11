package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.model.ViajeRemitoTramo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz ViajeRemitoTramo DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IViajeRemitoTramoDAO extends JpaRepository<ViajeRemitoTramo, Integer> {
    
    //Obtiene el siguiente id
    public ViajeRemitoTramo findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<ViajeRemitoTramo> findByViajeRemito(ViajeRemito viajeRemito);
    
}

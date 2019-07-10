package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeRemito;
import ar.com.draimo.jitws.model.ViajeTramoRemito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz ViajeTramoRemito DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IViajeTramoRemitoDAO extends JpaRepository<ViajeTramoRemito, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTramoRemito findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<ViajeTramoRemito> findByViajeRemito(ViajeRemito viajeRemito);
    
}

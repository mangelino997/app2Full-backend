//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO TipoComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ITipoComprobanteDAO extends JpaRepository<TipoComprobante, Integer> {
    
    //Obtiene el siguiente id
    public TipoComprobante findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<TipoComprobante> findByNombreContaining(String nombre);
    
    //Obtiene una lista por estaActivoIngresoCarga
    public List<TipoComprobante> findByEstaActivoRepartoTrue();
    
    //Obtiene una lista por estaActivoIngresoCarga
    public List<TipoComprobante> findByEstaActivoIngresoCargaTrue();
    
}

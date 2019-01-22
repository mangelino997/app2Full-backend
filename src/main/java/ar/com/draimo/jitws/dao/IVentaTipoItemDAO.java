//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.TipoComprobante;
import ar.com.draimo.jitws.model.VentaTipoItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaTipoItem
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaTipoItemDAO extends JpaRepository<VentaTipoItem, Integer> {
    
    //Obtiene el siguiente id
    public VentaTipoItem findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<VentaTipoItem> findByNombreContaining(String nombre);
    
    //Obtiene un listado de ventaTipoItem por tipoComprobante igual a 1
    public List<VentaTipoItem> findByTipoComprobante(Optional<TipoComprobante> tipoComprobante);
    
}

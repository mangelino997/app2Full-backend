//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaTipoItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO VentaTipoItem
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaTipoItemDAO extends JpaRepository<VentaTipoItem, Integer> {
    
    //Obtiene el ultimo registro
    public VentaTipoItem findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<VentaTipoItem> findByNombreContaining(String nombre);
    
    //Obtiene un listado de ventaTipoItem por tipoComprobante
    @Query(value = "SELECT * FROM ventatipoitem where idTipoComprobante = :idTipoComprobante", nativeQuery = true)
    public List<VentaTipoItem> listarTipoComprobante(@Param("idTipoComprobante") int idTipoComprobante);
    
}
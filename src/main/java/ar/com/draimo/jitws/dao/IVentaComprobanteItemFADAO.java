//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaComprobanteItemFA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO VentaComprobanteItemFA
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaComprobanteItemFADAO extends JpaRepository<VentaComprobanteItemFA, Integer> {
    
    //Obtiene el siguiente id
    public VentaComprobanteItemFA findTopByOrderByIdDesc();
    
    //Obtiene todos los registros
    @Query(value = "SELECT * FROM ventacomprobanteitemFA where id=:id", nativeQuery = true)
    public VentaComprobanteItemFA obtenerPorId(@Param("id") int id);
    
    //Obtiene todos los registros
    @Query(value = "SELECT * FROM ventacomprobanteitemFA", nativeQuery = true)
    public List<VentaComprobanteItemFA> listar();
}
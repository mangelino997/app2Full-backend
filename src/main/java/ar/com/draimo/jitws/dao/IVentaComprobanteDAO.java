//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaComprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO VentaComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaComprobanteDAO extends JpaRepository<VentaComprobante, Integer> {
    
    //Obtiene el siguiente id
    public VentaComprobante findTopByOrderByIdDesc();
    
    //Obtiene un registro por puntoVenta, letra y nroComprobante
    @Query(value = "SELECT * FROM ventacomprobante WHERE puntaVenta =:puntoVenta "
            + "AND letra=:letra AND numero=:numero", nativeQuery = true)
    public VentaComprobante obtenerPorPuntoVentaLetraYNumero(@Param("puntoVenta")
            int puntoVenta, @Param("letra") String letra, @Param("numero") int numero);
    
}

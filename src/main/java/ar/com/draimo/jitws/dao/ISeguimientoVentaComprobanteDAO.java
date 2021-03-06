//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoVentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SeguimientoVentaComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISeguimientoVentaComprobanteDAO extends JpaRepository<SeguimientoVentaComprobante, Integer> {
    
    //Obtiene el ultimo registro
    public SeguimientoVentaComprobante findTopByOrderByIdDesc();
    
    //Obtiene una lista ordenada por fecha
    public List<SeguimientoVentaComprobante> findByOrderByFechaDesc();
    
    //Obtiene una lista por seguimientoEstado
    public List<SeguimientoVentaComprobante> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
    //Obtiene una lista ordenada por VentaComprobante
    public List<SeguimientoVentaComprobante> findByVentaComprobante(VentaComprobante ventaComprobante);
    
    //elimina una lista por VentaComprobante
    public void deleteByVentaComprobante(VentaComprobante ventaComprobante);
    
}
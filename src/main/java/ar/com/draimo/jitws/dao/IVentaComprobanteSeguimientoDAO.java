//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoVentaComprobante;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaComprobanteSeguimiento
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaComprobanteSeguimientoDAO extends JpaRepository<SeguimientoVentaComprobante, Integer> {
    
    //Obtiene el siguiente id
    public SeguimientoVentaComprobante findTopByOrderByIdDesc();
    
    //Obtiene una lista ordenada por fecha
    public List<SeguimientoVentaComprobante> findByOrderByFechaDesc();
    
    //Obtiene una lista por seguimientoEstado
    public List<SeguimientoVentaComprobante> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
    //Obtiene una lista ordenada por VentaComprobante
    public List<SeguimientoVentaComprobante> findByVentaComprobante(VentaComprobante ventaComprobante);
    
}

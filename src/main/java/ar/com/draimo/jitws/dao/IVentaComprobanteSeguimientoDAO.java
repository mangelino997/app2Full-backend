//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.VentaComprobanteSeguimiento;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaComprobanteSeguimiento
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaComprobanteSeguimientoDAO extends JpaRepository<VentaComprobanteSeguimiento, Integer> {
    
    //Obtiene el siguiente id
    public VentaComprobanteSeguimiento findTopByOrderByIdDesc();
    
    //Obtiene una lista ordenada por fecha
    public List<VentaComprobanteSeguimiento> findByOrderByFechaDesc();
    
    //Obtiene una lista por seguimientoEstado
    public List<VentaComprobanteSeguimiento> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
    //Obtiene una lista ordenada por VentaComprobante
    public List<VentaComprobanteSeguimiento> findByVentaComprobante(VentaComprobante ventaComprobante);
    
}

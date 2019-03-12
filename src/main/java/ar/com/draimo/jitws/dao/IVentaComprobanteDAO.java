//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaComprobante;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaComprobante
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaComprobanteDAO extends JpaRepository<VentaComprobante, Integer> {
    
    //Obtiene el siguiente id
    public VentaComprobante findTopByOrderByIdDesc();
    
    //Obtiene un registro por puntoVenta, letra y nroComprobante
    public VentaComprobante findByPuntoVentaAndLetraAndNumero(int puntoVenta,String letra, int numero);
    
}

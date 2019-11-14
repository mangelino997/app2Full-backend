//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.PagoAnticipo;
import ar.com.draimo.jitws.model.Pago;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO PagoAnticipo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPagoAnticipoDAO extends JpaRepository<PagoAnticipo, Integer> {
    
    //Obtiene el siguiente id
    public PagoAnticipo findTopByOrderByIdDesc();
    
    //Obtiene una lista por Pago
    public List<PagoAnticipo> findByPago(Pago pago);
    
}

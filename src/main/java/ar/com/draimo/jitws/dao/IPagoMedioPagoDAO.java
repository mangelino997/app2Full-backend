//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Pago;
import ar.com.draimo.jitws.model.PagoMedioPago;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO PagoMedioPago
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPagoMedioPagoDAO extends JpaRepository<PagoMedioPago, Integer> {
    
    //Obtiene el siguiente id
    public PagoMedioPago findTopByOrderByIdDesc();
    
    //Obtiene una lista por Pago
    public List<PagoMedioPago> findByPago(Pago pago);
    
    //Obtiene una lista 
    @Query(value = "SELECT * FROM pagomediopago WHERE idPago=:idPago", nativeQuery = true)
    public List<PagoMedioPago> obtenerImporteTotalPorCobranza(@Param("idPago") int idPago);
    
}

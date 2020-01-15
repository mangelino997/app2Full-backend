//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.PagoAnticipo;
import ar.com.draimo.jitws.model.Pago;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    
    //Obtiene los anticipos por proveedor y saldo mayor a cero
    @Query(value = "SELECT pa.* FROM pagoanticipo pa INNER JOIN pago p ON pa.idPago=p.id "
            + "WHERE p.idProveedor=:idProveedor AND pa.saldo>0;", nativeQuery = true)
    public List<PagoAnticipo> listarPorProveedorYSaldoMayorCero(@Param("idProveedor") int idProveedor);
    
}

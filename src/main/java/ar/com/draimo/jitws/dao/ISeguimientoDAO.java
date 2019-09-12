//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Seguimiento;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import ar.com.draimo.jitws.model.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Seguimiento
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISeguimientoDAO extends JpaRepository<Seguimiento, Integer> {
    
    //Obtiene el siguiente id
    public Seguimiento findTopByOrderByIdDesc();
    
    //Obtiene una lista por sucursal
    public List<Seguimiento> findBySucursal(Sucursal sucursal);
    
    //Obtiene un registro por ventaCte
    @Query(value = "SELECT s.* FROM repartocomprobante c ,seguimiento s where "
            + "(s.idVentaComprobante=c.idVentaComprobante or "
            + "s.idOrdenRecoleccion=c.idOrdenRecoleccion or s.idViajeRemito=c.idViajeRemito)"
            + " and c.idReparto=:idReparto", nativeQuery = true)
    public List<Seguimiento> listarPorReparto(@Param("idReparto") int idReparto);
    
    //Obtiene una lista por SeguimientoEstado
    public List<Seguimiento> findBySeguimientoEstado(SeguimientoEstado seguimientoEstado);
    
}

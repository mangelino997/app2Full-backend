//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTramoCliente;
import ar.com.draimo.jitws.model.ViajeTramoClienteRemito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO viajeTramoClienteRemito
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTramoClienteRemitoDAO extends JpaRepository<ViajeTramoClienteRemito, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTramoClienteRemito findTopByOrderByIdDesc();
    
    //Obtiene una lista por viajeTramoCliente
    public List<ViajeTramoClienteRemito> findByViajeTramoCliente(ViajeTramoCliente viajeTramoCliente);
    
    //Obtiene una lista por viajeTramoCliente
    @Query(value = "SELECT * FROM viajetramoclienteremito r INNER JOIN viajetramocliente c ON "
            + "r.idViajeTramoCliente=c.id INNER JOIN viajetramo t ON c.idViajeTramo=t.id  "
            + "WHERE :idViajeTramoCliente= 0 OR c.id=:idViajeTramoCliente AND t.idViaje=:idViaje "
            + "AND r.estaFacturado=:estaFacturado", nativeQuery = true)
    public List<ViajeTramoClienteRemito> listarPorViajeYEstaFacturado(@Param("idViajeTramoCliente")
            int idViajeTramoCliente, @Param("idViaje") int idViaje, @Param("estaFacturado") boolean estaFacturado);
    
    //Obtiene un registro por id
    @Query(value = "SELECT * FROM viajetramoclienteremito WHERE id=:id", nativeQuery = true)
    public ViajeTramoClienteRemito obtenerPorId(@Param("id") int id);
    
    //Elimina un registro por viaje tramo cliente
    public void deleteByViajeTramoCliente(ViajeTramoCliente viajeTramoCliente);
    
}

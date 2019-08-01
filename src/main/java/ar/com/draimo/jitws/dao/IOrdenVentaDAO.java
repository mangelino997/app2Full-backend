//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.OrdenVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO OrdenVenta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenVentaDAO extends JpaRepository<OrdenVenta, Integer> {
    
    //Obtiene el siguiente id
    public OrdenVenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<OrdenVenta> findByNombreContaining(String nombre);
    
    //Obtiene un listado de ordenes de venta de empresaordenventa por idEmpresa
    @Query(value = "select o.* from empresaordenventa e inner join ordenventa o "
            + "on o.id = e.idOrdenVenta where e.idEmpresa=:idEmpresa", nativeQuery = true)
    public List<OrdenVenta> listarPorEmpresa(@Param("idEmpresa") int idEmpresa);
    
    //Obtiene un listado de ordenes de venta de clienteordenventa por idCliente
    @Query(value = "select o.* from clienteordenventa e inner join ordenventa o "
            + "on o.id = e.idOrdenVenta where e.idCliente=:idCliente", nativeQuery = true)
    public List<OrdenVenta> listarPorCliente(@Param("idCliente") int idCliente);
    
}

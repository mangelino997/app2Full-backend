//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;
import ar.com.draimo.jitws.model.OrdenRecoleccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO OrdenRecoleccion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenRecoleccionDAO extends JpaRepository<OrdenRecoleccion, Integer> {
    
    //Obtiene el siguiente id
    public OrdenRecoleccion findTopByOrderByIdDesc();
    
    //Obtiene por alias
    public List<OrdenRecoleccion> findByAliasContaining(String alias);
    
    //Obtiene un listado fecha de emision y cliente. Si son nulos obtiene todos los registros
    @Query(value = "select * from ordenrecoleccion where (:fechaEmision IS NULL "
            + "or date(fechaEmision)=:fechaEmision) AND (:idCliente=0 or idCliente=:idCliente)", nativeQuery = true)
    public List<OrdenRecoleccion> listarPorFiltros(@Param("fechaEmision") String
            fechaEmision, @Param("idCliente") int idCliente);
    
    //Obtiene un listado por ordenesDeRecoleccion disponibles
    @Query(value = "SELECT * FROM jitdb.ordenrecoleccion WHERE id!=(SELECT "
            + "s.idOrdenRecoleccion  FROM jitdb.seguimiento s, jitdb.seguimientoestado "
            + "e, jitdb.ordenrecoleccion v WHERE s.idOrdenRecoleccion =v.id and "
            + "e.id=s.idSeguimientoEstado and e.esEntregado=false) ;", nativeQuery = true)
    public List<OrdenRecoleccion> listarRecoleccionesDisponibles();
    
}

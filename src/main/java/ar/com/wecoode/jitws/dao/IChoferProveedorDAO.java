//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.ChoferProveedor;
import ar.com.wecoode.jitws.model.Proveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO ChoferProveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IChoferProveedorDAO extends JpaRepository<ChoferProveedor, Integer> {
    
    public final String NOMBRE_TABLA = "choferproveedor";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por id proveedor
    public List<ChoferProveedor> findByProveedor(Optional<Proveedor> proveedor);
    
    //Obtiene una lista por nombre
    public List<ChoferProveedor> findByNombreContaining(String nombre);
    
}

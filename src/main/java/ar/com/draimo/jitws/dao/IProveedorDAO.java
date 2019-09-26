//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Proveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IProveedorDAO extends JpaRepository<Proveedor, Integer> {
    
    //Obtiene el ultimo registro
    public Proveedor findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<Proveedor> findByAliasContaining(String alias);
    
    //Obtiene un listado por tipoProveedor y/o condicionCompra y/o activo y/o localidad
    @Query(value = "SELECT * FROM proveedor WHERE (:idTipoProveedor = 0 OR"
            + " idTipoProveedor=:idTipoProveedor) AND (:idCondCompra=0 OR idCondCompra=:idCondCompra)"
            + " AND (:estadoCuenta = 2 OR estaActiva=:estadoCuenta)AND (:idLocalidad=0 OR "
            + "idLocalidad=:idLocalidad)" , nativeQuery = true)
    public List<Proveedor> listarPorFiltros(@Param("idTipoProveedor") int idTipoProveedor,
            @Param("idCondCompra") int idCondCompra,@Param("estadoCuenta") 
                    int estadoCuenta,@Param("idLocalidad") int idLocalidad);
}
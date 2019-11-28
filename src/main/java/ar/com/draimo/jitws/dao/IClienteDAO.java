//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Cliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IClienteDAO extends JpaRepository<Cliente, Integer> {
    
    //Obtiene el ultimo registro
    public Cliente findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    public List<Cliente> findByAliasContaining(String alias);
    
    //Obtiene una lista de activos por alias
    public List<Cliente> findByAliasContainingAndFechaBajaIsNull(String alias);
    
    //Obtiene una lista de clientes exceptuando el id que se pasa para parametro
    public List<Cliente> findByIdNot(int id);
    
    //Obtiene una lista por alias exceptuando el id que se pasa para parametro
    public List<Cliente> findByAliasContainingAndIdNot(String alias, int id);
    
    //Obtiene una lista de clientes por filtro (localidad, cobrador,
    //condicion venta y tipo de seguro
    @Query(value = "SELECT * FROM cliente where (:idLocalidad "
            + "= 0 or idLocalidad=:idLocalidad) and (:idCobrador =0 or "
            + "idCobrador=:idCobrador) and (:idCondicionVenta=0 or "
            + "idCondicionVenta=:idCondicionVenta) and (:esSeguroPropio=2 or "
            + "esSeguroPropio=:esSeguroPropio)", nativeQuery = true)
    public List<Cliente> listarPorFiltros(@Param("idLocalidad") String idLocalidad, @Param("idCobrador") String idCobrador,
            @Param("idCondicionVenta") String idCondicionVenta, @Param("esSeguroPropio") 
            int esSeguroPropio);
}
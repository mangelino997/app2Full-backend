//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.OrdenVenta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

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
    
    //Obtiene una lista por cliente
    public List<OrdenVenta> findByCliente(Optional<Cliente> cliente);
    
    //Obtiene una lista por empresa
    public List<OrdenVenta> findByEmpresa(Optional<Empresa> empresa);
    
}

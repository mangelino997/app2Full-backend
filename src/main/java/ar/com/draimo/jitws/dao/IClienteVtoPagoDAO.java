//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ClienteVtoPago;
import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ClienteVtoPago
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IClienteVtoPagoDAO extends JpaRepository<ClienteVtoPago, Integer> {
    
    //Obtiene el ultimo registro
    public ClienteVtoPago findTopByOrderByIdDesc();
    
    //Obtiene una lista por cliente y empresa
    public List<ClienteVtoPago> findByClienteAndEmpresa(Cliente c, Empresa e);
    
}
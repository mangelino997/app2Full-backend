//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Pago;
import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Pago
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPagoDAO extends JpaRepository<Pago, Integer> {
    
    //Obtiene el siguiente id
    public Pago findTopByOrderByIdDesc();
    
    //Obtiene una lista por Empresa
    public List<Pago> findByEmpresa(Empresa empresa);
    
}

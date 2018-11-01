//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ViajeTerceroTramoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Viaje Tercero Tramo Cliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeTerceroTramoClienteDAO extends JpaRepository<ViajeTerceroTramoCliente, Integer> {
    
    //Obtiene el siguiente id
    public ViajeTerceroTramoCliente findTopByOrderByIdDesc();
    
}

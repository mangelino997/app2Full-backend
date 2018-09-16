//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.OrdenVentaEscala;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OrdenVentaEscala
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenVentaEscalaDAO extends JpaRepository<OrdenVentaEscala, Integer> {
    
    //Obtiene el siguiente id
    public OrdenVentaEscala findTopByOrderByIdDesc();
    
}

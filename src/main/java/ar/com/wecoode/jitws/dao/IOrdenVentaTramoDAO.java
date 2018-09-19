//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.OrdenVentaTramo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO OrdenVentaTramo
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IOrdenVentaTramoDAO extends JpaRepository<OrdenVentaTramo, Integer> {
    
    //Obtiene el siguiente id
    public OrdenVentaTramo findTopByOrderByIdDesc();
    
}
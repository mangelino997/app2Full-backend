//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompraComprobanteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz CompraComprobanteItem DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ICompraComprobanteItemDAO extends JpaRepository<CompraComprobanteItem, Integer> {
    
    //Obtiene el ultimo registro
    public CompraComprobanteItem findTopByOrderByIdDesc();
    
}
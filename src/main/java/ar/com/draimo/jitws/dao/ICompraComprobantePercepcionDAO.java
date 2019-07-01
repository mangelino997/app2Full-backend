package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompraComprobantePercepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz CompraComprobantePercepcion DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ICompraComprobantePercepcionDAO extends JpaRepository<CompraComprobantePercepcion, Integer> {
    
    //Obtiene el siguiente id
    public CompraComprobantePercepcion findTopByOrderByIdDesc();
    
}

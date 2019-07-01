package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompraComprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz CompraComprobante DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ICompraComprobanteDAO extends JpaRepository<CompraComprobante, Integer> {
    
    //Obtiene el siguiente id
    public CompraComprobante findTopByOrderByIdDesc();
    
}

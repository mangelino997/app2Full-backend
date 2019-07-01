package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CompraComprobanteVencimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz CompraComprobanteVencimiento DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ICompraComprobanteVencimientoDAO extends JpaRepository<CompraComprobanteVencimiento, Integer> {
    
    //Obtiene el siguiente id
    public CompraComprobanteVencimiento findTopByOrderByIdDesc();
    
}

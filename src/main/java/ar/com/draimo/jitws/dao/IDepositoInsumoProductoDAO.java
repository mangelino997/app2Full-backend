package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.DepositoInsumoProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz DepositoInsumoProducto DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface IDepositoInsumoProductoDAO extends JpaRepository<DepositoInsumoProducto, Integer> {
    
    //Obtiene el siguiente id
    public DepositoInsumoProducto findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<DepositoInsumoProducto> findByNombreContaining(String nombre);
    
}

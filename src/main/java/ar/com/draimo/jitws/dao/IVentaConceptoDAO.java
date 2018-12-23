//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaConcepto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Venta Concepto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaConceptoDAO extends JpaRepository<VentaConcepto, Integer> {
    
    //Obtiene el siguiente id
    public VentaConcepto findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<VentaConcepto> findByNombreContaining(String nombre);
    
}

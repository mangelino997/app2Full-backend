//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaItemConcepto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Venta Item Concepto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaItemConceptoDAO extends JpaRepository<VentaItemConcepto, Integer> {
    
    //Obtiene el siguiente id
    public VentaItemConcepto findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<VentaItemConcepto> findByNombreContaining(String nombre);
    
}

//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.VentaContrareembolso;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO VentaContrareembolso
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IVentaContrareembolsoDAO extends JpaRepository<VentaContrareembolso, Integer> {
    
    //Obtiene el siguiente id
    public VentaContrareembolso findTopByOrderByIdDesc();
    
}

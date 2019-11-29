//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.MedioPago;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO MedioPago
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMedioPagoDAO extends JpaRepository<MedioPago, Integer> {
    
    //Obtiene el siguiente id
    public MedioPago findTopByOrderByIdDesc();
    
    //Obtiene una lista por Nombre
    public List<MedioPago> findByNombreContaining(String nombre);
    
    //Obtiene una lista de activos para ingreso
    public List<MedioPago> findByEstaActivoIngresoTrue();
    
    //Obtiene una lista de activos para egreso
    public List<MedioPago> findByEstaActivoEgresoTrue();
    
}

//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Moneda;
import ar.com.draimo.jitws.model.MonedaCotizacion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO moneda cotizacion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMonedaCotizacionDAO extends JpaRepository<MonedaCotizacion, Integer> {
    
    //Obtiene el siguiente id
    public MonedaCotizacion findTopByOrderByIdDesc();
    
    
    //Obtiene un listado de moneda cotizacion por moneda
    public List<MonedaCotizacion> findByMoneda(Optional<Moneda> elemento);
    
}

//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Moneda;
import ar.com.draimo.jitws.model.MonedaCotizacion;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO moneda cotizacion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IMonedaCotizacionDAO extends JpaRepository<MonedaCotizacion, Integer> {
    
    //Obtiene el ultimo registro
    public MonedaCotizacion findTopByOrderByIdDesc();
    
    //Obtiene una moneda cotizacion por moneda y fecha
    public MonedaCotizacion findByMonedaAndFecha(Moneda moneda, Date fecha);
    
    //Obtiene un listado de moneda cotizacion por moneda  ordenada por fecha
    @Query(value = "SELECT * FROM monedacotizacion WHERE idMoneda=:idMoneda ORDER "
            + "BY fecha DESC", nativeQuery = true)
    public List<MonedaCotizacion> listarPorMoneda(@Param("idMoneda") int idMoneda);
    
    //Obtiene el ultimo registro por idMoneda
    @Query(value = "SELECT * FROM monedacotizacion WHERE idMoneda=:idMoneda ORDER "
            + "BY fecha DESC limit 1", nativeQuery = true)
    public MonedaCotizacion obtenerRecientePorMoneda(@Param("idMoneda") int idMoneda);
    
}
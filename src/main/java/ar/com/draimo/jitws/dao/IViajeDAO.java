//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Viaje;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Viaje 
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IViajeDAO extends JpaRepository<Viaje, Integer> {
    
    //Obtiene el ultimo registro
    public Viaje findTopByOrderByIdDesc();
    
    //Obtiene por filtros
    @Query(value = "SELECT * FROM viaje where ((:idViaje IS NULL or id=:idViaje) and (:fechaDesde IS NULL and :fechaHasta "
            + "IS  NULL) OR (fecha between :fechaDesde and :fechaHasta)) and (:idPersonal IS NULL or idPersonal=:idPersonal) "
            + "and (:idProveedor IS NULL or idProveedor=:idProveedor)", nativeQuery = true)
    public List<Viaje> listarPorFiltros(@Param("idViaje") String idVieja, @Param("fechaDesde") Date fechaDesde, 
            @Param("fechaHasta") Date fechaHasta, @Param("idPersonal") String idPersonal, @Param("idProveedor") String idProveedor);
    
    //Obtiene todos los registros
    @Query(value = "SELECT * FROM viaje", nativeQuery = true)
    public List<Viaje> obtenerTodos();
    
    //Obtiene un registro por id
    @Query(value = "SELECT * FROM viaje WHERE id=:id", nativeQuery = true)
    public Viaje obtenerViaje(@Param("id") int id);
    
}
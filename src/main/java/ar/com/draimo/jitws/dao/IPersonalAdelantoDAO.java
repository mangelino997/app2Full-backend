//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.PersonalAdelanto;
import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO PersonalAdelanto
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPersonalAdelantoDAO extends JpaRepository<PersonalAdelanto, Integer> {
    
    //Obtiene el siguiente id
    public PersonalAdelanto findTopByOrderByIdDesc();
    
    //Obtiene el id viaje del personal adelanto
    @Query(value = "SELECT idViaje FROM personaladelanto WHERE id=:idPersonalAdelanto", nativeQuery = true)
    public String obtenerIdViaje(@Param("idPersonalAdelanto") int idPersonalAdelanto);
    
    //Obtiene el id reparto del personal adelanto
    @Query(value = "SELECT idReparto FROM personaladelanto WHERE id=:idPersonalAdelanto", nativeQuery = true)
    public String obtenerIdReparto(@Param("idPersonalAdelanto") int idPersonalAdelanto);
    
    //Obtiene un listado por lote y/o fechaEmision
    @Query(value = "SELECT numeroLote, importe, observaciones, idUsuarioAlta FROM personaladelanto WHERE (:numeroLote=0 OR "
            + "numeroLote=:numeroLote) AND (:fechaEmision IS NULL OR fechaEmision=:fechaEmision) GROUP BY numeroLote", nativeQuery = true)
    public Object listarPorNumeroLote(@Param("numeroLote") int numeroLote, @Param("fechaEmision") Date fechaEmision);
}
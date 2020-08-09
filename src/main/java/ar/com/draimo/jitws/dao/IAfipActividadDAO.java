//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipActividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipActividad
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

/* extiende de JpaRespository porque es el encargado de realizar las peticiones a la base de datos
 y entre < > le indicamos que clase vamos a necesitar y con el cual va a trabajar 
*/
public interface IAfipActividadDAO extends JpaRepository<AfipActividad, Integer> {
    
    //Obtiene el siguiente id
    //con JPA al decirle findBy nos va a devolver lo que le solicitemos (Spring es el encargado
    // de devolverme la query)
    public AfipActividad findTopByOrderByIdDesc();
    
    //Obtiene una lista por alias
    // lo que le indicamos como parametro (en este caso 'alias') debe ser igual al atributo 
    // definido en la clase AfipActividad
    public List<AfipActividad> findByAliasContaining(String alias);
    
    //Obtiene una lista ordenada por codigoAfip
    public List<AfipActividad> findByOrderByCodigoAfipAsc();
    
}

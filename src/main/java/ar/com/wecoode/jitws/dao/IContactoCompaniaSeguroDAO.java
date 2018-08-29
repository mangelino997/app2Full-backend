//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.ContactoCompaniaSeguro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO ContactoCompaniaSeguro
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IContactoCompaniaSeguroDAO extends JpaRepository<ContactoCompaniaSeguro, Integer> {
    
    public final String NOMBRE_TABLA = "contactocompaniaseguro";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por nombre
    public List<ContactoCompaniaSeguro> findByNombreContaining(String nombre);
    
}

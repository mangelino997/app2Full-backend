//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Cliente
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IClienteDAO extends JpaRepository<Cliente, Integer> {
    
    public final String NOMBRE_TABLA = "cliente";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por razon social
    public List<Cliente> findByRazonSocialContaining(String razonSocial);
    
    //Obtiene una lista por nombre de fantasia
    public List<Cliente> findByNombreFantasiaContaining(String nombreFantasia);
    
    //Obtiene una lista por numero de documento
    public List<Cliente> findByNumeroDocumentoContaining(String numeroDocumento);
    
}

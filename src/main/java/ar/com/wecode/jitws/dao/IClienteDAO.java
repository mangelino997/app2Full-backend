//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    
    //Obtiene por numero de cuenta
    //public Cliente obtenerPorNumeroCuenta(int numeroCuenta);
    
    @Query(value = "SELECT * FROM cliente b WHERE b.razonSocial like %:razon%", nativeQuery = true)
    public List<Cliente> listarPorRazonSocial(@Param("razon") String razon);
    
    //Obtiene una lista por nombre de fantasia
    //public List<Cliente> listarPorNombreFantasia(String nombreFantasia);
    
    //Obtiene una lista por numero de documento
    //public List<Cliente> listarPorNumeroDocumento(String numeroDocumento);
    
}

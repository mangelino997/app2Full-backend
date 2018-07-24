//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.SucursalCliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Area
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISucursalClienteDAO extends JpaRepository<SucursalCliente, Integer> {
    
    public final String NOMBRE_TABLA = "sucursalcliente";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por nombre
    @Query(value = "SELECT * FROM sucursalcliente b WHERE b.nombre like %:nom%", nativeQuery = true)
    public List<SucursalCliente> listarPorNombre(@Param("nom") String nom);
    
    //Obtiene una lista por id cliente
    //public List<SucursalCliente> listarPorIdCliente(int idCliente);
    
}

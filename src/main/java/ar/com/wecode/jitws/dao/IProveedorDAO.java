//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Proveedor
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IProveedorDAO extends JpaRepository<Proveedor, Integer> {
    
    public final String NOMBRE_TABLA = "proveedor";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene por numero de cuenta
    //public Proveedor obtenerPorNumeroCuenta(int numeroCuenta);
    
    //Obtiene una lista por razon social
    @Query(value = "SELECT * FROM proveedor b WHERE b.razonSocial like %:nom%", nativeQuery = true)
    public List<Proveedor> listarPorRazonSocial(@Param("nom") String nom);
    
    //Obtiene una lista por nombre de fantasia
    @Query(value = "SELECT * FROM proveedor b WHERE b.nombreFantasia like %:nomFantasia%", nativeQuery = true)
    public List<Proveedor> listarPorNombreFantasia(@Param("nomFantasia") String nomFantasia);
    
    //Obtiene una lista por numero de documento
    @Query(value = "SELECT * FROM proveedor b WHERE b.numeroDocumento like %:numero%", nativeQuery = true)
    public List<Proveedor> listarPorNumeroDocumento(@Param("numero") String numero);
    
}

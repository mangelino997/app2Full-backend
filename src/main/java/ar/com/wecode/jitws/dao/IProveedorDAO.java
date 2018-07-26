//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    public Proveedor findByNumeroCuenta(int numeroCuenta);
    
    //Obtiene una lista por razon social
    public List<Proveedor> findByRazonSocialContaining(String razonSocial);
    
    //Obtiene una lista por nombre de fantasia
    public List<Proveedor> findByNombreFantasiaContaining(String nombreFantasia);
    
    //Obtiene una lista por numero de documento
    public List<Proveedor> findByNumeroDocumentoContaining(String numeroDocumento);
    
}

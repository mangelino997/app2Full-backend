//Paquete al que pertenece la interfaz
package ar.com.wecode.jitws.dao;

import ar.com.wecode.jitws.constant.NombreConstant;
import ar.com.wecode.jitws.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO Empresa
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IEmpresaDAO extends JpaRepository<Empresa, Integer> {
    
    public final String NOMBRE_TABLA = "empresa";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
    //Obtiene una lista por razon social
    @Query(value = "SELECT * FROM empresa b WHERE b.razonSocial like %:razon%", nativeQuery = true)
    public List<Empresa> listarPorNombre(@Param("razon") String razon);
    
}

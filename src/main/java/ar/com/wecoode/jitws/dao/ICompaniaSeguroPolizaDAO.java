//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.constant.NombreConstant;
import ar.com.wecoode.jitws.model.CompaniaSeguroPoliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DAO Compañia de seguro poliza
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ICompaniaSeguroPolizaDAO extends JpaRepository<CompaniaSeguroPoliza, Integer> {
    
    public final String NOMBRE_TABLA = "companiaseguro";
    
    //Obtiene el siguiente id
    @Query(value = "SELECT Auto_increment FROM information_schema.tables "
            + "WHERE table_name='" + NOMBRE_TABLA +"'" + " AND table_schema='" 
            + NombreConstant.NOMBRE_BASE_DATOS + "'", nativeQuery = true)
    public int obtenerSiguienteId();
    
}

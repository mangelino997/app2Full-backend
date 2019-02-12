//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.PlandeCuenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO PlandeCuenta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPlandeCuentaDAO extends JpaRepository<PlandeCuenta, Integer> {
    
    //Obtiene el siguiente id
    public PlandeCuenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<PlandeCuenta> findByNombreContaining(String nombre);
    
    @Query(value = "SELECT * FROM plandecuenta WHERE idEmpresa=:idEmpresa AND idGrupoCuentaContable=1 AND estaActivo=1", nativeQuery = true)
    public List<PlandeCuenta> listarGrupoActivo(@Param("idEmpresa") int idEmpresa);
    
}

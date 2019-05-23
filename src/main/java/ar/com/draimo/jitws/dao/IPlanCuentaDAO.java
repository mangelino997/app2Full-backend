//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.GrupoCuentaContable;
import ar.com.draimo.jitws.model.PlanCuenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO PlandeCuenta
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPlanCuentaDAO extends JpaRepository<PlanCuenta, Integer> {
    
    //Obtiene el siguiente id
    public PlanCuenta findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<PlanCuenta> findByNombreContaining(String nombre);
    
    //Obtiene un plan de cuenta por empresa y nivel
    public PlanCuenta findByEmpresaAndNivel(Empresa empresa, short nivel);
    
    @Query(value = "SELECT * FROM plandecuenta WHERE idEmpresa=:idEmpresa AND idGrupoCuentaContable=1 AND estaActivo=1", nativeQuery = true)
    public List<PlanCuenta> listarGrupoActivo(@Param("idEmpresa") int idEmpresa);
    
    //Obtiene una lista por padre
    public List<PlanCuenta> findByPadre(PlanCuenta planCuenta);
    
    //Obtiene por empresa y grupo cuenta contable
    public List<PlanCuenta> findByEmpresaAndGrupoCuentaContable(Empresa empresa, GrupoCuentaContable grupoCuentaContable);
    
}

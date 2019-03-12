//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;
import ar.com.draimo.jitws.model.Ejercicio;
import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO Ejercicio
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IEjercicioDAO extends JpaRepository<Ejercicio, Integer> {
    
    //Obtiene el siguiente id
    public Ejercicio findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<Ejercicio> findByEmpresaAndNombreContaining(Empresa empresa, String nombre);
    
    //Obtiene un listado por empresa
    public List<Ejercicio> findByEmpresa(Empresa empresa);
    
}

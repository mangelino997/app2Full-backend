//Paquete al que pertenece la interfaz
package ar.com.wecoode.jitws.dao;

import ar.com.wecoode.jitws.model.Banco;
import ar.com.wecoode.jitws.model.SucursalBanco;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO SucursalBanco
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface ISucursalBancoDAO extends JpaRepository<SucursalBanco, Integer> {
    
    //Obtiene el siguiente id
    public SucursalBanco findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<SucursalBanco> findByNombreContaining(String nombre);
    
    //Obtiene una lista por banco
    public List<SucursalBanco> findByBanco(Optional<Banco> banco);
    
    //Obtiene una lista por nombre de banco
    public List<SucursalBanco> findByBanco_NombreContaining(String nombre);
    
}

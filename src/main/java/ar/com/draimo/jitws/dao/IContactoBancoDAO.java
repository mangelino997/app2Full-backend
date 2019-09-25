//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ContactoBanco;
import ar.com.draimo.jitws.model.SucursalBanco;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO ContactoBanco
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IContactoBancoDAO extends JpaRepository<ContactoBanco, Integer> {
    
    //Obtiene el siguiente id
    public ContactoBanco findTopByOrderByIdDesc();
    
    //Obtiene una lista por nombre
    public List<ContactoBanco> findByNombreContaining(String nombre);
    
    //Obtiene una lista de contactos por sucursalBanco
    public List<ContactoBanco> findBySucursalBanco(Optional<SucursalBanco> elemento);
    
}
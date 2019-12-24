//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.PersonalCuentaBancaria;
import ar.com.draimo.jitws.model.Personal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO PersonalCuentaBancaria
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IPersonalCuentaBancariaDAO extends JpaRepository<PersonalCuentaBancaria, Integer> {
    
    //Obtiene el ultimo registro
    public PersonalCuentaBancaria findTopByOrderByIdDesc();
    
    //Obtiene una lista por Personal
    public List<PersonalCuentaBancaria> findByPersonal(Personal personal);
    
}
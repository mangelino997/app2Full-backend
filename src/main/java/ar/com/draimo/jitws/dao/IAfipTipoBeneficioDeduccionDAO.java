//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipDeduccionPersonal;
import ar.com.draimo.jitws.model.AfipTipoBeneficio;
import ar.com.draimo.jitws.model.AfipTipoBeneficioDeduccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO AfipTipoBeneficioDeduccion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipTipoBeneficioDeduccionDAO extends JpaRepository<AfipTipoBeneficioDeduccion, Integer> {
    
    //Obtiene el siguiente id
    public AfipTipoBeneficioDeduccion findTopByOrderByIdDesc();
    
    //Obtiene una lista por AfipTipoBeneficio
    public List<AfipTipoBeneficioDeduccion> findByAfipTipoBeneficio(AfipTipoBeneficio afipTipoBeneficio);
    
    //Obtiene una lista por AfipDeduccionPersonal
    public List<AfipTipoBeneficioDeduccion> findByAfipDeduccionPersonal(AfipDeduccionPersonal afipDeduccionPersonal);
    
}

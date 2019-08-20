//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipDeduccionPersonal;
import ar.com.draimo.jitws.model.AfipTipoBeneficio;
import ar.com.draimo.jitws.model.AfipTipoBeneficioDeduccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO AfipTipoBeneficioDeduccion
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipTipoBeneficioDeduccionDAO extends JpaRepository<AfipTipoBeneficioDeduccion, Integer> {
    
    //Obtiene el siguiente id
    public AfipTipoBeneficioDeduccion findTopByOrderByIdDesc();
    
    //Obtiene todos los registros ordenados
    public List<AfipTipoBeneficioDeduccion> findAllByOrderByAfipDeduccionPersonal_Id();
    
    //Obtiene una lista por AfipTipoBeneficio
    public List<AfipTipoBeneficioDeduccion> findByAnioAndAfipTipoBeneficioOrderByAfipDeduccionPersonal_Id(short anio, AfipTipoBeneficio afipTipoBeneficio);
    
    //Obtiene una lista por AfipTipoBeneficio, anio y deduccionPersonal
    public List<AfipTipoBeneficioDeduccion> findByAnioAndAfipTipoBeneficioAndAfipDeduccionPersonalOrderByAfipDeduccionPersonal_Id(
            short anio, AfipTipoBeneficio afipTipoBeneficio, AfipDeduccionPersonal afipDeduccionPersonal);
    
    //Obtiene una lista por filtros
    @Query(value = "SELECT id, version, anio, idAfipTipoBeneficio,  "
            + "idAfipDeduccionPersonal, CASE(:idMes) when 0 then importe else "
            + "ROUND(importe/12*:idMes, 2) end as importe FROM afiptipobeneficiodeduccion "
            + "WHERE idAfipTipoBeneficio=:idAfipTipoBeneficio AND anio=:anio", nativeQuery = true)
    public List<AfipTipoBeneficioDeduccion> listarPorFiltros(@Param("anio") short anio,
            @Param("idAfipTipoBeneficio")int idAfipTipoBeneficio,@Param("idMes") int idMes);
    
    //Obtiene una lista por AfipTipoBeneficio
    public List<AfipTipoBeneficioDeduccion> findByAfipTipoBeneficioOrderByAfipDeduccionPersonal_Id(AfipTipoBeneficio afipTipoBeneficio);
    
    //Obtiene una lista por AfipDeduccionPersonal
    public List<AfipTipoBeneficioDeduccion> findByAfipDeduccionPersonalOrderByAfipDeduccionPersonal_Id(AfipDeduccionPersonal afipDeduccionPersonal);
    
}

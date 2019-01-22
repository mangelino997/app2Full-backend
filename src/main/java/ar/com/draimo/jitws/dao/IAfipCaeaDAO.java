//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.AfipCaea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO AfipCaea
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IAfipCaeaDAO extends JpaRepository<AfipCaea, Integer> {
    
    //Obtiene el siguiente id
    public AfipCaea findTopByOrderByIdDesc();
    
    //Obtiene un registro por empresa, anio, mes y quincena
    @Query(value = "SELECT * FROM afipcaea where idEmpresa=:idEmpresa and "
            + "anio=:anio and mes=:mes and quincena=:quincena", nativeQuery = true)
    public AfipCaea obtenerPorEmpresaYPeriodoOrden(@Param("idEmpresa") int idEmpresa, 
            @Param("anio") short anio, @Param("mes") short mes, @Param("quincena") short quincena);
    
    //Obtiene una lista de registros por empresa y anio
    @Query(value = "SELECT * FROM afipcaea where idEmpresa=:idEmpresa and anio=:anio"
            + "  order by mes,quincena asc", nativeQuery = true)
    public List<AfipCaea> listarPorEmpresaYAnio(@Param("idEmpresa") int idEmpresa, @Param("anio") short anio);
    
}

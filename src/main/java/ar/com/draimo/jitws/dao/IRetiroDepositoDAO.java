//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.RetiroDeposito;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz DAO RetiroDeposito
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRetiroDepositoDAO extends JpaRepository<RetiroDeposito, Integer> {
    
    //Obtiene el ultimo registro
    public RetiroDeposito findTopByOrderByIdDesc();
    
    //Obtiene un listado de Retiro deposito por numero de documento
    public List<RetiroDeposito> findByNumeroDocumento(String elemento);
    
    //Obtiene un listado por empresa
    public List<RetiroDeposito> findByEmpresa(Optional<Empresa> empresa);
    
    //Obtiene un listado de planillas abiertas
    @Query(value = "SELECT * FROM retirodeposito WHERE estaCerrada=:estaCerrada", nativeQuery = true)
    public List<RetiroDeposito> listarPorEstaCerrada(@Param("estaCerrada") boolean estaCerrada);
    
    //Obtiene un litado de retirosDeposito por filtros
    @Query(value = "SELECT * FROM retirodeposito WHERE (:idEmpresa=0 OR idEmpresa=:idEmpresa) "
            + "AND (:idSucursal =0 OR idSucursal=:idSucursal) AND (fechaRegistracion"
            + "BETWEEN :fechaDesde AND :fechaHasta) AND estaCerrada=:estaCerrada", nativeQuery = true)
    public List<RetiroDeposito> listarPorFiltros(@Param("idEmpresa") int idEmpresa, 
            @Param("idSucursal") int idSucursal, @Param("fechaDesde") Date fechaDesde, 
            @Param("fechaHasta") Date fechaHasta, @Param("estaCerrada") boolean estaCerrada);
    
}
//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.RetiroDeposito;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO RetiroDeposito
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IRetiroDepositoDAO extends JpaRepository<RetiroDeposito, Integer> {
    
    //Obtiene el siguiente id
    public RetiroDeposito findTopByOrderByIdDesc();
    
    
    //Obtiene un listado de Retiro deposito por numero de documento
    public List<RetiroDeposito> findByNumeroDocumento(String elemento);
    
    //Obtiene un listado por empresa
    public List<RetiroDeposito> findByEmpresa(Optional<Empresa> empresa);
    
    //Obtiene un listado de planillas abiertas
    public List<RetiroDeposito> findByEstaCerradaFalse();
    
}

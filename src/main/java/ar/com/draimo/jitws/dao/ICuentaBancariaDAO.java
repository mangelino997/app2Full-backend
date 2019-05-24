package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.CuentaBancaria;
import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz CuentaBancaria DAO
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ICuentaBancariaDAO extends JpaRepository<CuentaBancaria, Integer> {
    
    //Obtiene el siguiente id
    public CuentaBancaria findTopByOrderByIdDesc();
    
    //Obtiene un listado por nombre
    public List<CuentaBancaria> findByEmpresa(Empresa empresa);
    
}

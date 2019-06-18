package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.TalonarioReciboLote;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz TalonarioReciboLote
 * Metodos contra la base de datos
 * @author blas
 */

@Repository
public interface ITalonarioReciboLoteDAO extends JpaRepository<TalonarioReciboLote, Integer> {
    
    //Obtiene el siguiente id
    public TalonarioReciboLote findTopByOrderByIdDesc();
    
    //Obtiene un listado por empresa y loteEntregadoFalse
    public List<TalonarioReciboLote> findByEmpresaAndLoteEntregadoFalse(Empresa empresa); 
    
}

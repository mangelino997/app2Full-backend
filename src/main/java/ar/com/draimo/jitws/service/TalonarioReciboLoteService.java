package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITalonarioReciboLoteDAO;
import ar.com.draimo.jitws.model.TalonarioReciboLote;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class TalonarioReciboLoteService {

    @Autowired
    ITalonarioReciboLoteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TalonarioReciboLote elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<TalonarioReciboLote> listar() {
        return elementoDAO.findAll();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TalonarioReciboLote agregar(TalonarioReciboLote elemento) {
        Date fecha = new Date(new java.util.Date().getTime());
        elemento.setFechaAlta(fecha);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TalonarioReciboLote elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(TalonarioReciboLote elemento) {
        elementoDAO.delete(elemento);
    }

}

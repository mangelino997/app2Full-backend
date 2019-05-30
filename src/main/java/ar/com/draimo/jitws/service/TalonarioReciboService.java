package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITalonarioReciboDAO;
import ar.com.draimo.jitws.model.TalonarioRecibo;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class TalonarioReciboService {

    @Autowired
    ITalonarioReciboDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TalonarioRecibo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<TalonarioRecibo> listar() {
        return elementoDAO.findAll();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TalonarioRecibo agregar(TalonarioRecibo elemento) {
        Date fecha = new Date(new java.util.Date().getTime());
        elemento.setFechaAlta(fecha);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TalonarioRecibo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(TalonarioRecibo elemento) {
        elementoDAO.delete(elemento);
    }

}

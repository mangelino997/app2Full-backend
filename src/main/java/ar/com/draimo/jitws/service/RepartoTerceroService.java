package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRepartoTerceroDAO;
import ar.com.draimo.jitws.model.RepartoTercero;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RepartoTercero
 * @author blas
 */

@Service
public class RepartoTerceroService {

    //Define la referencia al dao
    @Autowired
    IRepartoTerceroDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoTercero elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RepartoTercero> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoTercero agregar(RepartoTercero elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RepartoTercero elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RepartoTercero elemento) {
        elementoDAO.delete(elemento);
    }

}

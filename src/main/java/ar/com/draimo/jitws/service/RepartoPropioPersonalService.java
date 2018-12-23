package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRepartoPropioPersonalDAO;
import ar.com.draimo.jitws.model.RepartoPropioPersonal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio repartoPropioPersonal
 * @author blas
 */

@Service
public class RepartoPropioPersonalService {
    
    //Define la referencia al dao
    @Autowired
    IRepartoPropioPersonalDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoPropioPersonal elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RepartoPropioPersonal> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoPropioPersonal agregar(RepartoPropioPersonal elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RepartoPropioPersonal elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RepartoPropioPersonal elemento) {
        elementoDAO.delete(elemento);
    }
    
}

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.RepartoPersonal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IRepartoPersonalDAO;

/**
 * Servicio repartoPropioPersonal
 * @author blas
 */

@Service
public class RepartoPersonalService {
    
    //Define la referencia al dao
    @Autowired
    IRepartoPersonalDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoPersonal elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RepartoPersonal> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoPersonal agregar(RepartoPersonal elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RepartoPersonal elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RepartoPersonal elemento) {
        elementoDAO.delete(elemento);
    }
    
}

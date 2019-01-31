package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRepartoPropioDAO;
import ar.com.draimo.jitws.model.RepartoPropio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RepartoPropio
 * @author blas
 */

@Service
public class RepartoPropioService {

    //Define la referencia al dao
    @Autowired
    IRepartoPropioDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        RepartoPropio elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<RepartoPropio> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public RepartoPropio agregar(RepartoPropio elemento) {
        System.out.println(elemento.getFechaRegistracion());
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RepartoPropio elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(RepartoPropio elemento) {
        elementoDAO.delete(elemento);
    }

}

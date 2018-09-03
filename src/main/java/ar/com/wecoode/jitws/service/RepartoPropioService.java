package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IRepartoPropioDAO;
import ar.com.wecoode.jitws.model.RepartoPropio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<RepartoPropio> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(RepartoPropio elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(RepartoPropio elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(RepartoPropio elemento) {
        elementoDAO.delete(elemento);
    }

}

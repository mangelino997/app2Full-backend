package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IAgendaTelefonicaDAO;
import ar.com.wecoode.jitws.model.AgendaTelefonica;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Agenda Telefonica
 * @author blas
 */

@Service
public class AgendaTelefonicaService {
    
    
    //Define la referencia al dao
    @Autowired
    IAgendaTelefonicaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<AgendaTelefonica> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por nombre
    public List<AgendaTelefonica> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(AgendaTelefonica elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AgendaTelefonica elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(AgendaTelefonica elemento) {
        elementoDAO.delete(elemento);
    }
    
}

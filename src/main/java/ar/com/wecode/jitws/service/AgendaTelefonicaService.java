package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.IAgendaTelefonicaDAO;
import ar.com.wecode.jitws.model.AgendaTelefonica;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void agregar(AgendaTelefonica elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(AgendaTelefonica elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(AgendaTelefonica elemento) {
        elementoDAO.delete(elemento);
    }
    
}

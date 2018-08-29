package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IObraSocialDAO;
import ar.com.wecoode.jitws.model.ObraSocial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ObraSocial
 * @author blas
 */

@Service
public class ObraSocialService {
    
    //Define la referencia al dao
    @Autowired
    IObraSocialDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ObraSocial> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ObraSocial> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(ObraSocial elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(ObraSocial elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ObraSocial elemento) {
        elementoDAO.delete(elemento);
    }
    
}

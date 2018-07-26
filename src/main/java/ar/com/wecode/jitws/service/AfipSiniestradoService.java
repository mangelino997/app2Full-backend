package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.IAfipSiniestradoDAO;
import ar.com.wecode.jitws.model.AfipSiniestrado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio AfipSiniestrado
 * @author blas
 */

@Service
public class AfipSiniestradoService {
    
    //Define el dao
    @Autowired
    IAfipSiniestradoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<AfipSiniestrado> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<AfipSiniestrado> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(AfipSiniestrado elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(AfipSiniestrado elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(AfipSiniestrado elemento) {
        elementoDAO.delete(elemento);
    }
    
}

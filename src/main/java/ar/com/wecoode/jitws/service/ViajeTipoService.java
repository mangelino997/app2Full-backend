package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajeTipoDAO;
import ar.com.wecoode.jitws.model.ViajeTipo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ViajeTipo
 * @author blas
 */

@Service
public class ViajeTipoService {

    //Define la referencia al dao
    @Autowired
    IViajeTipoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajeTipo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ViajeTipo> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(ViajeTipo elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajeTipo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajeTipo elemento) {
        elementoDAO.delete(elemento);
    }

}

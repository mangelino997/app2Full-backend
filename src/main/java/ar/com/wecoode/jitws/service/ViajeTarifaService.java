package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajeTarifaDAO;
import ar.com.wecoode.jitws.model.ViajeTarifa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ViajeTarifa
 * @author blas
 */

@Service
public class ViajeTarifaService {

    //Define la referencia al dao
    @Autowired
    IViajeTarifaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajeTarifa> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ViajeTarifa> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(ViajeTarifa elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajeTarifa elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajeTarifa elemento) {
        elementoDAO.delete(elemento);
    }

}

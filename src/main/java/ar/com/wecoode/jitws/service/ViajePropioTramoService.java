package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajePropioTramoDAO;
import ar.com.wecoode.jitws.model.ViajePropioTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ViajePropioTramo
 * @author blas
 */

@Service
public class ViajePropioTramoService {

    //Define la referencia al dao
    @Autowired
    IViajePropioTramoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajePropioTramo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(ViajePropioTramo elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajePropioTramo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajePropioTramo elemento) {
        elementoDAO.delete(elemento);
    }

}

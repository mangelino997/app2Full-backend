package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajeTerceroTramoDAO;
import ar.com.wecoode.jitws.model.ViajeTerceroTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ViajeTerceroTramo
 * @author blas
 */

@Service
public class ViajeTerceroTramoService {

    //Define la referencia al dao
    @Autowired
    IViajeTerceroTramoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajeTerceroTramo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(ViajeTerceroTramo elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajeTerceroTramo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajeTerceroTramo elemento) {
        elementoDAO.delete(elemento);
    }

}

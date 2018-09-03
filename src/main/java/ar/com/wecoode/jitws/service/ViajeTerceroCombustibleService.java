package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajeTerceroCombustibleDAO;
import ar.com.wecoode.jitws.model.ViajeTerceroCombustible;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ViajeTerceroCombustible
 * @author blas
 */

@Service
public class ViajeTerceroCombustibleService {

    //Define la referencia al dao
    @Autowired
    IViajeTerceroCombustibleDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajeTerceroCombustible> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(ViajeTerceroCombustible elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajeTerceroCombustible elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajeTerceroCombustible elemento) {
        elementoDAO.delete(elemento);
    }

}

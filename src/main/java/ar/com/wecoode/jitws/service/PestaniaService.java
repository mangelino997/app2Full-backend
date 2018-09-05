package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IPestaniaDAO;
import ar.com.wecoode.jitws.model.Pestania;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Pestania
 * @author blas
 */

@Service
public class PestaniaService {

    //Define la referencia al dao
    @Autowired
    IPestaniaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Pestania> listar() {
        return elementoDAO.findAll();
    }

}

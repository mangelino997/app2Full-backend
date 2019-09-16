package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPestaniaDAO;
import ar.com.draimo.jitws.model.Pestania;
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
        Pestania elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<Pestania> listar() {
        return elementoDAO.findAll();
    }

}

package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.ICaeaDAO;
import ar.com.wecode.jitws.model.Caea;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Caea
 * @author blas
 */

@Service
public class CaeaService {
    
    //Define la referencia al dao
    @Autowired
    ICaeaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Caea> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agragar(Caea elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(Caea elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Caea elemento) {
        elementoDAO.delete(elemento);
    }
    
}

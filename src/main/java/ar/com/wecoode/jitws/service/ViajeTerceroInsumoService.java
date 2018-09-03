package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajeTerceroInsumoDAO;
import ar.com.wecoode.jitws.model.ViajeTerceroInsumo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ViajeTerceroInsumo
 * @author blas
 */

@Service
public class ViajeTerceroInsumoService {

    //Define la referencia al dao
    @Autowired
    IViajeTerceroInsumoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajeTerceroInsumo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(ViajeTerceroInsumo elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajeTerceroInsumo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajeTerceroInsumo elemento) {
        elementoDAO.delete(elemento);
    }

}

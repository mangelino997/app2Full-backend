package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajeTerceroEfectivoDAO;
import ar.com.wecoode.jitws.model.ViajeTerceroEfectivo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ViajeTerceroEfectivo
 * @author blas
 */

@Service
public class ViajeTerceroEfectivoService {

    //Define la referencia al dao
    @Autowired
    IViajeTerceroEfectivoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajeTerceroEfectivo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(ViajeTerceroEfectivo elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajeTerceroEfectivo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajeTerceroEfectivo elemento) {
        elementoDAO.delete(elemento);
    }

}

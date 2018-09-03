package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajePropioEfectivoDAO;
import ar.com.wecoode.jitws.model.ViajePropioEfectivo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ViajePropioEfectivo
 * @author blas
 */

@Service
public class ViajePropioEfectivoService {

    //Define la referencia al dao
    @Autowired
    IViajePropioEfectivoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajePropioEfectivo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(ViajePropioEfectivo elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajePropioEfectivo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajePropioEfectivo elemento) {
        elementoDAO.delete(elemento);
    }

}

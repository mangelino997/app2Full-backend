package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajePropioEfectivoDAO;
import ar.com.wecoode.jitws.model.ViajePropioEfectivo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void agregar(ViajePropioEfectivo elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropioEfectivo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajePropioEfectivo elemento) {
        elementoDAO.delete(elemento);
    }

}

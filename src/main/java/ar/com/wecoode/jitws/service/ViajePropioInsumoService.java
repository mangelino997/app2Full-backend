package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajePropioInsumoDAO;
import ar.com.wecoode.jitws.model.ViajePropioInsumo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePropioInsumo
 * @author blas
 */

@Service
public class ViajePropioInsumoService {

    //Define la referencia al dao
    @Autowired
    IViajePropioInsumoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajePropioInsumo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(ViajePropioInsumo elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropioInsumo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajePropioInsumo elemento) {
        elementoDAO.delete(elemento);
    }

}

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeTerceroInsumoDAO;
import ar.com.draimo.jitws.model.ViajeTerceroInsumo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ViajeTerceroInsumo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<ViajeTerceroInsumo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTerceroInsumo agregar(ViajeTerceroInsumo elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTerceroInsumo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajeTerceroInsumo elemento) {
        elementoDAO.delete(elemento);
    }

}

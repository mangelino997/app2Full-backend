package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISueldoCFGDAO;
import ar.com.draimo.jitws.model.SueldoCFG;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio SueldoCFG
 * @author blas
 */

@Service
public class SueldoCFGService {

    //Define la referencia al dao
    @Autowired
    ISueldoCFGDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SueldoCFG elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<SueldoCFG> listar() {
        return elementoDAO.findAll();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SueldoCFG agregar(SueldoCFG elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SueldoCFG elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(SueldoCFG elemento) {
        elementoDAO.delete(elemento);
    }

}

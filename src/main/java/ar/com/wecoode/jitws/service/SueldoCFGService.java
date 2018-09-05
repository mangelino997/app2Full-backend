package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ISueldoCFGDAO;
import ar.com.wecoode.jitws.model.SueldoCFG;
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
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<SueldoCFG> listar() {
        return elementoDAO.findAll();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(SueldoCFG elemento) {
        elementoDAO.saveAndFlush(elemento);
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

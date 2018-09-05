package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ITipoDocumentoDAO;
import ar.com.wecoode.jitws.model.TipoDocumento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio TipoDocumento
 * @author blas
 */

@Service
public class TipoDocumentoService {

    //Define la referencia al dao
    @Autowired
    ITipoDocumentoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<TipoDocumento> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<TipoDocumento> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(TipoDocumento elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoDocumento elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(TipoDocumento elemento) {
        elementoDAO.delete(elemento);
    }

}

package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IEstadoCivilDAO;
import ar.com.wecoode.jitws.model.EstadoCivil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Estado Civil
 * @author blas
 */

@Service
public class EstadoCivilService {
    
    //Define la referencia al dao
    @Autowired
    IEstadoCivilDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<EstadoCivil> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<EstadoCivil> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(EstadoCivil elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(EstadoCivil elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(EstadoCivil elemento) {
        elementoDAO.delete(elemento);
    }
    
}

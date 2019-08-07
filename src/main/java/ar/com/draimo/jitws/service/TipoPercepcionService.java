package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoPercepcionDAO;
import ar.com.draimo.jitws.model.TipoPercepcion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class TipoPercepcionService {

    @Autowired
    ITipoPercepcionDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TipoPercepcion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<TipoPercepcion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<TipoPercepcion> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoPercepcion agregar(TipoPercepcion elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoPercepcion elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private TipoPercepcion formatearStrings(TipoPercepcion elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

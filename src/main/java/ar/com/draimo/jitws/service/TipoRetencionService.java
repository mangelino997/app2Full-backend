package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoRetencionDAO;
import ar.com.draimo.jitws.model.TipoRetencion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class TipoRetencionService {

    @Autowired
    ITipoRetencionDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TipoRetencion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<TipoRetencion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<TipoRetencion> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoRetencion agregar(TipoRetencion elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoRetencion elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private TipoRetencion formatearStrings(TipoRetencion elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

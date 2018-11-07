package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoContactoDAO;
import ar.com.draimo.jitws.model.TipoContacto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio TipoContacto
 * @author blas
 */

@Service
public class TipoContactoService {

    //Define la referencia al dao
    @Autowired
    ITipoContactoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TipoContacto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<TipoContacto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<TipoContacto> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoContacto agregar(TipoContacto elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoContacto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(TipoContacto elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private TipoContacto formatearStrings(TipoContacto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

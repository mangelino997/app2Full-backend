package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISoporteEstadoDAO;
import ar.com.draimo.jitws.model.SoporteEstado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class SoporteEstadoService {

    @Autowired
    ISoporteEstadoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SoporteEstado elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<SoporteEstado> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<SoporteEstado> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SoporteEstado agregar(SoporteEstado elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SoporteEstado elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(SoporteEstado elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private SoporteEstado formatearStrings(SoporteEstado elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

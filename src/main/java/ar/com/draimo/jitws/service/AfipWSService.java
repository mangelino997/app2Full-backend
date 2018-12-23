package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipWSDAO;
import ar.com.draimo.jitws.model.AfipWS;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase servicio del modelo
 * @author blas
 */

@Service
public class AfipWSService {

    //Crea una instancia del DAO
    @Autowired
    IAfipWSDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipWS elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<AfipWS> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<AfipWS> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipWS agregar(AfipWS elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipWS elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(AfipWS elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private AfipWS formatearStrings(AfipWS elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

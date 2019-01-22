package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IMesDAO;
import ar.com.draimo.jitws.model.Mes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Mes
 * @author blas
 */

@Service
public class MesService {
    
    //Define la referencia al dao
    @Autowired
    IMesDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Mes elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<Mes> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Mes> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Mes agregar(Mes elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Mes elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Mes elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Mes formatearStrings(Mes elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}

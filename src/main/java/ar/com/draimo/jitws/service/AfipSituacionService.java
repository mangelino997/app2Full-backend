package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IAfipSituacionDAO;
import ar.com.draimo.jitws.model.AfipSituacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipSituacion
 * @author blas
 */

@Service
public class AfipSituacionService {
    
    //Define el dao
    @Autowired
    IAfipSituacionDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipSituacion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipSituacion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<AfipSituacion> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipSituacion agregar(AfipSituacion elemento) {
        formatearString(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipSituacion elemento) {
        formatearString(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(AfipSituacion elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los string 
    private AfipSituacion formatearString(AfipSituacion elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        elemento.setAlias(elemento.getCodigoAfip() + " - " + elemento.getNombre());
        return elemento;
    } 
    
}

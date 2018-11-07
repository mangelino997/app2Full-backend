package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajePropioTramoDAO;
import ar.com.draimo.jitws.model.ViajePropioTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePropioTramo
 * @author blas
 */

@Service
public class ViajePropioTramoService {

    //Define la referencia al dao
    @Autowired
    IViajePropioTramoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajePropioTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajePropioTramo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePropioTramo agregar(ViajePropioTramo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropioTramo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajePropioTramo elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ViajePropioTramo formatearStrings(ViajePropioTramo elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }

}

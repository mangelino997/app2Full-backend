package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeCombustible;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeCombustibleDAO;
import ar.com.draimo.jitws.dao.IViajeDAO;

/**
 * Servicio ViajeCombustible
 * @author blas
 */

@Service
public class ViajeCombustibleService {

    //Define la referencia al dao
    @Autowired
    IViajeCombustibleDAO elementoDAO;
    
    //Define la referencia al dao viaje
    @Autowired
    IViajeDAO viajeDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeCombustible elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajeCombustible> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista de tramos por viaje
    public List<ViajeCombustible> listarCombustibles(int idViaje) {
        return elementoDAO.findByViaje(viajeDAO.obtenerPorId(idViaje));
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeCombustible agregar(ViajeCombustible elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeCombustible actualizar(ViajeCombustible elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajeCombustible formatearStrings(ViajeCombustible elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

}

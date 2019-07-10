package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajePeaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajePeajeDAO;

/**
 * Servicio ViajePeaje
 * @author blas
 */

@Service
public class ViajePeajeService {

    //Define la referencia al dao
    @Autowired
    IViajePeajeDAO elementoDAO;
    
    //Define la referencia al dao viaje
    @Autowired
    IViajeDAO viajeDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajePeaje elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajePeaje> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista de peajes por viaje propio
    public List<ViajePeaje> listarPeajes(int idViaje) {
        return elementoDAO.findByViaje(viajeDAO.obtenerPorId(idViaje));
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePeaje agregar(ViajePeaje elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePeaje elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajePeaje formatearStrings(ViajePeaje elemento) {
        elemento.setLetra(elemento.getLetra().trim());
        return elemento;
    }

}

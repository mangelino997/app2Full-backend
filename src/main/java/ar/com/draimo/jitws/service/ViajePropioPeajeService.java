package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajePropioPeajeDAO;
import ar.com.draimo.jitws.model.ViajePropioPeaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePropioPeaje
 * @author blas
 */

@Service
public class ViajePropioPeajeService {

    //Define la referencia al dao
    @Autowired
    IViajePropioPeajeDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajePropioPeaje elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajePropioPeaje> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePropioPeaje agregar(ViajePropioPeaje elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropioPeaje elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajePropioPeaje elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ViajePropioPeaje formatearStrings(ViajePropioPeaje elemento) {
        elemento.setLetra(elemento.getLetra().trim());
        return elemento;
    }

}

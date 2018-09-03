package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajePropioPeajeDAO;
import ar.com.wecoode.jitws.model.ViajePropioPeaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajePropioPeaje> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(ViajePropioPeaje elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajePropioPeaje elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajePropioPeaje elemento) {
        elementoDAO.delete(elemento);
    }

}

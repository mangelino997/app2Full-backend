package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajeTramoDAO;

/**
 * Servicio ViajeTramo
 * @author blas
 */

@Service
public class ViajeTramoService {

    //Define la referencia al dao
    @Autowired
    IViajeTramoDAO elementoDAO;
    
    //Define la referencia al dao de viaje
    @Autowired
    IViajeDAO viajePropioDAO; 
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene una lista de tramos por viaje propio
    public List<ViajeTramo> listarTramos(int idViaje) {
        return elementoDAO.findByViaje(viajePropioDAO.obtenerPorId(idViaje));
    }
    
    //Obtiene la lista completa
    public List<ViajeTramo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTramo agregar(ViajeTramo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTramo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajeTramo formatearStrings(ViajeTramo elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }

}

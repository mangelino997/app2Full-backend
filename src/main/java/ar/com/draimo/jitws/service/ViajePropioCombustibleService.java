package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajePropioCombustibleDAO;
import ar.com.draimo.jitws.dao.IViajePropioDAO;
import ar.com.draimo.jitws.model.ViajePropioCombustible;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePropioCombustible
 * @author blas
 */

@Service
public class ViajePropioCombustibleService {

    //Define la referencia al dao
    @Autowired
    IViajePropioCombustibleDAO elementoDAO;
    
    //Define la referencia al dao viaje propio
    @Autowired
    IViajePropioDAO viajePropioDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajePropioCombustible elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajePropioCombustible> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista de tramos por viaje propio
    public List<ViajePropioCombustible> listarCombustibles(int idViajePropio) {
        return elementoDAO.findByViajePropio(viajePropioDAO.obtenerPorId(idViajePropio));
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePropioCombustible agregar(ViajePropioCombustible elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropioCombustible elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajePropioCombustible formatearStrings(ViajePropioCombustible elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

}

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeInsumo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajeInsumoDAO;

/**
 * Servicio ViajeInsumo
 * @author blas
 */

@Service
public class ViajeInsumoService {

    //Define la referencia al dao
    @Autowired
    IViajeInsumoDAO elementoDAO;
    
    //Define la referencia al dao viaje propio
    @Autowired
    IViajeDAO viajeDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeInsumo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajeInsumo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista de insumos por viaje propio
    public List<ViajeInsumo> listarInsumos(int idViaje) {
        return elementoDAO.findByViaje(viajeDAO.obtenerPorId(idViaje));
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeInsumo agregar(ViajeInsumo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeInsumo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajeInsumo formatearStrings(ViajeInsumo elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

}

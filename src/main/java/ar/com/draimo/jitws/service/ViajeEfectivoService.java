package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeEfectivo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajeEfectivoDAO;

/**
 * Servicio ViajeEfectivo
 * @author blas
 */

@Service
public class ViajeEfectivoService {

    //Define la referencia al dao
    @Autowired
    IViajeEfectivoDAO elementoDAO;
    
    //Define la referencia al dao viaje
    @Autowired
    IViajeDAO viajeDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeEfectivo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajeEfectivo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista de efectivos por viaje propio
    public List<ViajeEfectivo> listarEfectivos(int idViaje) {
        return elementoDAO.findByViaje(viajeDAO.obtenerPorId(idViaje));
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeEfectivo agregar(ViajeEfectivo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeEfectivo actualizar(ViajeEfectivo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajeEfectivo formatearStrings(ViajeEfectivo elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

}

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajePropioDAO;
import ar.com.draimo.jitws.dao.IViajePropioEfectivoDAO;
import ar.com.draimo.jitws.model.ViajePropioEfectivo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePropioEfectivo
 * @author blas
 */

@Service
public class ViajePropioEfectivoService {

    //Define la referencia al dao
    @Autowired
    IViajePropioEfectivoDAO elementoDAO;
    
    //Define la referencia al dao viaje propio
    @Autowired
    IViajePropioDAO viajePropioDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajePropioEfectivo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajePropioEfectivo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista de efectivos por viaje propio
    public List<ViajePropioEfectivo> listarEfectivos(int idViajePropio) {
        return elementoDAO.findByViajePropio(viajePropioDAO.obtenerPorId(idViajePropio));
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePropioEfectivo agregar(ViajePropioEfectivo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropioEfectivo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajePropioEfectivo formatearStrings(ViajePropioEfectivo elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

}

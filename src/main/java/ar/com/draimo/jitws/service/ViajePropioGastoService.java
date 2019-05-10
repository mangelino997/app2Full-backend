package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajePropioDAO;
import ar.com.draimo.jitws.dao.IViajePropioGastoDAO;
import ar.com.draimo.jitws.model.ViajePropioGasto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePropioGasto
 * @author blas
 */

@Service
public class ViajePropioGastoService {

    //Define la referencia al dao
    @Autowired
    IViajePropioGastoDAO elementoDAO;
    
    //Define la referencia al dao viaje propio
    @Autowired
    IViajePropioDAO viajePropioDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajePropioGasto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajePropioGasto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista de gastos por viaje propio
    public List<ViajePropioGasto> listarGastos(int idViajePropio) {
        return elementoDAO.findByViajePropio(viajePropioDAO.obtenerPorId(idViajePropio));
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePropioGasto agregar(ViajePropioGasto elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePropioGasto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajePropioGasto formatearStrings(ViajePropioGasto elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

}

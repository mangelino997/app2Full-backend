package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajePrecioDAO;
import ar.com.draimo.jitws.dao.IViajeTarifaDAO;
import ar.com.draimo.jitws.dao.IViajeTipoDAO;
import ar.com.draimo.jitws.model.ViajePrecio;
import ar.com.draimo.jitws.model.ViajeTarifa;
import ar.com.draimo.jitws.model.ViajeTipo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajePrecio
 * @author blas
 */

@Service
public class ViajePrecioService {

    //Define la referencia al dao
    @Autowired
    IViajePrecioDAO elementoDAO;
    
    //Defina la referencia al dao viaje tipo
    @Autowired
    IViajeTipoDAO viajeTipoDAO;
    
    //Define la referencia al dao viaje tarifa
    @Autowired
    IViajeTarifaDAO viajeTarifaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajePrecio elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajePrecio> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene el costo
    public ViajePrecio obtenerCosto(int idViajeTipo, int idViajeTarifa) {
        //Obtiene el viaje tipo por id
        Optional<ViajeTipo> viajeTipo = viajeTipoDAO.findById(idViajeTipo);
        //Obtiene el viaje tarifa por id
        Optional<ViajeTarifa> viajeTarifa = viajeTarifaDAO.findById(idViajeTipo);
        return elementoDAO.findByViajeTipoAndViajeTarifa(viajeTipo, viajeTarifa);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePrecio agregar(ViajePrecio elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajePrecio actualizar(ViajePrecio elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajePrecio elemento) {
        elementoDAO.delete(elemento);
    }

}

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeTerceroDAO;
import ar.com.draimo.jitws.dao.IViajeTerceroTramoDAO;
import ar.com.draimo.jitws.model.ViajeTerceroTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajeTerceroTramo
 * @author blas
 */

@Service
public class ViajeTerceroTramoService {

    //Define la referencia al dao
    @Autowired
    IViajeTerceroTramoDAO elementoDAO;
    
    //Define la referencia al dao de viajeTercero
    @Autowired
    IViajeTerceroDAO viajeTerceroDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTerceroTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ViajeTerceroTramo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista de tramos por viaje tercero
    public List<ViajeTerceroTramo> listarTramos(int idViajeTercero) {
        return elementoDAO.findByViajeTercero(viajeTerceroDAO.findById(idViajeTercero));
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTerceroTramo agregar(ViajeTerceroTramo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTerceroTramo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ViajeTerceroTramo elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ViajeTerceroTramo formatearStrings(ViajeTerceroTramo elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }

}

package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IViajeTerceroTramoDAO;
import ar.com.wecoode.jitws.model.ViajeTerceroTramo;
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
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTerceroTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<ViajeTerceroTramo> listar() {
        return elementoDAO.findAll();
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
        elemento.setObservaciones(Funcion.primerLetraAMayuscula(elemento.getObservaciones().trim()));
        return elemento;
    }

}

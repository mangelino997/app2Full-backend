package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVentaConfigDAO;
import ar.com.draimo.jitws.model.VentaConfig;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio VentaConfig
 * @author blas
 */

@Service
public class VentaConfigService {

    //Define la referencia al dao
    @Autowired
    IVentaConfigDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaConfig elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<VentaConfig> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaConfig agregar(VentaConfig elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaConfig elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(VentaConfig elemento) {
        elementoDAO.delete(elemento);
    }
    
}

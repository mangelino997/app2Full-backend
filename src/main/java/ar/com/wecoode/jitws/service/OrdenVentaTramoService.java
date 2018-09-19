package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IOrdenVentaTramoDAO;
import ar.com.wecoode.jitws.model.OrdenVentaTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio OrdenVentaTramo
 * @author blas
 */

@Service
public class OrdenVentaTramoService {
    
    //Define la referencia al dao
    @Autowired
    IOrdenVentaTramoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVentaTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<OrdenVentaTramo> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenVentaTramo agregar(OrdenVentaTramo elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrdenVentaTramo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(OrdenVentaTramo elemento) {
        elementoDAO.delete(elemento);
    }
    
}
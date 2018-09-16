package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IOrdenVentaEscalaDAO;
import ar.com.wecoode.jitws.model.OrdenVentaEscala;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio OrdenVentaEscala
 * @author blas
 */

@Service
public class OrdenVentaEscalaService {
    
    //Define la referencia al dao
    @Autowired
    IOrdenVentaEscalaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVentaEscala elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<OrdenVentaEscala> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenVentaEscala agregar(OrdenVentaEscala elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrdenVentaEscala elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(OrdenVentaEscala elemento) {
        elementoDAO.delete(elemento);
    }
    
}

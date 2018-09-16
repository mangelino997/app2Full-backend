package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IPuntoVentaDAO;
import ar.com.wecoode.jitws.model.PuntoVenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Punto de Venta
 * @author blas
 */

@Service
public class PuntoVentaService {

    //Define la referencia al dao
    @Autowired
    IPuntoVentaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PuntoVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<PuntoVenta> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PuntoVenta agregar(PuntoVenta elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PuntoVenta elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(PuntoVenta elemento) {
        elementoDAO.delete(elemento);
    }

}

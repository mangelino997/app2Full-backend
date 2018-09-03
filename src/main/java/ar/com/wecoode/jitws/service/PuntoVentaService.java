package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IPuntoVentaDAO;
import ar.com.wecoode.jitws.model.PuntoVenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<PuntoVenta> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(PuntoVenta elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(PuntoVenta elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(PuntoVenta elemento) {
        elementoDAO.delete(elemento);
    }

}

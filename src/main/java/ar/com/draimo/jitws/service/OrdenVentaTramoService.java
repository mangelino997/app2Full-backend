package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTramoDAO;
import ar.com.draimo.jitws.model.OrdenVentaTramo;
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
    
    //Define la referencia al dao orden venta
    @Autowired
    IOrdenVentaDAO ordenVentaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVentaTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<OrdenVentaTramo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por orden de venta
    public List<OrdenVentaTramo> listarPorOrdenVenta(int idOrdenVenta) {
        return elementoDAO.findByOrdenVenta(ordenVentaDAO.findById(idOrdenVenta).get());
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

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEscalaTarifaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaPrecioDAO;
import ar.com.draimo.jitws.model.OrdenVentaPrecio;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio OrdenVentaPrecio
 * @author blas
 */

@Service
public class OrdenVentaPrecioService {
    
    //Define la referencia al dao
    @Autowired
    IOrdenVentaPrecioDAO elementoDAO;
    
    //Define la referencia al dao de ordenVenta
    @Autowired
    IOrdenVentaDAO ordenVentaDAO;
    
    //Define la referencia al dao escala tarifa
    @Autowired
    IEscalaTarifaDAO escalaTarifaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVentaPrecio elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<OrdenVentaPrecio> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene un listado por orden venta.
    public List<OrdenVentaPrecio> listarPorOrdenVenta(int idOrdenVenta) throws IOException {
        return elementoDAO.findByOrdenVenta(ordenVentaDAO.findById(idOrdenVenta).get());
    }
    
    //Obtiene una lista por orden de venta y precios desde
    public List<OrdenVentaPrecio> listarPorOrdenVentaYPreciosDesde(int idOrdenVenta, Date preciosDesde) {
        return elementoDAO.findByOrdenVentaAndPreciosDesde(ordenVentaDAO.findById(idOrdenVenta).get(), preciosDesde);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenVentaPrecio agregar(OrdenVentaPrecio elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrdenVentaPrecio elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
}

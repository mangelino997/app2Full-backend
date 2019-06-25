package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTramoDAO;
import ar.com.draimo.jitws.model.OrdenVentaTramo;
import java.sql.Date;
import java.util.ArrayList;
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
    
    //Define la referencia al dao de orden venta
    @Autowired
    IOrdenVentaDAO ordenVentaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVentaTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene una lista por orden de venta y precios desde
    public List<OrdenVentaTramo> listarPorOrdenVentaYPreciosDesde(int idOrdenVenta, String preciosDesde) {
        Date precios = Date.valueOf(preciosDesde) ;
        List<OrdenVentaTramo> ordenesTramo = 
            elementoDAO.findByOrdenVentaTarifa_OrdenVentaAndOrdenVentaTarifa_PreciosDesde(
                ordenVentaDAO.findById(idOrdenVenta).get(), precios);
        return ordenesTramo;
    }
    
    //Obtiene la lista de fechas por orden de venta
    public List<OrdenVentaTramo> listarFechasPorOrdenVenta(int idOrdenVenta) {
        List<OrdenVentaTramo> ordenesTramo = elementoDAO.findByOrdenVentaTarifa_OrdenVenta(
                ordenVentaDAO.findById(idOrdenVenta).get());
        List<OrdenVentaTramo> ordenesTramoFechaDistinta = new ArrayList<>();
        List<Date> fechas = new ArrayList<>();
        for(OrdenVentaTramo elemento : ordenesTramo) {
            if(!fechas.contains(elemento.getOrdenVentaTarifa().getPreciosDesde())) {
                fechas.add(elemento.getOrdenVentaTarifa().getPreciosDesde());
                ordenesTramoFechaDistinta.add(elemento);
            }
        }
        return ordenesTramoFechaDistinta;
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
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
}

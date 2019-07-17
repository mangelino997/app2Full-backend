package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEscalaTarifaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaEscalaDAO;
import ar.com.draimo.jitws.model.OrdenVentaTarifa;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IOrdenVentaTarifaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTramoDAO;

/**
 * Servicio OrdenVentaTarifa
 * @author blas
 */

@Service
public class OrdenVentaTarifaService {
    
    //Define la referencia al dao
    @Autowired
    IOrdenVentaTarifaDAO elementoDAO;
    
    //Define la referencia al dao de ordenVenta
    @Autowired
    IOrdenVentaDAO ordenVentaDAO;
    
    //Define la referencia al dao escala tarifa
    @Autowired
    IEscalaTarifaDAO escalaTarifaDAO;
    
    //Define la referencia al dao ordenVentaescala
    @Autowired
    IOrdenVentaEscalaDAO ordenVentaEscalaDAO;
    
    //Define la referencia al dao ordenVentaTramo
    @Autowired
    IOrdenVentaTramoDAO ordenVentaTramoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVentaTarifa elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<OrdenVentaTarifa> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene un listado por orden venta.
    public List<OrdenVentaTarifa> listarPorOrdenVenta(int idOrdenVenta) throws IOException {
        return elementoDAO.findByOrdenVenta(ordenVentaDAO.findById(idOrdenVenta).get());
    }
    
    //Obtiene una lista por orden de venta y precios desde
    public List<OrdenVentaTarifa> listarPorOrdenVentaYPreciosDesde(int idOrdenVenta, Date preciosDesde) {
        return elementoDAO.findByOrdenVentaAndPreciosDesde(ordenVentaDAO.findById(idOrdenVenta).get(), preciosDesde);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenVentaTarifa agregar(OrdenVentaTarifa elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrdenVentaTarifa elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        OrdenVentaTarifa elemento = elementoDAO.findById(id).get();
        if(elemento.getTipoTarifa().isPorEscala()){
            ordenVentaEscalaDAO.deleteByOrdenVentaTarifa(elemento);
        } else {
            ordenVentaTramoDAO.deleteByOrdenVentaTarifa(elemento);
        }
        elementoDAO.deleteById(id);
    }
    
}

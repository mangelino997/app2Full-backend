package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEscalaTarifaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaEscalaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTarifaDAO;
import ar.com.draimo.jitws.model.EscalaTarifa;
import ar.com.draimo.jitws.model.OrdenVentaEscala;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
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
    
    //Define la referencia al dao escala tarifa
    @Autowired
    IEscalaTarifaDAO escalaTarifaDAO;
    
    //Define la referencia al dao ordenVenta
    @Autowired
    IOrdenVentaDAO ordenVentaDAO;
    
    //Define la referencia al dao ordenVentaTarifa
    @Autowired
    IOrdenVentaTarifaDAO ordenVentaTarifaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVentaEscala elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<OrdenVentaEscala> listar() {
        return elementoDAO.findAll();
    }
    
    /*
    * Obtiene una lista con todas las escalas tarifas
    */
    public List<OrdenVentaEscala> listarConEscalaTarifa() {
        //Obtiene la lista completa de escalas tarifas
        List<EscalaTarifa> escalasTarifas = escalaTarifaDAO.findAll();
        //Ordena la lista de escalas tarifas
        escalasTarifas.sort(Comparator.comparing(EscalaTarifa::getValor));
        //Crea una lista vacia de ordenes de ventas escalas
        List<OrdenVentaEscala> ordenesVentasEscalas = new ArrayList<>();
        //Define una orden venta escala
        OrdenVentaEscala ordenVentaEscala;
        //Recorre la lista de escalas tarifas
        for(EscalaTarifa escalaTarifa : escalasTarifas) {
            ordenVentaEscala = new OrdenVentaEscala();
            ordenVentaEscala.setEscalaTarifa(escalaTarifa);
            ordenesVentasEscalas.add(ordenVentaEscala);
        }
        //Retorna los datos
        return ordenesVentasEscalas;
    }
    
    //Obtiene un listado por orden venta.
    public List<OrdenVentaEscala> listarPorOrdenVenta(int idOrdenVenta) {
        List<OrdenVentaEscala> ordenesEscala = elementoDAO.findByOrdenVentaTarifa_OrdenVenta(
             ordenVentaDAO.findById(idOrdenVenta).get());
        return ordenesEscala;
    }
    
    //Obtiene un listado por ordenVentaTarifa
    public List<OrdenVentaEscala> listarPorOrdenVentaTarifa(int idOrdenVentaTarifa) {
        List<OrdenVentaEscala> ordenesEscala = elementoDAO.findByOrdenVentaTarifa(
             ordenVentaTarifaDAO.findById(idOrdenVentaTarifa).get());
        return ordenesEscala;
    }
    
    //Obtiene una lista por orden de venta y precios desde
    public List<OrdenVentaEscala> listarPorOrdenVentaYPreciosDesde(int idOrdenVenta, String preciosDesde) {
        Date precios = Date.valueOf(preciosDesde) ;
        List<OrdenVentaEscala> ordenesEscala = 
            elementoDAO.findByOrdenVentaTarifa_OrdenVentaAndOrdenVentaTarifa_PreciosDesde(
                ordenVentaDAO.findById(idOrdenVenta).get(), precios);
        return ordenesEscala;
    }
    
    //Obtiene la lista de fechas por orden de venta
    public List<OrdenVentaEscala> listarFechasPorOrdenVenta(int idOrdenVenta) {
        List<OrdenVentaEscala> ordenesEscala = elementoDAO.findByOrdenVentaTarifa_OrdenVenta(
                ordenVentaDAO.findById(idOrdenVenta).get());
        List<OrdenVentaEscala> ordenesEscalaFechaDistinta = new ArrayList<>();
        List<Date> fechas = new ArrayList<>();
        for(OrdenVentaEscala elemento : ordenesEscala) {
            if(!fechas.contains(elemento.getOrdenVentaTarifa().getPreciosDesde())) {
                fechas.add(elemento.getOrdenVentaTarifa().getPreciosDesde());
                ordenesEscalaFechaDistinta.add(elemento);
            }
        }
        return ordenesEscalaFechaDistinta;
    }
    
    //Obtiene el precio del flete
    public BigDecimal obtenerPrecioFlete(int idOrdenVenta, String valor) {
        BigDecimal v = new BigDecimal(valor);
        List<EscalaTarifa> escalaTarifas = escalaTarifaDAO.obtenerDosEscalasporIdOrdenVenta(idOrdenVenta);
        BigDecimal valorHasta = escalaTarifas.get(0).getValor().subtract
        (escalaTarifas.get(1).getValor()).subtract(new BigDecimal(1.00)).setScale(2, RoundingMode.UNNECESSARY);
        OrdenVentaEscala ordenVentaEscala = elementoDAO.obtenerPorOrdenVentaYValorProximo
        (idOrdenVenta, v, v.add(valorHasta));
        BigDecimal precioFlete;
        if (ordenVentaEscala.getImporteFijo()!=null) {
            precioFlete = ordenVentaEscala.getImporteFijo();
        } else {
            
            precioFlete = (v.multiply(ordenVentaEscala.getPrecioUnitario()));
        }
        return precioFlete;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenVentaEscala agregar(OrdenVentaEscala elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrdenVentaEscala elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
}

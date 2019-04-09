package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEscalaTarifaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaEscalaDAO;
import ar.com.draimo.jitws.model.EscalaTarifa;
import ar.com.draimo.jitws.model.OrdenVentaEscala;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    
    //Define la referencia al dao de ordenVenta
    @Autowired
    IOrdenVentaDAO ordenVentaDAO;
    
    //Define la referencia al dao escala tarifa
    @Autowired
    IEscalaTarifaDAO escalaTarifaDAO;
    
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
    * Obtiene una lista con todas las escalas tarifas asignadas
    * para la tabla de orden-venta.html
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
    public Object listarPorOrdenVenta(int idOrdenVenta) throws IOException {
        List<OrdenVentaEscala> ordenesEscala = elementoDAO.findByOrdenVenta(ordenVentaDAO.findById(idOrdenVenta));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenVenta");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(ordenesEscala);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista por orden de venta y precios desde
    public Object listarPorOrdenVentaYPreciosDesde(int idOrdenVenta, String preciosDesde) 
            throws IOException, ParseException {
        String[] fechas = preciosDesde.split("-");
        String fecha = fechas[2] + "-" + fechas[1] + "-" + fechas[0];
        java.util.Date javaFecha = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        List<OrdenVentaEscala> ordenesEscala = elementoDAO.findByOrdenVentaAndPreciosDesde(
                ordenVentaDAO.findById(idOrdenVenta).get(), new Date(javaFecha.getTime()));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenVenta");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(ordenesEscala);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene la lista de fechas por orden de venta
    public Object listarFechasPorOrdenVenta(int idOrdenVenta) throws IOException {
        List<OrdenVentaEscala> ordenesEscala = elementoDAO.findByOrdenVenta(ordenVentaDAO.findById(idOrdenVenta));
        List<OrdenVentaEscala> ordenesEscalaFechaDistinta = new ArrayList<>();
        List<Date> fechas = new ArrayList<>();
        for(OrdenVentaEscala elemento : ordenesEscala) {
            if(!fechas.contains(elemento.getPreciosDesde())) {
                fechas.add(elemento.getPreciosDesde());
                ordenesEscalaFechaDistinta.add(elemento);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenVenta");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(ordenesEscalaFechaDistinta);
        return new ObjectMapper().readValue(string, Object.class);
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
    
    //Agrega una lista de registros
    @Transactional(rollbackFor = Exception.class)
    public OrdenVentaEscala agregarLista(List<OrdenVentaEscala> elementos) {
        for (OrdenVentaEscala elemento : elementos) {
            elementoDAO.saveAndFlush(elemento);
        }
        return elementos.get(elementos.size()-1);
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
    public void eliminar(OrdenVentaEscala elemento) {
        elementoDAO.delete(elemento);
    }
    
}

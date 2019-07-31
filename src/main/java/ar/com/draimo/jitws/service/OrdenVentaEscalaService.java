package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEscalaTarifaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaEscalaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTarifaDAO;
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
    public Object listar() throws IOException {
        List<OrdenVentaEscala>  elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    /*
    * Obtiene una lista con todas las escalas tarifas
    */
    public Object listarConEscalaTarifa() throws IOException {
        //Obtiene la lista completa de escalas tarifas
        List<EscalaTarifa> escalasTarifas = escalaTarifaDAO.findAll();
        //Ordena la lista de escalas tarifas
        escalasTarifas.sort(Comparator.comparing(EscalaTarifa::getValor));
        //Crea una lista vacia de ordenes de ventas escalas
        List<OrdenVentaEscala> elementos = new ArrayList<>();
        //Define una orden venta escala
        OrdenVentaEscala ordenVentaEscala;
        //Recorre la lista de escalas tarifas
        for(EscalaTarifa escalaTarifa : escalasTarifas) {
            ordenVentaEscala = new OrdenVentaEscala();
            ordenVentaEscala.setEscalaTarifa(escalaTarifa);
            elementos.add(ordenVentaEscala);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene un listado por orden venta.
    public Object listarPorOrdenVenta(int idOrdenVenta) throws IOException {
        List<OrdenVentaEscala> elementos = elementoDAO.findByOrdenVentaTarifa_OrdenVenta(
             ordenVentaDAO.findById(idOrdenVenta).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene un listado por ordenVentaTarifa
    public Object listarPorOrdenVentaTarifa(int idOrdenVentaTarifa) throws IOException {
        List<OrdenVentaEscala> elementos = elementoDAO.findByOrdenVentaTarifaOrderByEscalaTarifa_ValorAsc(
             ordenVentaTarifaDAO.findById(idOrdenVentaTarifa).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista por orden de venta y precios desde
    public Object listarPorOrdenVentaYPreciosDesde(int idOrdenVenta, String preciosDesde) throws IOException {
        Date precios = Date.valueOf(preciosDesde) ;
        List<OrdenVentaEscala> elementos = 
            elementoDAO.findByOrdenVentaTarifa_OrdenVentaAndPreciosDesde(
                ordenVentaDAO.findById(idOrdenVenta).get(), precios);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene la lista de fechas por orden de venta
    public Object listarFechasPorOrdenVenta(int idOrdenVenta) throws IOException {
        List<OrdenVentaEscala> ordenesEscala = elementoDAO.findByOrdenVentaTarifa_OrdenVenta(
                ordenVentaDAO.findById(idOrdenVenta).get());
        List<OrdenVentaEscala> elementos = new ArrayList<>();
        List<Date> fechas = new ArrayList<>();
        for(OrdenVentaEscala elemento : ordenesEscala) {
            if(!fechas.contains(elemento.getPreciosDesde())) {
                fechas.add(elemento.getPreciosDesde());
                elementos.add(elemento);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
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
    public Object agregar(OrdenVentaEscala elemento) throws IOException {
        elementoDAO.saveAndFlush(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(OrdenVentaEscala elemento) throws IOException {
        elementoDAO.save(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
}

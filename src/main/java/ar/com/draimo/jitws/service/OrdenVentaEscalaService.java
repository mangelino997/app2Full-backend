//Paquete al que pertenece el servicio
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
        return (elemento!=null?elemento.getId()+1:1);
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
    
    //Obtiene una lista con todas las escalas tarifas
    public Object listarConEscalaTarifa() throws IOException {
        //Obtiene la lista completa de escalas tarifas ordenado por valor
        List<EscalaTarifa> escalasTarifas = escalaTarifaDAO.listarOrdenadoPorValor();
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
    public Object listarPorOrdenVentaTarifa(int idOrdenVenta, int idTipoTarifa) throws IOException {
        List<OrdenVentaEscala> elementos = 
            elementoDAO.listarPorOrdenVentaTarifaOrdenadoPorValor(idOrdenVenta, idTipoTarifa);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista por orden de venta y precios desde
    public List<OrdenVentaEscala> listarPorOrdenVentaTarifaYPreciosDesde(int 
            idOrdenVentaTarifa, String preciosDesde) {
        return elementoDAO.listarPorOrdenVentaTarifaYPreciosDesde(idOrdenVentaTarifa,
                Date.valueOf(preciosDesde));
    }
    
    //Obtiene un listado de fechas de preciosDesde por ordenVentaTarifa
    public List<Date> listarPreciosDesdePorOrdenVentaTarifa(int idOrdenVentaTarifa) {
        return elementoDAO.listarPreciosDesdePorOrdenVentaTarifa(idOrdenVentaTarifa);
    }
    
    //Obtiene el precio del flete
    public BigDecimal obtenerPrecioFlete(int idOrdenVenta, String valor) {
        BigDecimal v = new BigDecimal(valor);
        List<EscalaTarifa> escalaTarifas = escalaTarifaDAO.obtenerDosEscalasporIdOrdenVenta(idOrdenVenta);
        BigDecimal valorHasta = escalaTarifas.get(0).getValor().subtract
        (escalaTarifas.get(1).getValor()).setScale(2, RoundingMode.UNNECESSARY);
        OrdenVentaEscala  ordenVentaEscala = elementoDAO.obtenerPorOrdenVentaYValorProximo
        (idOrdenVenta, v, v.add(valorHasta));
            return ordenVentaEscala.getImporteFijo()!=null? ordenVentaEscala.getImporteFijo():
             (v.multiply(ordenVentaEscala.getPrecioUnitario()));
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public int agregar(OrdenVentaEscala elemento) {
        elemento.setOrdenVentaTarifa(ordenVentaTarifaDAO.obtenerPorOrdenVentaYTipoTarifa(
                elemento.getOrdenVentaTarifa().getOrdenVenta().getId(), 
                elemento.getOrdenVentaTarifa().getTipoTarifa().getId()));
        return elementoDAO.saveAndFlush(elemento).getId();
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public int actualizar(OrdenVentaEscala elemento) {
        return elementoDAO.save(elemento).getId();
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
}
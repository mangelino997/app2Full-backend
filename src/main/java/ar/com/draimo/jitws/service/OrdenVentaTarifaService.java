//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEscalaTarifaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaEscalaDAO;
import ar.com.draimo.jitws.model.OrdenVentaTarifa;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IOrdenVentaTarifaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTramoDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

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
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
         List<OrdenVentaTarifa>  elementos= elementoDAO.findAll();
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
         List<OrdenVentaTarifa> elementos= elementoDAO.findByOrdenVenta(ordenVentaDAO.findById(idOrdenVenta).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene un listado por orden venta y escala
    public Object listarPorOrdenVentaYEscala(int idOrdenVenta, boolean escala) throws IOException {
         List<OrdenVentaTarifa> elementos= elementoDAO.findByOrdenVentaAndTipoTarifa_PorEscala(
                 ordenVentaDAO.findById(idOrdenVenta).get(), escala);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
//    //Obtiene una lista por orden de venta y precios desde
//    public Object listarPorOrdenVentaYPreciosDesde(int idOrdenVenta, Date preciosDesde) throws IOException {
//         List<OrdenVentaTarifa>  elementos = elementoDAO.findByOrdenVentaAndPreciosDesde(ordenVentaDAO.findById(idOrdenVenta).get(), preciosDesde);ObjectMapper mapper = new ObjectMapper();
//        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
//                .serializeAllExcept("cliente");
//        FilterProvider filters = new SimpleFilterProvider()
//                .addFilter("clienteordenventafiltro", theFilter);
//        String string = mapper.writer(filters).writeValueAsString(elementos);
//        return mapper.readValue(string, Object.class);
//        
//    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public int agregar(OrdenVentaTarifa elemento) throws IOException {
        return elementoDAO.saveAndFlush(elemento).getId();
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(OrdenVentaTarifa elemento) throws IOException {
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
        OrdenVentaTarifa elemento = elementoDAO.findById(id).get();
        if(elemento.getTipoTarifa().getPorEscala()){
            ordenVentaEscalaDAO.deleteByOrdenVentaTarifa(elemento);
        } else {
            ordenVentaTramoDAO.deleteByOrdenVentaTarifa(elemento);
        }
        elementoDAO.deleteById(id);
    }
    
}
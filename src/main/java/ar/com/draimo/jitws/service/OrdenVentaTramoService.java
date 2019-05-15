package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTramoDAO;
import ar.com.draimo.jitws.model.OrdenVentaTramo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public Object listarPorOrdenVenta(int idOrdenVenta) throws IOException {
        List<OrdenVentaTramo> ordenesTramos = elementoDAO.findByOrdenVenta(ordenVentaDAO.findById(idOrdenVenta).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenVenta");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(ordenesTramos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista por orden de venta y precios desde
    public Object listarPorOrdenVentaYPreciosDesde(int idOrdenVenta, Date preciosDesde) 
            throws IOException, ParseException {
        List<OrdenVentaTramo> ordenesTramos = elementoDAO.findByOrdenVentaAndPreciosDesde(
                ordenVentaDAO.findById(idOrdenVenta).get(), preciosDesde);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenVenta");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(ordenesTramos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene la lista de fechas por orden de venta
    public Object listarFechasPorOrdenVenta(int idOrdenVenta) throws IOException {
        List<OrdenVentaTramo> ordenesTramos = elementoDAO.findByOrdenVenta(ordenVentaDAO.findById(idOrdenVenta).get());
        List<OrdenVentaTramo> ordenesTramosFechaDistinta = new ArrayList<>();
        List<Date> fechas = new ArrayList<>();
        for(OrdenVentaTramo elemento : ordenesTramos) {
            if(!fechas.contains(elemento.getPreciosDesde())) {
                fechas.add(elemento.getPreciosDesde());
                ordenesTramosFechaDistinta.add(elemento);
            }
        }
        Collections.sort(ordenesTramosFechaDistinta, sortDate);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ordenVenta");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(ordenesTramosFechaDistinta);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Agrega una lista de registros
    @Transactional(rollbackFor = Exception.class)
    public OrdenVentaTramo agregarLista(List<OrdenVentaTramo> elementos) {
        for (OrdenVentaTramo elemento : elementos) {
            elementoDAO.save(elemento);
        }
        return elementos.get(elementos.size()-1);
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
    
    //Comparator para ordenar lista
    private Comparator<OrdenVentaTramo> sortDate = new Comparator<OrdenVentaTramo>() {
	public int compare(OrdenVentaTramo s1, OrdenVentaTramo s2) {
	   Date rollno1 = s1.getPreciosDesde();
	   Date rollno2 = s2.getPreciosDesde();
	   return rollno2.compareTo(rollno1);
   }};
    
}

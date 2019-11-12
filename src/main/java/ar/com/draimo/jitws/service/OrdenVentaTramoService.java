//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTarifaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTramoDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.OrdenVentaTramo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    
    //Define la referencia al dao de orden venta tarifa
    @Autowired
    IOrdenVentaTarifaDAO ordenVentaTarifaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVentaTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
         List<OrdenVentaTramo> elementos = elementoDAO.findAll();
        return aplicarFiltros(elementos);
    }
    
    //Obtiene una lista por orden de venta
    public Object listarPorOrdenVenta(int idOrdenVenta) throws IOException {
         List<OrdenVentaTramo> elementos = elementoDAO.findByOrdenVentaTarifa_OrdenVenta(
                 ordenVentaDAO.findById(idOrdenVenta).get());
        return aplicarFiltros(elementos);
    }
    
    //Obtiene una lista por orden de venta y precios desde
    public Object listarPorOrdenVentaYPreciosDesde(int idOrdenVenta, String preciosDesde) throws IOException {
        Date precios = Date.valueOf(preciosDesde) ;
        List<OrdenVentaTramo> elementos = 
            elementoDAO.findByOrdenVentaTarifa_OrdenVentaAndPreciosDesde(
                ordenVentaDAO.findById(idOrdenVenta).get(), precios);
        return aplicarFiltros(elementos);
    }
    
    //Obtiene una lista por orden de venta tarifa
    public Object listarPorOrdenVentaTarifa(int idOrdenVentaTarifa) throws IOException {
        List<OrdenVentaTramo> elementos = 
            elementoDAO.findByOrdenVentaTarifa(
                ordenVentaTarifaDAO.findById(idOrdenVentaTarifa).get());
        return aplicarFiltros(elementos);
    }
    
    //Obtiene la lista de fechas por orden de venta
    public Object listarFechasPorOrdenVenta(int idOrdenVenta) throws IOException {
        List<OrdenVentaTramo> ordenesTramo = elementoDAO.listarPreciosDesdePorOrdenVenta(idOrdenVenta);
        return aplicarFiltros(ordenesTramo);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public int agregar(OrdenVentaTramo elemento) {
        controlarLongitud(elemento);
        elemento = elementoDAO.saveAndFlush(elemento);
        return elemento.getId();
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public int actualizar(OrdenVentaTramo elemento) {
        controlarLongitud(elemento);
        elemento = elementoDAO.save(elemento);
        return elemento.getId();
    }
    
    //COntrola longitudes de atributos short
    private void controlarLongitud(OrdenVentaTramo elemento) {
        String kmPactado = String.valueOf(elemento.getKmPactado());
        //Obtiene longitud de kmPactado, si es mayor a 4retorna error
        if (kmPactado.length()>4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " KM PACTADO");
        }
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //retorna un object despues de aplicar los filtros
    private Object aplicarFiltros(List<OrdenVentaTramo> elementos) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
}
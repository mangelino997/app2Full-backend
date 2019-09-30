//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrdenRecoleccionDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.OrdenRecoleccion;
import ar.com.draimo.jitws.model.TipoComprobante;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio OrdenRecoleccion
 * @author blas
 */

@Service
public class OrdenRecoleccionService {
    
    //Define la referencia al dao
    @Autowired
    IOrdenRecoleccionDAO elementoDAO;
    
    //Define la referencia al dao tipo comprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenRecoleccion elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene un registro por id
    public Object obtenerPorId(int idOrdenRecoleccion) throws IOException {
        OrdenRecoleccion orden= (elementoDAO.findById(idOrdenRecoleccion).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(orden);
        return mapper.readValue(string, Object.class);
    } 
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<OrdenRecoleccion> ordenes = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(ordenes);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene la lista por recolecciones disponibles
    public Object listarRecoleccionesDisponibles() throws IOException {
        List<OrdenRecoleccion> ordenes = elementoDAO.listarRecoleccionesDisponibles();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(ordenes);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista por alias
    public Object listarPorAlias(String alias) throws IOException {
        List<OrdenRecoleccion> ordenes = alias.equals("***")?
            elementoDAO.findAll(): elementoDAO.findByAliasContaining(alias);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(ordenes);
        return mapper.readValue(string, Object.class);
    }
    
    
    //Obtiene una lista por alias
    public Object listarPorFiltros(String fechaEmision, int idCliente) throws IOException {
        List<OrdenRecoleccion> ordenes = fechaEmision.isEmpty() && idCliente==0?
            elementoDAO.listarPorFiltros(fechaEmision, idCliente):elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(ordenes);
        return mapper.readValue(string, Object.class);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenRecoleccion agregar(OrdenRecoleccion elemento) throws Exception {
        elemento = formatearStrings(elemento);
        elemento.setFechaEmision(LocalDateTime.now());
        elemento.setEstaEnReparto(false);
        TipoComprobante tipoComprobante = tipoComprobanteDAO.findById(13).get();
        elemento.setTipoComprobante(tipoComprobante);
        //Obtiene longitud de bultos, si supera 6 retorna error
        String bultos = String.valueOf(elemento.getBultos());
        if (bultos.length()>6) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " BULTOS");
        }
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(OrdenRecoleccion or) {
        or.setAlias(or.getId() + " | " + or.getCliente().getId() + " - " + 
                or.getCliente().getRazonSocial() + " | " + or.getFecha());
        elementoDAO.save(or);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrdenRecoleccion elemento) throws Exception {
        elemento = formatearStrings(elemento);
        elemento.setAlias(elemento.getId() + " | " + elemento.getCliente().getId() 
                + " - " + elemento.getCliente().getRazonSocial() + " | " + elemento.getFecha());
        //Obtiene longitud de bultos, si supera 6 retorna error
        String bultos = String.valueOf(elemento.getBultos());
        if (bultos.length()>6) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " BULTOS");
        }
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private OrdenRecoleccion formatearStrings(OrdenRecoleccion elemento) {
        elemento.setDomicilio(elemento.getDomicilio().trim());
        elemento.setSolicitadoPor(elemento.getSolicitadoPor().trim());
        elemento.setTelefonoContacto(elemento.getTelefonoContacto().trim());
        elemento.setDescripcionCarga(elemento.getDescripcionCarga().trim());
        if(elemento.getObservaciones()!= null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }
    
}
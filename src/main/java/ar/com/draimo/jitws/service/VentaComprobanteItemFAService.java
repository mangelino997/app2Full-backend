package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.VentaComprobanteItemFA;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemFADAO;
import ar.com.draimo.jitws.model.VentaComprobante;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;

/**
 * Servicio VentaComprobanteItem
 * @author blas
 */

@Service
public class VentaComprobanteItemFAService {
    
    //Define la referencia al dao
    @Autowired
    IVentaComprobanteItemFADAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaComprobanteItemFA elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<VentaComprobanteItemFA> ventasComprobantes = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ventaComprobante", "ordenVenta","cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroVentaComprobanteItemFA", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroVentaComprobanteItemNC", theFilter)
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("clienteordenventafiltro", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string = mapper.writer(filters).writeValueAsString(ventasComprobantes);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobanteItemFA agregar(VentaComprobanteItemFA elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaComprobanteItemFA elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(VentaComprobanteItemFA elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private VentaComprobanteItemFA formatearStrings(VentaComprobanteItemFA elemento) {
        if(elemento.getDescripcionCarga() != null) {
            elemento.setDescripcionCarga(elemento.getDescripcionCarga().trim());
        }
        return elemento;
    }
    
}

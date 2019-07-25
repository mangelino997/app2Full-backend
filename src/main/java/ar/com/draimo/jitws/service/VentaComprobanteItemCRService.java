package ar.com.draimo.jitws.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemCRDAO;
import ar.com.draimo.jitws.model.VentaComprobante;
import ar.com.draimo.jitws.model.VentaComprobanteItemCR;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;

/**
 * Servicio VentaComprobanteItem cr
 * @author blas
 */

@Service
public class VentaComprobanteItemCRService {
    
    //Define la referencia al dao
    @Autowired
    IVentaComprobanteItemCRDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaComprobanteItemCR elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<VentaComprobanteItemCR> ventasComprobantes = elementoDAO.findAll();
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
    public VentaComprobanteItemCR agregar(VentaComprobanteItemCR elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaComprobanteItemCR elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}

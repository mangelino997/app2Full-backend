//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.VentaComprobanteItemFA;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IVentaComprobanteItemFADAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio VentaComprobanteItem
 *
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
        return elemento != null ? elemento.getId() + 1 : 1;
    }
    
    //Obtiene el por id
    public Object obtenerPorId(int id) throws IOException {
        VentaComprobanteItemFA elemento = elementoDAO.obtenerPorId(id);
        return retornarObjeto(null, elemento);
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<VentaComprobanteItemFA> ventaItems = elementoDAO.findAll();
        return retornarObjeto(ventaItems, null);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobanteItemFA agregar(VentaComprobanteItemFA elemento) {
        controlarLongitud(elemento);
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaComprobanteItemFA elemento) {
        controlarLongitud(elemento);
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Controla longitud de atributos short
    private void controlarLongitud(VentaComprobanteItemFA elemento) {
        //Obtiene longitud de bultos, si supera 6 retorna error
        String bultos = String.valueOf(elemento.getBultos());
        if (bultos.length() > 6) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " BULTOS");
        }
    }

    //Formatea los strings
    private VentaComprobanteItemFA formatearStrings(VentaComprobanteItemFA elemento) {
        if (elemento.getDescripcionCarga() != null) {
            elemento.setDescripcionCarga(elemento.getDescripcionCarga().trim());
        }
        return elemento;
    }

    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<VentaComprobanteItemFA> elementos, VentaComprobanteItemFA elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ventaComprobante", "ordenVenta", "ordenesVentas", "cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroVentaComprobanteItemFA", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroVentaComprobanteItemNC", theFilter)
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("clienteordenventafiltro", theFilter)
                .addFilter("cliente", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento!=null ? elemento : elementos);
        return mapper.readValue(string, Object.class);
    }
    
}
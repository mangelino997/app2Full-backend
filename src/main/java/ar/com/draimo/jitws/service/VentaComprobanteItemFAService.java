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

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<VentaComprobanteItemFA> ventasComprobantes = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("ventaComprobante", "ordenVenta", "cliente", "ordenesVentas");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroVentaComprobanteItemFA", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroVentaComprobanteItemNC", theFilter)
                .addFilter("filtroOrdenVentaEscala", theFilter)
                .addFilter("clienteordenventafiltro", theFilter)
                .addFilter("cliente", theFilter)
                .addFilter("filtroOrdenVentaTramo", theFilter);
        String string = mapper.writer(filters).writeValueAsString(ventasComprobantes);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaComprobanteItemFA agregar(VentaComprobanteItemFA elemento) throws Exception {
        //Obtiene longitud de bultos, si supera 6 retorna error
        String bultos = String.valueOf(elemento.getBultos());
        if (bultos.length() > 6) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " BULTOS");
        }
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaComprobanteItemFA elemento) throws Exception {
        //Obtiene longitud de bultos, si supera 6 retorna error
        String bultos = String.valueOf(elemento.getBultos());
        if (bultos.length() > 6) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " BULTOS");
        }
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private VentaComprobanteItemFA formatearStrings(VentaComprobanteItemFA elemento) {
        if (elemento.getDescripcionCarga() != null) {
            elemento.setDescripcionCarga(elemento.getDescripcionCarga().trim());
        }
        return elemento;
    }

}
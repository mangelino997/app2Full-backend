//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteRemitoDAO;
import ar.com.draimo.jitws.dto.ViajeRemitoDTO;
import ar.com.draimo.jitws.model.ViajeTramoClienteRemito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de ViajeTramoClienteRemito
 *
 * @author blas
 */
@Service
public class ViajeTramoClienteRemitoService {

    //Define el dao
    @Autowired
    IViajeTramoClienteRemitoDAO elementoDAO;

    //Define el dao deviajeTramo cliente
    @Autowired
    IViajeTramoClienteDAO viajeTramoClienteDAO;

    //Define el dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTramoClienteRemito elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeTramoClienteRemito> v = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "viaje");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(v);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por ViajeTramoCliente
    public Object listarPorViajeTramoCliente(int idViajeTramoCliente) throws IOException {
        List<ViajeTramoClienteRemito> v = elementoDAO.findByViajeTramoCliente(
                viajeTramoClienteDAO.findById(idViajeTramoCliente).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "viaje");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(v);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por Viaje/viaje tramo/facturado
    public Object listarPorViajeYEstado(ViajeRemitoDTO viajeTramoClienteDto) throws IOException {
        List<ViajeTramoClienteRemito> v = elementoDAO.listarPorViajeYEstaFacturado(
                viajeTramoClienteDto.getIdRemito(),viajeTramoClienteDto.getIdViaje(),
                viajeTramoClienteDto.isEstaFacturado());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "viaje");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(v);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(ViajeTramoClienteRemito elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTramoClienteRemito elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
}
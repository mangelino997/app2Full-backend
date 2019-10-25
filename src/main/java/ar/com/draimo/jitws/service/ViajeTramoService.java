//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteDAO;
import ar.com.draimo.jitws.dao.IViajeTramoDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Viaje;
import ar.com.draimo.jitws.model.ViajeTramoCliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio ViajeTramo
 *
 * @author blas
 */
@Service
public class ViajeTramoService {

    //Define la referencia al dao
    @Autowired
    IViajeTramoDAO elementoDAO;

    //Define la referencia al dao de viaje tramo cliente
    @Autowired
    IViajeTramoClienteDAO viajeTramoClienteDAO;

    //Define la referencia al dao de viaje
    @Autowired
    IViajeDAO viajeDAO;

    //Define la referencia al service de Viaje
    @Autowired
    ViajeService viajeService;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene una lista de tramos por viaje 
    public Object listarTramos(int idViaje) throws IOException {
        List<ViajeTramo> elementos = elementoDAO.findByViaje(viajeDAO.obtenerViaje(idViaje));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "datos", "viajeTramos", "viajeCombustibles",
                        "viajeEfectivos", "viajeInsumos", "viajeGastos", "viajePeajes", "viaje");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeTramo> elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "datos", "viajeTramos", "viajeCombustibles",
                        "viajeEfectivos", "viajeInsumos", "viajeGastos", "viajePeajes", "viaje");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(ViajeTramo elemento) throws IOException, Exception {
        elemento = formatearStrings(elemento);
        Viaje viaje = new Viaje();
        if (elemento.getViaje().getId() == 0) {
            viaje = viajeService.formatearStrings(elemento.getViaje());
            viaje = viajeDAO.saveAndFlush(viaje);
            viaje = viajeService.establecerAlias(viaje);
            elemento.setViaje(viaje);
        }
        controlarLongitud(elemento);
        elemento = elementoDAO.saveAndFlush(elemento);
        List<ViajeTramoCliente> vtCliente = new ArrayList<>();
        for (ViajeTramoCliente viajeTramoCliente : elemento.getViajeTramoClientes()) {
            viajeTramoCliente.setViajeTramo(elemento);
            viajeTramoCliente = viajeTramoCliente.getId() == 0
                    ? viajeTramoClienteDAO.saveAndFlush(viajeTramoCliente) : viajeTramoCliente;
            vtCliente.add(viajeTramoCliente);
        }
        elemento.setViajeTramoClientes(vtCliente);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "datos", "viajeTramos", "viajeCombustibles",
                        "viajeEfectivos", "viajeInsumos", "viajeGastos", "viajePeajes");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTramo elemento) throws IOException, Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        elementoDAO.save(elemento);
        for (ViajeTramoCliente viajeTramoCliente : elemento.getViajeTramoClientes()) {
            viajeTramoCliente.setViajeTramo(elemento);
            viajeTramoCliente = viajeTramoCliente.getId() == 0
                    ? viajeTramoClienteDAO.saveAndFlush(viajeTramoCliente) : viajeTramoCliente;
        }
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

    //Formatea los strings
    public ViajeTramo formatearStrings(ViajeTramo elemento) {
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }

    //Control para short excedidos en su limite
    private void controlarLongitud(ViajeTramo elemento) {
        //Obtiene longitud de numeroOrden, si supera 3 retorna error
        String numOrden = String.valueOf(elemento.getNumeroOrden());
        if (numOrden.length() > 3) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " NUMERO ORDEN");
        }
        //Obtiene longitud de km, si supera 4 retorna error
        String km = String.valueOf(elemento.getKm());
        if (km.length() > 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " KM");
        }
    }

}
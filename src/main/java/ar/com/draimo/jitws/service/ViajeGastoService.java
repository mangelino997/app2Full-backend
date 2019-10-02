//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeGasto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajeGastoDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio ViajeGasto
 *
 * @author blas
 */
@Service
public class ViajeGastoService {

    //Define la referencia al dao
    @Autowired
    IViajeGastoDAO elementoDAO;

    //Define la referencia al dao viaje
    @Autowired
    IViajeDAO viajeDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeGasto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeGasto> elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista de gastos por viaje 
    public Object listarGastos(int idViaje) throws IOException {
        List<ViajeGasto> elementos = elementoDAO.findByViaje(viajeDAO.findById(idViaje).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(ViajeGasto elemento) throws IOException, Exception {
        //Obtiene longitud de cantidad, si supera 3 retorna error
        String cant = String.valueOf(elemento.getCantidad());
        if (cant.length() > 3) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " CANTIDAD");
        }
        elemento = elementoDAO.saveAndFlush(formatearStrings(elemento));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "datos", "viajeTramos", "viajeCombustibles",
                        "viajeEfectivos", "viajeInsumos", "viajeGastos", "viajePeajes");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeGasto elemento) throws IOException, Exception {
        //Obtiene longitud de cantidad, si supera 3 retorna error
        String cant = String.valueOf(elemento.getCantidad());
        if (cant.length() > 3) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " CANTIDAD");
        }
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

    //Anula un registro
    @Transactional(rollbackFor = Exception.class)
    public void anular(ViajeGasto elemento) throws IOException {
        elemento.setEstaAnulado(true);
        elementoDAO.save(elemento);
    }

    //Normaliza un registro
    @Transactional(rollbackFor = Exception.class)
    public void normalizar(ViajeGasto elemento) throws IOException {
        elemento.setEstaAnulado(false);
        elemento.setObservacionesAnulado(null);
        elementoDAO.save(elemento);
    }

    //Formatea los strings
    private ViajeGasto formatearStrings(ViajeGasto elemento) {
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if (elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

}
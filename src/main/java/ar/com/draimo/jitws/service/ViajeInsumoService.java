//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeInsumo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajeInsumoDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio ViajeInsumo
 *
 * @author blas
 */
@Service
public class ViajeInsumoService {

    //Define la referencia al dao
    @Autowired
    IViajeInsumoDAO elementoDAO;

    //Define la referencia al dao de viaje 
    @Autowired
    IViajeDAO viajeDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeInsumo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeInsumo> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista de insumos por viaje 
    public Object listarInsumos(int idViaje) throws IOException {
        List<ViajeInsumo> elementos = elementoDAO.findByViaje(viajeDAO.obtenerViaje(idViaje));
        return retornarObjeto(elementos, null);
    }

    //Anula un registro
    @Transactional(rollbackFor = Exception.class)
    public void anularInsumo(ViajeInsumo elemento) throws IOException {
        elemento.setEstaAnulado(true);
        elementoDAO.save(elemento);
    }

    //Normaliza un registro
    @Transactional(rollbackFor = Exception.class)
    public void normalizarInsumo(ViajeInsumo elemento) throws IOException {
        elemento.setEstaAnulado(false);
        elemento.setObservacionesAnulado(null);
        elementoDAO.save(elemento);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(ViajeInsumo elemento) throws IOException, Exception {
        controlarLongitud(elemento);
        elemento = elementoDAO.saveAndFlush(formatearStrings(elemento));
        return retornarObjeto(null, elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeInsumo elemento) throws IOException, Exception {
        controlarLongitud(elemento);
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

    //Formatea los strings
    private ViajeInsumo formatearStrings(ViajeInsumo elemento) {
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if (elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }
    
    //Controla la lonitud de los aatributos de tipo short
    private void controlarLongitud(ViajeInsumo elemento) {
        //Obtiene longitud de cantidad, si supera 3 retorna error
        String cant = String.valueOf(elemento.getCantidad());
        if (cant.length() > 3) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " CANTIDAD");
        }
    } 

    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<ViajeInsumo> elementos, ViajeInsumo elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = (elemento != null ? SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "datos", "viajeTramos",
                        "viajeCombustibles","viajeEfectivos", "viajeInsumos", "viajeGastos",
                        "viajePeajes") : SimpleBeanPropertyFilter.serializeAllExcept(
                                "cliente", "viajeTramo", "datos"));
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento!=null ? elemento : elementos);
        return mapper.readValue(string, Object.class);
    }
    
}
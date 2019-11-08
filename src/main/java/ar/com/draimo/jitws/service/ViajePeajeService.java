//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajePeaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajePeajeDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;

/**
 * Servicio ViajePeaje
 *
 * @author blas
 */
@Service
public class ViajePeajeService {

    //Define la referencia al dao
    @Autowired
    IViajePeajeDAO elementoDAO;

    //Define la referencia al dao de viaje
    @Autowired
    IViajeDAO viajeDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajePeaje elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajePeaje> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista de peajes por viaje
    public Object listarPeajes(int idViaje) throws IOException {
        List<ViajePeaje> elementos = elementoDAO.findByViaje(viajeDAO.obtenerViaje(idViaje));
        return retornarObjeto(elementos, null);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(ViajePeaje elemento) throws IOException {
        elemento = elementoDAO.saveAndFlush(formatearStrings(elemento));
        return retornarObjeto(null, elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajePeaje elemento) throws IOException {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

    //Formatea los strings
    private ViajePeaje formatearStrings(ViajePeaje elemento) {
        elemento.setLetra(elemento.getLetra().trim());
        return elemento;
    }

    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<ViajePeaje> elementos, ViajePeaje elemento) throws IOException {
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

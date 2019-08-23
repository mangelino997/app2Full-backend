package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeCombustible;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeCombustibleDAO;
import ar.com.draimo.jitws.dao.IViajeDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;

/**
 * Servicio ViajeCombustible
 * @author blas
 */

@Service
public class ViajeCombustibleService {

    //Define la referencia al dao
    @Autowired
    IViajeCombustibleDAO elementoDAO;
    
    //Define la referencia al dao viaje
    @Autowired
    IViajeDAO viajeDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeCombustible elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeCombustible>  elementos= elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente","viajeTramo","datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista de tramos por viaje
    public Object listarCombustibles(int idViaje) throws IOException {
        List<ViajeCombustible>  elementos= elementoDAO.findByViaje(viajeDAO.obtenerPorId(idViaje));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente","viajeTramo","datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Anula un registro
    @Transactional(rollbackFor = Exception.class)
    public void anularCombustible(ViajeCombustible elemento) throws IOException {
        elemento.setEstaAnulado(true);
        elementoDAO.save(elemento);
    }
    
    //Normaliza un registro
    @Transactional(rollbackFor = Exception.class)
    public void normalizarCombustible(ViajeCombustible elemento) throws IOException {
        elemento.setEstaAnulado(false);
        elemento.setObservacionesAnulado(null);
        elementoDAO.save(elemento);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(ViajeCombustible elemento) throws IOException {
        elemento = formatearStrings(elemento);
        elemento = elementoDAO.saveAndFlush(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente","viajeTramo","datos");
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
    public void actualizar(ViajeCombustible elemento) throws IOException {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajeCombustible formatearStrings(ViajeCombustible elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

}

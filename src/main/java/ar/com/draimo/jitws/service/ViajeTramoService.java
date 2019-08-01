package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeTramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajeTramoDAO;
import ar.com.draimo.jitws.model.Viaje;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;

/**
 * Servicio ViajeTramo
 * @author blas
 */

@Service
public class ViajeTramoService {

    //Define la referencia al dao
    @Autowired
    IViajeTramoDAO elementoDAO;
    
    //Define la referencia al dao de viaje
    @Autowired
    IViajeDAO viajeDAO; 
    
    //Define la referencia al service de Viaje
    @Autowired
    ViajeService viajeService; 
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene una lista de tramos por viaje propio
    public Object listarTramos(int idViaje) throws IOException {
        List<ViajeTramo>  elementos= elementoDAO.findByViaje(viajeDAO.obtenerPorId(idViaje));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeTramo>  elementos= elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(ViajeTramo elemento) throws IOException {
        elemento = formatearStrings(elemento);
        Viaje viaje = new Viaje();
        if(elemento.getViaje().getId()==0) {
           viaje = viajeService.formatearStrings(elemento.getViaje());
           viaje = viajeDAO.saveAndFlush(viaje);
           viaje = viajeService.establecerAlias(viaje);
           elemento.setViaje(viaje);
        }
        elementoDAO.saveAndFlush(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elemento);
        return new ObjectMapper().readValue(string, Object.class);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(ViajeTramo elemento) throws IOException {
        elemento = formatearStrings(elemento);
        ObjectMapper mapper = new ObjectMapper();
        elementoDAO.save(elemento);
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elemento);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajeTramo formatearStrings(ViajeTramo elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }

}

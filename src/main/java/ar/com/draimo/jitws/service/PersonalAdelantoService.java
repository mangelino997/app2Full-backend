package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPersonalAdelantoDAO;
import ar.com.draimo.jitws.model.PersonalAdelanto;
import ar.com.draimo.jitws.model.Reparto;
import ar.com.draimo.jitws.model.Viaje;
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
 * Define PersonalAdelantoService
 * @author blas
 */

@Service
public class PersonalAdelantoService {

    @Autowired
    IPersonalAdelantoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PersonalAdelanto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<PersonalAdelanto> personalAdelantos = elementoDAO.findAll();
        String id;
        Viaje viaje;
        Reparto reparto;
        /*
        * Recorre la lista de personal adelantos para obtener el idViaje o idReparto de cada item
        * Esto se realiza para evitar error de inner join de mas de 61 tablas
        */
        for(PersonalAdelanto personalAdelanto : personalAdelantos) {
            id = elementoDAO.obtenerIdViaje(personalAdelanto.getId());
            if(id != null) {
                viaje = new Viaje();
                viaje.setId(Integer.parseInt(id));
                personalAdelanto.setViaje(viaje);
            } else {
                id = elementoDAO.obtenerIdReparto(personalAdelanto.getId());
                reparto = new Reparto();
                reparto.setId(Integer.parseInt(id));
                personalAdelanto.setReparto(reparto);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajefiltro", theFilter)
                .addFilter("personaladelantofiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(personalAdelantos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PersonalAdelanto agregar(PersonalAdelanto elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PersonalAdelanto elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

}
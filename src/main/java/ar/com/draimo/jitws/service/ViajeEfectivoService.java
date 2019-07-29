package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeEfectivo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeDAO;
import ar.com.draimo.jitws.dao.IViajeEfectivoDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;

/**
 * Servicio ViajeEfectivo
 * @author blas
 */

@Service
public class ViajeEfectivoService {

    //Define la referencia al dao
    @Autowired
    IViajeEfectivoDAO elementoDAO;
    
    //Define la referencia al dao viaje
    @Autowired
    IViajeDAO viajeDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeEfectivo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeEfectivo>  elementos= elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string =  mapper.writer(filters).writeValueAsString(elementos);
        return new ObjectMapper().readValue(string, Object.class);
    }
    
    //Obtiene una lista de efectivos por viaje propio
    public Object listarEfectivos(int idViaje) throws IOException {
        List<ViajeEfectivo>  elementos= elementoDAO.findByViaje(viajeDAO.obtenerPorId(idViaje));
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
    public ViajeEfectivo agregar(ViajeEfectivo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeEfectivo actualizar(ViajeEfectivo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Formatea los strings
    private ViajeEfectivo formatearStrings(ViajeEfectivo elemento) {
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

}

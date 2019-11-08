//paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.ViajeTramoCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeTramoClienteDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteRemitoDAO;
import ar.com.draimo.jitws.dao.IViajeTramoDAO;
import ar.com.draimo.jitws.model.ViajeTramo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;

/**
 * Servicio ViajeTramoCliente
 *
 * @author blas
 */
@Service
public class ViajeTramoClienteService {

    //Define la referencia al dao
    @Autowired
    IViajeTramoClienteDAO elementoDAO;

    //Define la referencia al dao de viaje tramo
    @Autowired
    IViajeTramoDAO viajeTramoDAO;
    
    //Define la referencia al dao viaje tramo cliente remito
    @Autowired
    IViajeTramoClienteRemitoDAO viajeTramoClienteRemitoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTramoCliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene el registro por viajeTramo
    public Object listarPorViajeTramo(int idViajeTramo) throws IOException {
        ViajeTramo viajeTramo = viajeTramoDAO.findById(idViajeTramo).get();
        List<ViajeTramoCliente> elementos = elementoDAO.findByViajeTramo(viajeTramo);
        return retornarObjeto(elementos, null);
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeTramoCliente> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, null);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(ViajeTramoCliente elemento) throws IOException {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(ViajeTramoCliente elemento) throws IOException {
        ViajeTramoCliente v= elementoDAO.save(elemento);
        return retornarObjeto(null, elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        //Elimina los viaje tramo cliente remito asociados al viaje tramo cliente
        viajeTramoClienteRemitoDAO.deleteByViajeTramoCliente(elementoDAO.findById(id).get());
        //Elimina el registro
        elementoDAO.deleteById(id);
    }
    
    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<ViajeTramoCliente> elementos, ViajeTramoCliente elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "viaje");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos!=null?elementos:elemento);
        return mapper.readValue(string, Object.class);
    }

}
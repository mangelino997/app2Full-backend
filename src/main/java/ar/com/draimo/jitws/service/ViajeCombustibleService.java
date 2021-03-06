//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRepartoDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
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
 *
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

    //Define la referencia al dao reparto
    @Autowired
    IRepartoDAO repartoDAO;

    //Define la referencia al dao tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeCombustible elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeCombustible> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista de tramos por viaje
    public Object listarCombustibles(int idViaje) throws IOException {
        List<ViajeCombustible> elementos = elementoDAO.findByViaje(viajeDAO.obtenerViaje(idViaje));
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista de combustibles por reparto
    public Object listarCombustiblesReparto(int idReparto) throws IOException {
        List<ViajeCombustible> elementos = elementoDAO.findByReparto(repartoDAO.obtenerPorId(idReparto));
        return retornarObjeto(elementos, null);
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
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(15).get());
        elemento = elementoDAO.saveAndFlush(formatearStrings(elemento));
        return retornarObjeto(null, elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeCombustible elemento) throws IOException {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

    //Formatea los strings
    private ViajeCombustible formatearStrings(ViajeCombustible elemento) {
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if (elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<ViajeCombustible> elementos, ViajeCombustible elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "datos", "hijos", "viaje", "ventaComprobante");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("clientefiltro", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento!=null ? elemento : elementos);
        return mapper.readValue(string, Object.class);
    }
    
}

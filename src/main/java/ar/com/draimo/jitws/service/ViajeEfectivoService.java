//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IRepartoDAO;
import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
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
import java.sql.Date;

/**
 * Servicio ViajeEfectivo
 *
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

    //Define la referencia al dao reparto
    @Autowired
    IRepartoDAO repartoDAO;

    //Define la referencia al dao tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeEfectivo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeEfectivo> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista de efectivos por viaje propio
    public Object listarEfectivos(int idViaje) throws IOException {
        List<ViajeEfectivo> elementos = elementoDAO.findByViaje(viajeDAO.obtenerViaje(idViaje));
        return retornarObjeto(elementos, null);
    }

    //Obtiene una lista de efectivos por reparto
    public Object listarEfectivosReparto(int idReparto) throws IOException {
        List<ViajeEfectivo> elementos = elementoDAO.findByReparto(repartoDAO.obtenerPorId(idReparto));
        return retornarObjeto(elementos, null);
    }

    //Anula un registro
    @Transactional(rollbackFor = Exception.class)
    public void anularEfectivo(ViajeEfectivo elemento) throws IOException {
        elemento.setEstaAnulado(true);
        elementoDAO.save(elemento);
    }

    //Normaliza un registro
    @Transactional(rollbackFor = Exception.class)
    public void normalizarEfectivo(ViajeEfectivo elemento) throws IOException {
        elemento.setEstaAnulado(false);
        elemento.setObservacionesAnulado(null);
        elementoDAO.save(elemento);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(ViajeEfectivo elemento) throws IOException {
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(16).get());
        elemento.setFecha(new Date(new java.util.Date().getTime()));
        elemento = elementoDAO.saveAndFlush(formatearStrings(elemento));
        return retornarObjeto(null, elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeEfectivo elemento) throws IOException {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

    //Formatea los strings
    private ViajeEfectivo formatearStrings(ViajeEfectivo elemento) {
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if (elemento.getObservacionesAnulado() != null) {
            elemento.setObservacionesAnulado(elemento.getObservacionesAnulado().trim());
        }
        return elemento;
    }

    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<ViajeEfectivo> elementos, ViajeEfectivo elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "datos", "viajeTramos", 
                        "viajeCombustibles", "viajeEfectivos", "viajeInsumos", 
                        "viajeGastos", "viajePeajes", "hijos", "viaje", "ventaComprobante");
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
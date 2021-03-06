//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteRemitoDAO;
import ar.com.draimo.jitws.dto.ViajeRemitoDTO;
import ar.com.draimo.jitws.model.ViajeTramoClienteRemito;
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
 * Servicio de ViajeTramoClienteRemito
 *
 * @author blas
 */
@Service
public class ViajeTramoClienteRemitoService {

    //Define el dao
    @Autowired
    IViajeTramoClienteRemitoDAO elementoDAO;

    //Define el dao deviajeTramo cliente
    @Autowired
    IViajeTramoClienteDAO viajeTramoClienteDAO;

    //Define el dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTramoClienteRemito elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }
    
    //Obtiene por ViajeTramoCliente
    public Object obtenerPorViajeTramoCliente(int idViajeTramoCliente) throws IOException {
        List<ViajeTramoClienteRemito> v = elementoDAO.findByViajeTramoCliente(
                viajeTramoClienteDAO.findById(idViajeTramoCliente).get());
        return v.size() > 0 ? retornarObjeto(null, v.get(0)):
        new ViajeTramoClienteRemito();
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeTramoClienteRemito> v = elementoDAO.findAll();
        return retornarObjeto(v, null);
    }

    //Obtiene una lista por ViajeTramoCliente
    public Object listarPorViajeTramoCliente(int idViajeTramoCliente) throws IOException {
        List<ViajeTramoClienteRemito> v = elementoDAO.findByViajeTramoCliente(
                viajeTramoClienteDAO.findById(idViajeTramoCliente).get());
        return retornarObjeto(v, null);
    }

    //Obtiene una lista por Viaje/viaje tramo/facturado
    public Object listarPorViajeYEstado(ViajeRemitoDTO viajeTramoClienteDto) throws IOException {
        List<ViajeTramoClienteRemito> v = elementoDAO.listarPorViajeYEstaFacturado(
                viajeTramoClienteDto.getIdRemito(),viajeTramoClienteDto.getIdViaje(),
                viajeTramoClienteDto.getEstaFacturado()==1);
        return retornarObjeto(v, null);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(ViajeTramoClienteRemito elemento) {
        elementoDAO.saveAndFlush(elemento);
    }
    
    //Agrega un registro para vacio facturado
    @Transactional(rollbackFor = Exception.class)
    public void agregarVacioFacturado(ViajeTramoClienteRemito elemento) {
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(5).get());
        elemento.setPuntoVenta(0);
        elemento.setLetra("R");
        elemento.setNumero(0);
        elemento.setBultos((short)1);
        elemento.setEstaFacturado(false);
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTramoClienteRemito elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<ViajeTramoClienteRemito> elementos, 
            ViajeTramoClienteRemito elemento) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente", "viajeTramo", "viaje");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("clientefiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos!=null 
                ? elementos : elemento);
        return mapper.readValue(string, Object.class);
    }
}
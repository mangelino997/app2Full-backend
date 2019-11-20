//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeRemitoDAO;
import ar.com.draimo.jitws.model.ViajeTramoRemito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IViajeTramoRemitoDAO;
import ar.com.draimo.jitws.dto.ViajeRemitoDTO;
import ar.com.draimo.jitws.model.ChoferProveedor;
import ar.com.draimo.jitws.model.Personal;
import ar.com.draimo.jitws.model.Viaje;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;

/**
 * ViajeTramoRemito
 *
 * @author blas
 */
@Service
public class ViajeTramoRemitoService {

    //Define la referencia al DAO
    @Autowired
    IViajeTramoRemitoDAO elementoDAO;

    //Define la referencia al DAO de viajeRemito
    @Autowired
    IViajeRemitoDAO viajeRemitoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTramoRemito elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ViajeTramoRemito> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, false);
    }

    //Obtiene un listado por viaje y estado
    public Object listarPorViajeYEstado(ViajeRemitoDTO viajeRemitoDTO) throws IOException {
        List<ViajeTramoRemito> elementos = elementoDAO.listarPorViajeYEstaFacturado(
                viajeRemitoDTO.getIdViaje(), viajeRemitoDTO.getIdRemito(), viajeRemitoDTO.getEstaFacturado()==1);
        for (ViajeTramoRemito elemento : elementos) {
            int id = elemento.getViajeTramo().getViaje().getId();
            Personal p = elemento.getViajeTramo().getViaje().getPersonal();
            ChoferProveedor c = elemento.getViajeTramo().getViaje().getChoferProveedor();
            boolean viajePropio = elemento.getViajeTramo().getViaje().isEsViajePropio();
            elemento.getViajeTramo().setViaje(new Viaje());
            elemento.getViajeTramo().getViaje().setPersonal(p);
            elemento.getViajeTramo().getViaje().setChoferProveedor(c);
            elemento.getViajeTramo().getViaje().setEsViajePropio(viajePropio);
            elemento.getViajeTramo().getViaje().setId(id);
        }
        return retornarObjeto(elementos, true);
    }

    //Obtiene una lista por viajeRemito
    public Object listarPorViajeRemito(int idRemito) throws IOException {
        List<ViajeTramoRemito> elementos = elementoDAO.findByViajeRemito(
                viajeRemitoDAO.findById(idRemito).get());
        return retornarObjeto(elementos, false);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTramoRemito agregar(ViajeTramoRemito elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTramoRemito elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Convierte una lista o un elemento a object para retornar con filtros aplicados
    private Object retornarObjeto(List<ViajeTramoRemito> elementos, boolean b) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = b ? SimpleBeanPropertyFilter
                .serializeAllExcept("ordenesVentas", "cliente", "viajeTramo",
                        "datos", "hijos", "ventaComprobante") : SimpleBeanPropertyFilter
                        .serializeAllExcept("ordenesVentas", "cliente", "viaje", "viajeTramo",
                                "datos", "hijos", "ventaComprobante");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clientefiltro", theFilter)
                .addFilter("viajetramofiltro", theFilter)
                .addFilter("viajefiltro", theFilter)
                .addFilter("clienteordenventafiltro", theFilter)
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter)
                .addFilter("viajetramoclientefiltro", theFilter)
                .addFilter("filtroVentaComprobanteItemCR", theFilter)
                .addFilter("filtroPlanCuenta", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

}

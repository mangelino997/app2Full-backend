package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IEmpresaOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.ITipoTarifaDAO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.EmpresaOrdenVenta;
import ar.com.draimo.jitws.model.OrdenVenta;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio EmpresaOrdenVenta
 *
 * @author blas
 */
@Service
public class EmpresaOrdenVentaService {

    //Define la referencia al dao
    @Autowired
    IOrdenVentaDAO ordenVentaDAO;

    //Define la referencia a clienteDAO
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define la referencia al dao OrdenVenta
    @Autowired
    IEmpresaOrdenVentaDAO elementoDAO;

    //Define la referencia al dao TipoTarifaDAO
    @Autowired
    ITipoTarifaDAO tipoTarifaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        EmpresaOrdenVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<EmpresaOrdenVenta> empresaOrdenesVentas = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("empresa");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("empresaordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(empresaOrdenesVentas);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por Empresa
    public Object listarPorEmpresa(int idEmpresa) throws IOException {
        List<EmpresaOrdenVenta> empresaOrdenesVentas = elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("empresa");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("empresaordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(empresaOrdenesVentas);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por OrdenVenta
    public Object listarPorOrdenVenta(int idOrdenVenta) throws IOException {
        OrdenVenta ordenVenta = ordenVentaDAO.findById(idOrdenVenta).get();
        List<EmpresaOrdenVenta> elementos = elementoDAO.findByOrdenVenta(ordenVenta);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("empresa");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("empresaordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene por compania de Empresa y OrdenVenta
    public Object listarPorEmpresaYOrdenVenta(int idEmpresa, int idOrdenVenta) throws IOException {
        Empresa elemento = empresaDAO.findById(idEmpresa).get();
        OrdenVenta ordenVenta = ordenVentaDAO.findById(idOrdenVenta).get();
        List<EmpresaOrdenVenta> elementos = elementoDAO.findByEmpresaAndOrdenVenta(elemento, ordenVenta);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("empresa");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("empresaordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(EmpresaOrdenVenta elemento) throws IOException {
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        elementoDAO.saveAndFlush(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("empresa");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("empresaordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(EmpresaOrdenVenta elemento) throws IOException {
        elemento.setFechaUltMod(new Date(new java.util.Date().getTime()));
            elementoDAO.save(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("empresa");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("empresaordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

}

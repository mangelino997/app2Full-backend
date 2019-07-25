package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
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
 * Servicio OrdenVenta
 *
 * @author blas
 */
@Service
public class OrdenVentaService {

    //Define la referencia al dao
    @Autowired
    IOrdenVentaDAO elementoDAO;

    //Define la referencia al dao cliente
    @Autowired
    IClienteDAO clienteDAO;

    //Define la referencia al dao empresaa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<OrdenVenta> listar() throws IOException {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<OrdenVenta> listarPorNombre(String nombre) {
        if (nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Obtiene una lista por cliente
    public Object listarPorCliente(int idCliente) throws IOException {
        List<OrdenVenta> remitos = elementoDAO.findByCliente(clienteDAO.findById(idCliente));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por empresa
    public Object listarPorEmpresa(int idEmpresa) throws IOException {
        List<OrdenVenta> remitos = elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa));
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(remitos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenVenta agregar(OrdenVenta elemento) {
        //Formatea los string de OrdenVenta
        elemento = formatearStrings(elemento);
        //Establece la fecha actual
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        //Establece activa en true
        elemento.setEstaActiva(true);
        //Establece ActivaDesde con fecha actual
        elemento.setActivaDesde(new Date(new java.util.Date().getTime()));
        //Agrega la orden de venta
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public OrdenVenta actualizar(OrdenVenta elemento) {
        //Formatea los string de OrdenVenta
        elemento = formatearStrings(elemento);
        //Actualiza la orden de venta
        return elementoDAO.saveAndFlush(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

    //Formatea los strings
    private OrdenVenta formatearStrings(OrdenVenta elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        if (elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }

}
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IEmpresaOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaEscalaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTarifaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaTramoDAO;
import ar.com.draimo.jitws.model.ClienteOrdenVenta;
import ar.com.draimo.jitws.model.EmpresaOrdenVenta;
import ar.com.draimo.jitws.model.OrdenVenta;
import ar.com.draimo.jitws.model.OrdenVentaEscala;
import ar.com.draimo.jitws.model.OrdenVentaTarifa;
import ar.com.draimo.jitws.model.OrdenVentaTramo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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

    //Define la referencia al dao
    @Autowired
    IClienteOrdenVentaDAO clienteOrdenVentaDAO;

    //Define la referencia al dao
    @Autowired
    IEmpresaOrdenVentaDAO empresaOrdenVentaDAO;

    //Define la referencia al dao
    @Autowired
    IOrdenVentaTramoDAO ordenVentaTramoDAO;

    //Define la referencia al dao
    @Autowired
    IOrdenVentaEscalaDAO ordenVentaEscalaDAO;

    //Define la referencia al dao
    @Autowired
    IOrdenVentaTarifaDAO ordenVentaTarifaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrdenVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<OrdenVenta> elementos = elementoDAO.findAll();
        return elementos;
    }

    //Obtiene la lista por empresa
    public List<OrdenVenta> listarPorEmpresa(int idEmpresa) {
        return elementoDAO.listarPorEmpresa(idEmpresa);
    }

    //Obtiene la lista por cliente
    public List<OrdenVenta> listarPorCliente(int idCliente) {
        return elementoDAO.listarPorCliente(idCliente);
    }

    //Obtiene una lista por nombre
    public Object listarPorNombre(String nombre) throws IOException {
        List<OrdenVenta> elementos =new ArrayList<>();
        if (nombre.equals("***")) {
            elementos= elementoDAO.findAll();
        } else {
            elementos= elementoDAO.findByNombreContaining(nombre);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public int agregar(String elementoString, String clienteString, String empresaString,
            String ordenVentaTarifaString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        OrdenVenta elemento = mapper.readValue(elementoString, OrdenVenta.class);
        OrdenVentaTarifa ordenVentaTarifa = mapper.readValue(ordenVentaTarifaString, OrdenVentaTarifa.class);
        //Formatea los string de OrdenVenta
        elemento = formatearStrings(elemento);
        //Establece la fecha actual
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        //Establece activa en true
        elemento.setEstaActiva(true);
        //Establece ActivaDesde con fecha actual
        elemento.setActivaDesde(new Date(new java.util.Date().getTime()));
        //Agrega la orden de venta
        elemento = elementoDAO.saveAndFlush(elemento);
        ordenVentaTarifa.setOrdenVenta(elemento);
        ordenVentaTarifa = ordenVentaTarifaDAO.saveAndFlush(ordenVentaTarifa);
        if(clienteString!=null) {
            ClienteOrdenVenta clienteOrdenVenta = mapper.readValue(clienteString, ClienteOrdenVenta.class);
            clienteOrdenVenta.setOrdenVenta(elemento);
            clienteOrdenVenta.setFechaAlta(elemento.getFechaAlta());
            clienteOrdenVenta.setEstaActiva(true);
            clienteOrdenVentaDAO.saveAndFlush(clienteOrdenVenta);
        }
        if(empresaString!=null) {
            EmpresaOrdenVenta empresaOrdenVenta = mapper.readValue(empresaString, EmpresaOrdenVenta.class);
            empresaOrdenVenta.setOrdenVenta(elemento);
            empresaOrdenVenta.setFechaAlta(elemento.getFechaAlta());
            empresaOrdenVenta.setEstaActiva(true);
            empresaOrdenVentaDAO.saveAndFlush(empresaOrdenVenta);
        }
        return ordenVentaTarifa.getId();
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Object actualizar(OrdenVenta elemento) throws IOException {
        //Formatea los string de OrdenVenta
        elemento = formatearStrings(elemento);
        //Actualiza la orden de venta
         elementoDAO.save(elemento);
         ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
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
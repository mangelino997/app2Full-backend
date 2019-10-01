//Paquete al que pertenece el servicio
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
import ar.com.draimo.jitws.model.OrdenVentaTarifa;
import com.fasterxml.jackson.databind.DeserializationFeature;
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

    //Define la referencia al dao clienteOrdenVenta
    @Autowired
    IClienteOrdenVentaDAO clienteOrdenVentaDAO;

    //Define la referencia al dao EmpresaOrdenVenta
    @Autowired
    IEmpresaOrdenVentaDAO empresaOrdenVentaDAO;

    //Define la referencia al dao OrdenVentaTramo
    @Autowired
    IOrdenVentaTramoDAO ordenVentaTramoDAO;

    //Define la referencia al dao OrdenVentaEscala
    @Autowired
    IOrdenVentaEscalaDAO ordenVentaEscalaDAO;

    //Define la referencia al dao OrdenVentaTarifa
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
        List<OrdenVenta> elementos = nombre.equals("***")?
            elementoDAO.findAll():elementoDAO.findByNombreContaining(nombre);
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
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OrdenVenta ordenVenta = mapper.readValue(elementoString, OrdenVenta.class);
        OrdenVentaTarifa ordenVentaTarifa = mapper.readValue(ordenVentaTarifaString, OrdenVentaTarifa.class);
        ClienteOrdenVenta clienteOrdenVenta;
        EmpresaOrdenVenta empresaOrdenVenta;
        //Formatea los string de OrdenVenta
        ordenVenta = formatearStrings(ordenVenta);
        //Establece la fecha actual
        ordenVenta.setFechaAlta(new Date(new java.util.Date().getTime()));
        //Establece activa en true
        ordenVenta.setEstaActiva(true);
        //Establece ActivaDesde con fecha actual
        ordenVenta.setActivaDesde(new Date(new java.util.Date().getTime()));
        //Agrega la orden de venta
        ordenVenta = elementoDAO.saveAndFlush(ordenVenta);
        ordenVentaTarifa.setOrdenVenta(ordenVenta);
        ordenVentaTarifa = ordenVentaTarifaDAO.saveAndFlush(ordenVentaTarifa);
        if (!clienteString.equals("null")) {
            clienteOrdenVenta = mapper.readValue(clienteString, ClienteOrdenVenta.class);
            clienteOrdenVenta.setFechaAlta(ordenVenta.getFechaAlta());
            clienteOrdenVenta.setEstaActiva(true);
            clienteOrdenVenta.setOrdenVenta(ordenVenta);
            clienteOrdenVentaDAO.saveAndFlush(clienteOrdenVenta);
        }
        if (!empresaString.equals("null")) {
            empresaOrdenVenta = mapper.readValue(empresaString, EmpresaOrdenVenta.class);
            empresaOrdenVenta.setFechaAlta(ordenVenta.getFechaAlta());
            empresaOrdenVenta.setEstaActiva(true);
            empresaOrdenVenta.setOrdenVenta(ordenVenta);
            empresaOrdenVentaDAO.saveAndFlush(empresaOrdenVenta);
        }
        return ordenVenta.getId();
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
        //Obtiene la orden de venta por id
        OrdenVenta ordenVenta = elementoDAO.findById(id).get();
        //Obtiene las ordenes de venta tarifa por id orden venta
        List<OrdenVentaTarifa> ordenesVentasTarifas = 
                ordenVentaTarifaDAO.findByOrdenVenta(ordenVenta);
        //Recorre las ordenes de ventas tarifas para eliminar escalas o tramos referenciados
        for(OrdenVentaTarifa ordenVentaTarifa : ordenesVentasTarifas) {
            //Verifica si se trata de por escala o por tramo
            if(ordenVentaTarifa.getTipoTarifa().getPorEscala()) {
                //Elimina las escalas por orden venta tarifa
                ordenVentaEscalaDAO.deleteByOrdenVentaTarifa(ordenVentaTarifa);
            } else {
                ordenVentaTramoDAO.deleteByOrdenVentaTarifa(ordenVentaTarifa);
            }
            //Elimina la orden venta tarifa
            ordenVentaTarifaDAO.delete(ordenVentaTarifa);
        }
        //Busca ordenes de venta asignada a empresa y si existe, elimina
        empresaOrdenVentaDAO.deleteByOrdenVenta(ordenVenta);
        //Busca ordenes de venta asignada a cliente y si existe, elimina
        clienteOrdenVentaDAO.deleteByOrdenVenta(ordenVenta);
        //Elimina la orden de venta
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
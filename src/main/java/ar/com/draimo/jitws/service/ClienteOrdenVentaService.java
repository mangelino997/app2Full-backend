package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IClienteOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.dao.ITipoTarifaDAO;
import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ClienteOrdenVenta;
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
 * Servicio ClienteOrdenVenta
 * @author blas
 */

@Service
public class ClienteOrdenVentaService {
    
    //Define la referencia al dao
    @Autowired
    IOrdenVentaDAO ordenVentaDAO;
    
    //Define la referencia a clienteDAO
    @Autowired
    IClienteDAO clienteDAO;
    
    //Define la referencia al dao OrdenVenta
    @Autowired
    IClienteOrdenVentaDAO elementoDAO;
    
    //Define la referencia al dao TipoTarifaDAO
    @Autowired
    ITipoTarifaDAO tipoTarifaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ClienteOrdenVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ClienteOrdenVenta> clienteOrdenesVentas = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(clienteOrdenesVentas);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista por Cliente
    public Object listarPorCliente(int idCliente) throws IOException {
        List<ClienteOrdenVenta> clienteOrdenesVentas = elementoDAO.findByCliente(clienteDAO.findById(idCliente).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(clienteOrdenesVentas);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista por OrdenVenta
    public Object listarPorOrdenVenta(int idOrdenVenta) throws IOException {
        OrdenVenta ordenVenta = ordenVentaDAO.findById(idOrdenVenta).get();
        List<ClienteOrdenVenta> clienteOrdenesVentas =  elementoDAO.findByOrdenVenta(ordenVenta);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(clienteOrdenesVentas);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene por compania de Cliente y OrdenVenta
    public Object listarPorClienteYOrdenVenta(int idCliente, int idOrdenVenta) throws IOException {
        Cliente cliente = clienteDAO.findById(idCliente).get();
        OrdenVenta ordenVenta = ordenVentaDAO.findById(idOrdenVenta).get();
        List<ClienteOrdenVenta> clienteOrdenesVentas = elementoDAO.findByClienteAndOrdenVenta(cliente, ordenVenta);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(clienteOrdenesVentas);
        return mapper.readValue(string, Object.class);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ClienteOrdenVenta agregar(ClienteOrdenVenta elemento) {
        ClienteOrdenVenta clienteOrdenVenta;
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        if(elemento.isEsOrdenVentaPorDefecto()){
            clienteOrdenVenta= elementoDAO.findByClienteAndEsOrdenVentaPorDefectoTrue(
                    elemento.getCliente());
            if (clienteOrdenVenta!=null) {
                clienteOrdenVenta.setEsOrdenVentaPorDefecto(false);
                elementoDAO.save(clienteOrdenVenta);
            }
        }
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ClienteOrdenVenta elemento) {
        ClienteOrdenVenta clienteOrdenVenta;
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        if(elemento.isEsOrdenVentaPorDefecto()){
            clienteOrdenVenta= elementoDAO.findByClienteAndEsOrdenVentaPorDefectoTrue(
                    elemento.getCliente());
            if (clienteOrdenVenta!=null) {
                clienteOrdenVenta.setEsOrdenVentaPorDefecto(false);
                elementoDAO.save(clienteOrdenVenta);
            }
        }
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
}

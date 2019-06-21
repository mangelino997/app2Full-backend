package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IClienteOrdenVentaDAO;
import ar.com.draimo.jitws.dao.IOrdenVentaDAO;
import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ClienteOrdenVenta;
import ar.com.draimo.jitws.model.OrdenVenta;
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
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ClienteOrdenVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ClienteOrdenVenta> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Cliente
    public List<ClienteOrdenVenta> listarPorCliente(int idCliente) {
        Cliente cliente = clienteDAO.findById(idCliente).get();
        return elementoDAO.findByCliente(cliente);
    }
    
    //Obtiene una lista por OrdenVenta
    public List<ClienteOrdenVenta> listarPorOrdenVenta(int idOrdenVenta) {
        OrdenVenta ordenVenta = ordenVentaDAO.findById(idOrdenVenta).get();
        return elementoDAO.findByOrdenVenta(ordenVenta);
    }
    
    //Obtiene por compania de Cliente y OrdenVenta
    public List<ClienteOrdenVenta> listarPorClienteYOrdenVenta(int idCliente, int idOrdenVenta) {
        Cliente cliente = clienteDAO.findById(idCliente).get();
        OrdenVenta ordenVenta = ordenVentaDAO.findById(idOrdenVenta).get();
        return elementoDAO.findByClienteAndOrdenVenta(cliente, ordenVenta);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ClienteOrdenVenta agregar(ClienteOrdenVenta elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ClienteOrdenVenta elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ClienteOrdenVenta elemento) {
        elementoDAO.delete(elemento);
    }
    
}

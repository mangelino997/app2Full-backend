package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IContactoClienteDAO;
import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ContactoCliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Contacto Cliente
 * @author blas
 */

@Service
public class ContactoClienteService {
    
    //Define la referencia al dao
    @Autowired
    IContactoClienteDAO elementoDAO;
    
    //Define la referencia a cliente dao
    @Autowired
    IClienteDAO clienteDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ContactoCliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<ContactoCliente> contactoClientes = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(contactoClientes);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista por nombre
    public Object listarPorNombre(String nombre) throws IOException {
        List<ContactoCliente> contactoClientes = new ArrayList<>();
        if(nombre.equals("***")) {
            contactoClientes = elementoDAO.findAll();
        } else {
            contactoClientes = elementoDAO.findByNombreContaining(nombre);
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(contactoClientes);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene por cliente
    public Object listarPorCliente(int idCliente) throws IOException {
        //Obtiene el cliente por id
        Optional<Cliente> cliente = clienteDAO.findById(idCliente);
        List<ContactoCliente> contactoClientes = elementoDAO.findByCliente(cliente);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("cliente");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("clienteordenventafiltro", theFilter);
        String string = mapper.writer(filters).writeValueAsString(contactoClientes);
        return mapper.readValue(string, Object.class);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ContactoCliente agregar(ContactoCliente elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ContactoCliente elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ContactoCliente elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ContactoCliente formatearStrings(ContactoCliente elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        if(elemento.getTelefonoFijo() != null) {
            elemento.setTelefonoFijo(elemento.getTelefonoFijo().trim());
        }
        if(elemento.getTelefonoMovil() != null) {
            elemento.setTelefonoMovil(elemento.getTelefonoMovil().trim());
        }
        if(elemento.getCorreoelectronico() != null) {
            elemento.setCorreoelectronico(elemento.getCorreoelectronico().trim().toLowerCase());
        }
        return elemento;
    }
    
}

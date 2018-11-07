package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.IContactoClienteDAO;
import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.ContactoCliente;
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
    public List<ContactoCliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ContactoCliente> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene por cliente
    public List<ContactoCliente> listarPorCliente(int idCliente) {
        //Obtiene el cliente por id
        Optional<Cliente> cliente = clienteDAO.findById(idCliente);
        //Retorna por cliente
        return elementoDAO.findByCliente(cliente);
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

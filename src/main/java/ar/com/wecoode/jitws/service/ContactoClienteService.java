package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IClienteDAO;
import ar.com.wecoode.jitws.dao.IContactoClienteDAO;
import ar.com.wecoode.jitws.model.Cliente;
import ar.com.wecoode.jitws.model.ContactoCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return elementoDAO.obtenerSiguienteId();
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
    public ContactoCliente obtenerPorCliente(int idCliente) {
        //Obtiene el cliente por id
        Optional<Cliente> cliente = clienteDAO.findById(idCliente);
        //Retorna por cliente
        return elementoDAO.findByCliente(cliente);
    }
    
    //Agrega un registro
    public void agregar(ContactoCliente elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(ContactoCliente elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ContactoCliente elemento) {
        elementoDAO.delete(elemento);
    }
    
}

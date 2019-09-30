//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IContactoProveedorDAO;
import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.model.ContactoProveedor;
import ar.com.draimo.jitws.model.Proveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Contacto Proveedor
 * @author blas
 */

@Service
public class ContactoProveedorService {
    
    //Define la referencia al dao
    @Autowired
    IContactoProveedorDAO elementoDAO;
    
    //Define la referencia a proveedor dao
    @Autowired
    IProveedorDAO proveedorDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ContactoProveedor elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ContactoProveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ContactoProveedor> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene por proveedor
    public List<ContactoProveedor> listarPorProveedor(int idProveedor) {
        //Obtiene el proveedor por id
        Optional<Proveedor> proveedor = proveedorDAO.findById(idProveedor);
        return elementoDAO.findByProveedor(proveedor);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ContactoProveedor agregar(ContactoProveedor elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza el registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ContactoProveedor elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private ContactoProveedor formatearStrings(ContactoProveedor elemento) {
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
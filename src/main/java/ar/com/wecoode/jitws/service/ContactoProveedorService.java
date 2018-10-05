package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IContactoProveedorDAO;
import ar.com.wecoode.jitws.dao.IProveedorDAO;
import ar.com.wecoode.jitws.model.ContactoProveedor;
import ar.com.wecoode.jitws.model.Proveedor;
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
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<ContactoProveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ContactoProveedor> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }
    
    //Obtiene por proveedor
    public List<ContactoProveedor> listarPorProveedor(int idProveedor) {
        //Obtiene el proveedor por id
        Optional<Proveedor> proveedor = proveedorDAO.findById(idProveedor);
        //Retorna por proveedor
        return elementoDAO.findByProveedor(proveedor);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ContactoProveedor agregar(ContactoProveedor elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza el registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ContactoProveedor elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ContactoProveedor elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ContactoProveedor formatearStrings(ContactoProveedor elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        elemento.setCorreoelectronico(elemento.getCorreoelectronico().toLowerCase());
        return elemento;
    }
    
}

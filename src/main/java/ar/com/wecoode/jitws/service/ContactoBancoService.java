package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IContactoBancoDAO;
import ar.com.wecoode.jitws.dao.ISucursalBancoDAO;
import ar.com.wecoode.jitws.model.ContactoBanco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Contacto Banco
 * @author blas
 */

@Service
public class ContactoBancoService {
    
    //Define la referencia al dao
    @Autowired
    IContactoBancoDAO elementoDAO;
    
    //Define la referencia a sucursal banco dao
    @Autowired
    ISucursalBancoDAO sucursalBancoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ContactoBanco elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<ContactoBanco> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ContactoBanco> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ContactoBanco agregar(ContactoBanco elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ContactoBanco elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ContactoBanco elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ContactoBanco formatearStrings(ContactoBanco elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        elemento.setCorreoelectronico(elemento.getCorreoelectronico().toLowerCase());
        return elemento;
    }
    
}

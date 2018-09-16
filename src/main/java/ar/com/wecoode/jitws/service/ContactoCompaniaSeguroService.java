package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IContactoCompaniaSeguroDAO;
import ar.com.wecoode.jitws.model.ContactoCompaniaSeguro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Compania Seguro
 * @author blas
 */

@Service
public class ContactoCompaniaSeguroService {
    
    //Define la referencia al dao
    @Autowired
    IContactoCompaniaSeguroDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ContactoCompaniaSeguro elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<ContactoCompaniaSeguro> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ContactoCompaniaSeguro> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ContactoCompaniaSeguro agregar(ContactoCompaniaSeguro elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ContactoCompaniaSeguro elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ContactoCompaniaSeguro elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ContactoCompaniaSeguro formatearStrings(ContactoCompaniaSeguro elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        elemento.setCorreoelectronico(elemento.getCorreoelectronico().toLowerCase());
        return elemento;
    }
    
}

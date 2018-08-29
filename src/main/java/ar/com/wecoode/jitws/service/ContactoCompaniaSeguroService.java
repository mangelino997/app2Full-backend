package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IContactoCompaniaSeguroDAO;
import ar.com.wecoode.jitws.model.ContactoCompaniaSeguro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return elementoDAO.obtenerSiguienteId();
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
    public void agregar(ContactoCompaniaSeguro elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(ContactoCompaniaSeguro elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ContactoCompaniaSeguro elemento) {
        elementoDAO.delete(elemento);
    }
    
}

package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IContactoBancoDAO;
import ar.com.wecoode.jitws.dao.ISucursalBancoDAO;
import ar.com.wecoode.jitws.model.ContactoBanco;
import ar.com.wecoode.jitws.model.SucursalBanco;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return elementoDAO.obtenerSiguienteId();
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
    
    //Obtiene por sucursal banco
    public ContactoBanco obtenerPorSucursalBanco(int idSucursalBanco) {
        //Obtiene la sucursal banco por id
        Optional<SucursalBanco> sucursalBanco = sucursalBancoDAO.findById(idSucursalBanco);
        //Retorna por sucursal banco
        return elementoDAO.findBySucursalBanco(sucursalBanco);
    }
    
    //Agrega un registro
    public void agregar(ContactoBanco elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(ContactoBanco elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ContactoBanco elemento) {
        elementoDAO.delete(elemento);
    }
    
}

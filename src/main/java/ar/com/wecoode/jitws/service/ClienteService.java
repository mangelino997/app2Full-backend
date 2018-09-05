package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IClienteDAO;
import ar.com.wecoode.jitws.model.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Cliente
 * @author blas
 */

@Service
public class ClienteService {
    
    //Define la referencia al dao
    @Autowired
    IClienteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene una lista completa
    public List<Cliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene por id
    public Optional<Cliente> obtenerPorId(int id) {
        return elementoDAO.findById(id);
    }
    
    //Obtiene una lista por razon social
    public List<Cliente> listarPorRazonSocial(String razonSocial) {
        if(razonSocial.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByRazonSocialContaining(razonSocial);
        }
    }
    
    //Obtiene una lista por nombre de fantasia
    public List<Cliente> listarPorNombreFantasia(String nombreFantasia) {
        if(nombreFantasia.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreFantasiaContaining(nombreFantasia);
        }
    }
    
    //Obtiene una lista por numero de documento
    public List<Cliente> listarPorNumeroDocumento(String numeroDocumento) {
        if(numeroDocumento.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNumeroDocumentoContaining(numeroDocumento);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(Cliente elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Cliente elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Cliente elemento) {
        elementoDAO.delete(elemento);
    }
    
}

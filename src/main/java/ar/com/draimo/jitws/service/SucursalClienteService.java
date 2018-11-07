package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.ISucursalClienteDAO;
import ar.com.draimo.jitws.model.Cliente;
import ar.com.draimo.jitws.model.SucursalCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio SucursalCliente
 * @author blas
 */

@Service
public class SucursalClienteService {
    
    //Define la referencia al dao
    @Autowired
    ISucursalClienteDAO elementoDAO;
    
    //Define la referencia al dao cliente
    @Autowired
    IClienteDAO clienteDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SucursalCliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<SucursalCliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<SucursalCliente> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista por nombre de banco
    public List<SucursalCliente> listarPorCliente(int idCliente) {
        //Obtiene el cliente por id
        Optional<Cliente> cliente = clienteDAO.findById(idCliente);
        return elementoDAO.findByCliente(cliente);
    }
    
    //Obtiene una lista por alias del cliente
    public List<SucursalCliente> listarPorAliasCliente(String alias) {
        return elementoDAO.findByCliente_AliasContaining(alias);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SucursalCliente agregar(SucursalCliente elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SucursalCliente elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(SucursalCliente elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private SucursalCliente formatearStrings(SucursalCliente elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setDomicilio(elemento.getDomicilio().trim());
        if(elemento.getTelefonoFijo() != null) {
            elemento.setTelefonoFijo(elemento.getTelefonoFijo().trim());
        }
        if(elemento.getTelefonoMovil() != null) {
            elemento.setTelefonoMovil(elemento.getTelefonoMovil().trim());
        }
        return elemento;
    }
    
}

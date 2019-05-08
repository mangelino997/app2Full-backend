package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IChoferProveedorDAO;
import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.model.ChoferProveedor;
import ar.com.draimo.jitws.model.Proveedor;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Chofer Proveedor
 * @author blas
 */

@Service
public class ChoferProveedorService {
    
    //Define la referencia al dao
    @Autowired
    IChoferProveedorDAO elementoDAO;
    
    //Define la referencia al dao proveedor
    @Autowired
    IProveedorDAO proveedorDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ChoferProveedor elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ChoferProveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ChoferProveedor> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }
    
    //Obtiene una lista por proveedor
    public List<ChoferProveedor> listarPorProveedor(int id) {
        //Obtiene el proveedor por id
        Optional<Proveedor> proveedor = proveedorDAO.findById(id);
        return elementoDAO.findByProveedor(proveedor);
    }
    
    //Obtiene una lista por alias y proveedor
    public List<ChoferProveedor> listarPorAliasYProveedor(String alias, int idProveedor) {
        return elementoDAO.findByAliasContainingAndProveedor(alias, proveedorDAO.findById(idProveedor).get());
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ChoferProveedor agregar(ChoferProveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta((new Date(new java.util.Date().getTime())));
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(ChoferProveedor elemento) {
        elemento.setAlias(elemento.getId() + " - " + elemento.getNombre() 
                + " - " + elemento.getNumeroDocumento());
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ChoferProveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        elemento.setAlias(elemento.getId() + " - " + elemento.getNombre() 
                + " - " + elemento.getNumeroDocumento());
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ChoferProveedor elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ChoferProveedor formatearStrings(ChoferProveedor elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setDomicilio(elemento.getDomicilio().trim());
        elemento.setNumeroDocumento(elemento.getNumeroDocumento().trim());
        if(elemento.getTelefonoFijo() != null) {
            elemento.setTelefonoFijo(elemento.getTelefonoFijo().trim());
        }
        if(elemento.getTelefonoMovil() != null) {
            elemento.setTelefonoMovil(elemento.getTelefonoMovil().trim());
        }
        return elemento;
    }
    
}

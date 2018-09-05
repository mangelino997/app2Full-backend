package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IProveedorDAO;
import ar.com.wecoode.jitws.model.Proveedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Proveedor
 * @author blas
 */

@Service
public class ProveedorService {

    //Define la referencia al dao
    @Autowired
    IProveedorDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Proveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Proveedor> listarPorAlias(String alias) {
        return elementoDAO.findByAliasContaining(alias);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(Proveedor elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Proveedor elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Proveedor elemento) {
        elementoDAO.delete(elemento);
    }

}

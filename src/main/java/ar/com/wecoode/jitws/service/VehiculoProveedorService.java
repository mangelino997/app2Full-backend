package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IVehiculoProveedorDAO;
import ar.com.wecoode.jitws.model.VehiculoProveedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio VehiculoProveedor
 * @author blas
 */

@Service
public class VehiculoProveedorService {

    //Define la referencia al dao
    @Autowired
    IVehiculoProveedorDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<VehiculoProveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<VehiculoProveedor> listarPorAlias(String alias) {
        return elementoDAO.findByAliasContaining(alias);
    }
    
    //Obtiene una lista por alias filtro remolque
    public List<VehiculoProveedor> listarPorAliasFiltroRemolque(String alias) {
        return elementoDAO.findByAliasContainingAndEsRemolqueTrue(alias);
    }

    //Agrega un registro
    public void agregar(VehiculoProveedor elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(VehiculoProveedor elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(VehiculoProveedor elemento) {
        elementoDAO.delete(elemento);
    }

}

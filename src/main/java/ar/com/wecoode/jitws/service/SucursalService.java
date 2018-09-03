package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ISucursalDAO;
import ar.com.wecoode.jitws.model.Sucursal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Sucursal
 * @author blas
 */

@Service
public class SucursalService {

    //Define la referencia al dao
    @Autowired
    ISucursalDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Sucursal> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Sucursal> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(Sucursal elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(Sucursal elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Sucursal elemento) {
        elementoDAO.delete(elemento);
    }

}

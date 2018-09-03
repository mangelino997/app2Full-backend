package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IVendedorDAO;
import ar.com.wecoode.jitws.model.Vendedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Vendedor
 * @author blas
 */

@Service
public class VendedorService {

    //Define la referencia al dao
    @Autowired
    IVendedorDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Vendedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Vendedor> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(Vendedor elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(Vendedor elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Vendedor elemento) {
        elementoDAO.delete(elemento);
    }

}

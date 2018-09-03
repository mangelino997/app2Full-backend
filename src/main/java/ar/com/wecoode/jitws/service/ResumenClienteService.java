package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IResumenClienteDAO;
import ar.com.wecoode.jitws.model.ResumenCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Provincia
 * @author blas
 */

@Service
public class ResumenClienteService {

    //Define la referencia al dao
    @Autowired
    IResumenClienteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ResumenCliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ResumenCliente> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(ResumenCliente elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ResumenCliente elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ResumenCliente elemento) {
        elementoDAO.delete(elemento);
    }

}

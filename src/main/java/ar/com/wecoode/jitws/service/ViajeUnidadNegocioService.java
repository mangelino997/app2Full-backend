package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IViajeUnidadNegocioDAO;
import ar.com.wecoode.jitws.model.ViajeUnidadNegocio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio ViajeUnidadNegocio
 * @author blas
 */

@Service
public class ViajeUnidadNegocioService {

    //Define la referencia al dao
    @Autowired
    IViajeUnidadNegocioDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<ViajeUnidadNegocio> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ViajeUnidadNegocio> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    public void agregar(ViajeUnidadNegocio elemento) {
        elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public void actualizar(ViajeUnidadNegocio elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(ViajeUnidadNegocio elemento) {
        elementoDAO.delete(elemento);
    }

}

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IResumenClienteDAO;
import ar.com.draimo.jitws.model.ResumenCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Resumen Cliente
 * @author blas
 */

@Service
public class ResumenClienteService {

    //Define la referencia al dao
    @Autowired
    IResumenClienteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ResumenCliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ResumenCliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ResumenCliente> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ResumenCliente agregar(ResumenCliente elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ResumenCliente elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ResumenCliente elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ResumenCliente formatearStrings(ResumenCliente elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

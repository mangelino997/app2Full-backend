package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISindicatoDAO;
import ar.com.draimo.jitws.model.Sindicato;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Sindicato
 * @author blas
 */

@Service
public class SindicatoService {

    //Define la referencia al dao
    @Autowired
    ISindicatoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Sindicato elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Sindicato> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Sindicato> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Sindicato agregar(Sindicato elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Sindicato elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Sindicato elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Sindicato formatearStrings(Sindicato elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        if(elemento.getSitioWeb() != null) {
            elemento.setSitioWeb(elemento.getSitioWeb().trim().toLowerCase());
        }
        return elemento;
    }

}

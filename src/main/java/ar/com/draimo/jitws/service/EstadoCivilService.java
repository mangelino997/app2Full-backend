package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEstadoCivilDAO;
import ar.com.draimo.jitws.model.EstadoCivil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Estado Civil
 * @author blas
 */

@Service
public class EstadoCivilService {
    
    //Define la referencia al dao
    @Autowired
    IEstadoCivilDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        EstadoCivil elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<EstadoCivil> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<EstadoCivil> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public EstadoCivil agregar(EstadoCivil elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(EstadoCivil elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(EstadoCivil elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private EstadoCivil formatearStrings(EstadoCivil elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}

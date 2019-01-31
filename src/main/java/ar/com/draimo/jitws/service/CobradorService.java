package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobradorDAO;
import ar.com.draimo.jitws.model.Cobrador;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Cobrador
 * @author blas
 */

@Service
public class CobradorService {
    
    //Define la referencia al dao
    @Autowired
    ICobradorDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Cobrador elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Cobrador> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Cobrador> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Cobrador agregar(Cobrador elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Cobrador elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Cobrador elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Cobrador formatearStrings(Cobrador elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}

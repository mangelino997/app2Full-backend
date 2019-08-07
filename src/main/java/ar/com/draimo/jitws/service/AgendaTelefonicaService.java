package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAgendaTelefonicaDAO;
import ar.com.draimo.jitws.model.AgendaTelefonica;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Agenda Telefonica
 * @author blas
 */

@Service
public class AgendaTelefonicaService {
    
    
    //Define la referencia al dao
    @Autowired
    IAgendaTelefonicaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AgendaTelefonica elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AgendaTelefonica> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista por nombre
    public List<AgendaTelefonica> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AgendaTelefonica agregar(AgendaTelefonica elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AgendaTelefonica elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los string
    private AgendaTelefonica formatearStrings(AgendaTelefonica elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        if(elemento.getDomicilio() != null) {
            elemento.setDomicilio(elemento.getDomicilio().trim());
        }
        if(elemento.getTelefonoFijo() != null) {
            elemento.setTelefonoFijo(elemento.getTelefonoFijo().trim());
        }
        if(elemento.getTelefonoMovil() != null) {
            elemento.setTelefonoMovil(elemento.getTelefonoMovil().trim());
        }
        elemento.setCorreoelectronico(elemento.getCorreoelectronico().toLowerCase().trim());
        return elemento;
    }
    
}

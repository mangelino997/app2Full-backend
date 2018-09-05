package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IPersonalDAO;
import ar.com.wecoode.jitws.model.Personal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Personal
 * @author blas
 */

@Service
public class PersonalService {

    //Define la referencia al dao
    @Autowired
    IPersonalDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Personal> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Personal> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreCompletoContaining(nombre);
    }
    
    //Obtiene un chofer por nombre
    public List<Personal> listarChoferPorNombre(String nombre) {
        return elementoDAO.findByNombreCompletoContainingAndEsChofer(nombre, 1);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(Personal elemento) {
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Personal elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Personal elemento) {
        elementoDAO.delete(elemento);
    }

}

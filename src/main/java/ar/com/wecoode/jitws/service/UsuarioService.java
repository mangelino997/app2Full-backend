package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IUsuarioDAO;
import ar.com.wecoode.jitws.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Usuario
 * @author blas
 */

@Service
public class UsuarioService {

    //Define la referencia al dao
    @Autowired
    IUsuarioDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene una lista completa
    public List<Usuario> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene por username
    public Usuario obtenerPorUsername(String username) {
        return elementoDAO.findOneByUsername(username);
    }

    //Agrega un registro
    public Usuario agregar(Usuario elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    public Usuario actualizar(Usuario elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Usuario elemento) {
        elementoDAO.delete(elemento);
    }

}

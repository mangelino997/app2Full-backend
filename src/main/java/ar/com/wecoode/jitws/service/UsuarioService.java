package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IUsuarioDAO;
import ar.com.wecoode.jitws.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Usuario
 * @author blas
 */

@Service
public class UsuarioService {
    
    //Define la encriptacion de la contrasenia
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    @Transactional(rollbackFor = Exception.class)
    public Usuario agregar(Usuario elemento) {
        elemento.setPassword(bCryptPasswordEncoder.encode(elemento.getPassword()));
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Usuario actualizar(Usuario elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Usuario elemento) {
        elementoDAO.delete(elemento);
    }

}

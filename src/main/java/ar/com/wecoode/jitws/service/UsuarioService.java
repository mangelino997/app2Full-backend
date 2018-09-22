package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
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
        Usuario elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene una lista completa
    public List<Usuario> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene por username
    public Usuario obtenerPorUsername(String username) {
        return elementoDAO.findOneByUsername(username);
    }
    
    //Obtiene un listado por nombre
    public List<Usuario> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Usuario agregar(Usuario elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Usuario actualizar(Usuario elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Usuario elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Usuario formatearStrings(Usuario elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        elemento.setUsername(elemento.getUsername().trim().toLowerCase());
        elemento.setPassword(bCryptPasswordEncoder.encode(elemento.getPassword()));
        return elemento;
    }

}

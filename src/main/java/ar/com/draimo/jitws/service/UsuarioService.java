package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IRolDAO;
import ar.com.draimo.jitws.dao.IUsuarioDAO;
import ar.com.draimo.jitws.dao.IUsuarioEmpresaDAO;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Usuario;
import ar.com.draimo.jitws.model.UsuarioEmpresa;
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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Define la referencia al dao usuario
    @Autowired
    IUsuarioDAO elementoDAO;
    
    //Define la referencia al dao rol
    @Autowired
    IRolDAO rolDAO;
    
    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Define la referencia al dao usuarioempresa
    @Autowired
    IUsuarioEmpresaDAO usuarioEmpresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Usuario elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene una lista completa
    public List<Usuario> listar() {
        return elementoDAO.findAllByEsDesarrolladorFalse();
    }
    
    //Obtiene por username
    public Usuario obtenerPorUsername(String username) {
        return elementoDAO.findOneByUsername(username);
    }
    
    //Obtiene un listado por nombre
    public List<Usuario> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAllByEsDesarrolladorFalse();
        } else {
            return elementoDAO.findByNombreContainingAndEsDesarrolladorFalse(nombre);
        }
    }
    
    //Obtiene una lista por rol
    public List<Usuario> listarPorRol(int idRol) {
        return elementoDAO.findByRol(rolDAO.findById(idRol).get());
    }
    
    //Obtiene una lista por rol secundario
    public List<Usuario> listarPorRolSecundario(int idRol) {
        return elementoDAO.findByRolSecundario(rolDAO.findById(idRol).get());
    }
    
    //Obtiene una lista de usuarios por empresa
    public List<Usuario> listarPorEmpresa(int idEmpresa) {
        List<Usuario> usuarios = elementoDAO.listarPorEmpresaYMostrarTrue(idEmpresa);
        return usuarios;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Usuario agregar(Usuario elemento) {
        elemento = formatearStrings(elemento);
        elemento.setEsDesarrollador(false);
        elemento.setPassword(bCryptPasswordEncoder.encode(elemento.getPassword()));
        //Agrega el usuario
        Usuario usuario = elementoDAO.saveAndFlush(elemento);
        //Define un UsuarioEmpresa
        UsuarioEmpresa usuarioEmpresa;
        //Obtiene las empresas
        List<Empresa> empresas = empresaDAO.findAll();
        //Asigna todas empresas al nuevo usuario con mostrar en false
        for(Empresa empresa : empresas) {
            usuarioEmpresa = new UsuarioEmpresa();
            usuarioEmpresa.setUsuario(usuario);
            usuarioEmpresa.setEmpresa(empresa);
            usuarioEmpresa.setMostrar(false);
            usuarioEmpresaDAO.saveAndFlush(usuarioEmpresa);
        }
        return usuario;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Usuario actualizar(Usuario elemento) {
        elemento = formatearStrings(elemento);
        elemento.setEsDesarrollador(false);
        return elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Usuario elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Usuario formatearStrings(Usuario elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setUsername(elemento.getUsername().trim().toLowerCase());
        return elemento;
    }

}

package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IEmpresaDAO;
import ar.com.wecoode.jitws.dao.IUsuarioDAO;
import ar.com.wecoode.jitws.dao.IUsuarioEmpresaDAO;
import ar.com.wecoode.jitws.model.Empresa;
import ar.com.wecoode.jitws.model.Usuario;
import ar.com.wecoode.jitws.model.UsuarioEmpresa;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio UsuarioEmpresa
 * @author blas
 */

@Service
public class UsuarioEmpresaService {

    //Define la referencia al dao
    @Autowired
    IUsuarioEmpresaDAO elementoDAO;
    
    //Define la referencia al dao usuario
    @Autowired
    IUsuarioDAO usuarioDAO;
    
    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<UsuarioEmpresa> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene un listado por usuario
    public List<UsuarioEmpresa> listarPorUsuario(int idUsuario) {
        //Obtiene el usuario por id
        Optional<Usuario> usuario = usuarioDAO.findById(idUsuario);
        return elementoDAO.findByUsuario(usuario);
    }
    
    //Obtiene las empresas activas del usuario
    public List<UsuarioEmpresa> listarEmpresasActivasDeUsuario(int idUsuario) {
        //Obtiene el usuario por id
        Optional<Usuario> usuario = usuarioDAO.findById(idUsuario);
        return elementoDAO.findByUsuarioAndMostrarTrue(usuario);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public void agregar(UsuarioEmpresa elemento) {
        elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(List<UsuarioEmpresa> elementos) {
        //Recorre la lista
        for(int i = 0 ; i < elementos.size() ; i++) {
            //Actualiza el registro
            elementoDAO.save(elementos.get(i));
        }
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(UsuarioEmpresa elemento) {
        elementoDAO.delete(elemento);
    }
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, manteniendo el dato
     * 'Mostrar' de cada empresa
     */
    @Transactional(rollbackFor = Exception.class)
    public List<UsuarioEmpresa> eliminarTabla() {
        
        //Obtiene el listado de rolsubopcion
        List<UsuarioEmpresa> usuariosEmpresas = elementoDAO.findAll();

        //Elimina todos los datos de la tabla
        elementoDAO.eliminarTodo();
        
        //Reestablece el autoincremental
        elementoDAO.reestablecerAutoincremental();
        
        return usuariosEmpresas;
        
    }
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, manteniendo el dato
     * 'Mostrar' de cada empresa
     */
    @Transactional(rollbackFor = Exception.class)
    public void reestablecerTabla(List<UsuarioEmpresa> usuariosEmpresas) {
        
        //Obtiene la lista completa de roles
        List<Usuario> usuarios = usuarioDAO.findAll();
        
        //Obtiene la lista completa de subopciones
        List<Empresa> empresas = empresaDAO.findAll();
        
        boolean mostrar;
        //Define un RolSubopcion
        UsuarioEmpresa usuarioEmpresa;
        for (Usuario usuario : usuarios) {
            //Recorre la lista de submodulos
            for (Empresa empresa : empresas) {
                mostrar = false;
                //Crea una instancia de RolSubopcion
                usuarioEmpresa = new UsuarioEmpresa();
                usuarioEmpresa.setUsuario(usuario);
                usuarioEmpresa.setEmpresa(empresa);
                for(UsuarioEmpresa usuarioEmpresaObj : usuariosEmpresas) {
                    if(usuarioEmpresaObj.getUsuario().getId() == usuario.getId() 
                            && usuarioEmpresaObj.getEmpresa().getId() == empresa.getId()) {
                        mostrar = usuarioEmpresaObj.getMostrar();
                        break;
                    }
                }
                usuarioEmpresa.setMostrar(usuario.getId() == 1 ? true : mostrar);
                elementoDAO.saveAndFlush(usuarioEmpresa);
            }
        }
        
    }
    
    /*
     * Asigna todas las empresas a cada uno de los usuarios, eliminando todo los
     * datos y reestableciendo desde cero
     */
    @Transactional(rollbackFor = Exception.class)
    public void reestablecerTablaDesdeCero() {
        
        //Elimina todos los datos de la tabla
        elementoDAO.eliminarTodo();
        
        //Reestablece el autoincremental
        elementoDAO.reestablecerAutoincremental();
        
        //Obtiene la lista completa de roles
        List<Usuario> usuarios = usuarioDAO.findAll();
        
        //Obtiene la lista completa de subopciones
        List<Empresa> empresas = empresaDAO.findAll();
        
        //Define un RolSubopcion
        UsuarioEmpresa usuarioEmpresa;
        for (Usuario usuario : usuarios) {
            //Recorre la lista de submodulos
            for (Empresa empresa : empresas) {
                //Crea una instancia de RolSubopcion
                usuarioEmpresa = new UsuarioEmpresa();
                usuarioEmpresa.setUsuario(usuario);
                usuarioEmpresa.setEmpresa(empresa);
                usuarioEmpresa.setMostrar((usuario.getId() < 3));
                elementoDAO.saveAndFlush(usuarioEmpresa);
            }
        }
        
    }
    
}

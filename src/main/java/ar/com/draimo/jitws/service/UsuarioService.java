//Paquete al que pertence el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IRolDAO;
import ar.com.draimo.jitws.dao.ISucursalDAO;
import ar.com.draimo.jitws.dao.IUsuarioDAO;
import ar.com.draimo.jitws.dao.IUsuarioEmpresaDAO;
import ar.com.draimo.jitws.dto.InitUsuarioDTO;
import ar.com.draimo.jitws.dto.LoginDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.Usuario;
import ar.com.draimo.jitws.model.UsuarioEmpresa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Usuario
 *
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

    //Define la referencia al dao sucursalDAO
    @Autowired
    ISucursalDAO sucursalDAO;

    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define la referencia al dao usuarioempresa
    @Autowired
    IUsuarioEmpresaDAO usuarioEmpresaDAO;
    
    //Define usuarioEmpresaService
    @Autowired
    UsuarioEmpresaService usuarioEmpresaService;
    
    //Define menu service
    @Autowired
    MenuService menuService;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitUsuarioDTO inicializar(int idRol, int idSubopcion) {
        InitUsuarioDTO elemento = new InitUsuarioDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setRoles(rolDAO.findAll());
        elemento.setSucursales(sucursalDAO.findAll());
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Usuario elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene una lista completa
    public List<Usuario> listar() {
        return elementoDAO.findAllByEsDesarrolladorFalse();
    }

    //Obtiene por username
    public Usuario obtenerPorUsername(String username) {
        return elementoDAO.findOneByUsername(username);
    }
    
    /*
    * Obtiene un usuario, sus empresa activas y el menu de ese usuario por username
    * Retorna un DTO para la seccion Login
    */
    public LoginDTO obtenerUsuario(String username) {
        //Crea un login dto
        LoginDTO loginDTO = new LoginDTO();
        //Obtiene y establece el usuario
        loginDTO.setUsuario(elementoDAO.findOneByUsername(username));
        //Obtiene y establece las empresas del usuario
        loginDTO.setEmpresas(usuarioEmpresaService.listarEmpresasActivasDeUsuario(loginDTO.getUsuario().getId()));
        //Obtiene y establece el menu del usuario
        loginDTO.setMenuPrincipalDTO(menuService.listar(loginDTO.getUsuario().getRol().getId()));
        //Si el usuario tiene rol secundario, obtiene y establece el menu secundario
        if(loginDTO.getUsuario().getRolSecundario() != null) {
            loginDTO.setMenuSecundarioDTO(menuService.listar(loginDTO.getUsuario().getRolSecundario().getId()));
        }
        return loginDTO;
    }

    //Obtiene un listado por nombre
    public List<Usuario> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAllByEsDesarrolladorFalse()
                : elementoDAO.findByNombreContainingAndEsDesarrolladorFalse(nombre);
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
        return elementoDAO.listarPorEmpresaYMostrarTrue(idEmpresa);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Usuario agregar(Usuario elemento) {
        elemento.setEsDesarrollador(false);
        if (elemento.getPassword().length() > 15) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " PASSWORD");
        }
        elemento.setPassword(bCryptPasswordEncoder.encode(elemento.getPassword()));
        //Agrega el usuario
        Usuario usuario = elementoDAO.saveAndFlush(formatearStrings(elemento));
        //Define un UsuarioEmpresa
        UsuarioEmpresa usuarioEmpresa;
        //Obtiene las empresas
        List<Empresa> empresas = empresaDAO.findAll();
        //Asigna todas empresas al nuevo usuario con mostrar en false
        for (Empresa empresa : empresas) {
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
        elemento.setEsDesarrollador(false);
        return elementoDAO.save(formatearStrings(elemento));
    }

    //Actualiza la constraseña de un registro
    @Transactional(rollbackFor = Exception.class)
    public Usuario actualizarContrasenia(Usuario elemento) {
        elemento.setPassword(bCryptPasswordEncoder.encode(elemento.getPassword()));
        return elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        for (UsuarioEmpresa u : usuarioEmpresaDAO.findByUsuario(elementoDAO.findById(elemento))) {
            usuarioEmpresaDAO.deleteById(u.getId());
        }
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Usuario formatearStrings(Usuario elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        elemento.setUsername(elemento.getUsername().trim().toLowerCase());
        return elemento;
    }

}
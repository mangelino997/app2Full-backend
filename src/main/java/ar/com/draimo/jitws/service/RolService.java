//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IOpcionDAO;
import ar.com.draimo.jitws.dao.IPestaniaDAO;
import ar.com.draimo.jitws.dao.IRolDAO;
import ar.com.draimo.jitws.dao.IRolOpcionDAO;
import ar.com.draimo.jitws.dao.IRolSubopcionDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.dao.ISubopcionPestaniaDAO;
import ar.com.draimo.jitws.dao.IUsuarioDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.dto.InitRolDTO;
import ar.com.draimo.jitws.dto.RolDTO;
import ar.com.draimo.jitws.model.Opcion;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.RolOpcion;
import ar.com.draimo.jitws.model.RolSubopcion;
import ar.com.draimo.jitws.model.Subopcion;
import ar.com.draimo.jitws.model.SubopcionPestania;
import ar.com.draimo.jitws.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Rol
 *
 * @author blas
 */
@Service
public class RolService {

    //Define la referencia al dao
    @Autowired
    IRolDAO elementoDAO;

    //Define la referencia al dao subopcion
    @Autowired
    ISubopcionDAO subopcionDAO;

    //Define la referencia al dao pestania
    @Autowired
    IPestaniaDAO pestaniaDAO;

    //Define la referencia al dao opcion
    @Autowired
    IOpcionDAO opcionDAO;

    //Define la referencia al dao rolsubopcion
    @Autowired
    IRolSubopcionDAO rolSubopcionDAO;

    //Define la referencia al dao subopcionpestania
    @Autowired
    ISubopcionPestaniaDAO subopcionPestaniaDAO;

    //Define la referencia al dao rolopcion
    @Autowired
    IRolOpcionDAO rolOpcionDAO;

    //Define la referencia al dao usuario
    @Autowired
    IUsuarioDAO usuarioDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitRolDTO inicializar(int idRol, int idSubopcion) {
        InitRolDTO elemento = new InitRolDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setUltimoId(obtenerSiguienteId());
        elemento.setRoles(elementoDAO.findAll());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Rol elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Rol> listar() {
        return elementoDAO.findAllByEsDesarrolladorFalse();
    }

    //Obtiene una lista por nombre
    public List<Rol> listarPorNombre(String nombre) {
        return (nombre.equals("*")?elementoDAO.findAllByEsDesarrolladorFalse():
            elementoDAO.findByNombreContainingAndEsDesarrolladorFalse(nombre));
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Rol agregar(RolDTO elemento) {
        elemento = formatearStrings(elemento);
        //Crea el nuevo rol
        Rol r = new Rol();
        r.setNombre(elemento.getNombre());
        r.setEsDesarrollador(false);
        Rol rol = elementoDAO.saveAndFlush(r);
        //Define un RolSubopcion
        RolSubopcion rolSubopcion;
        //Define una subopcion pestania
        SubopcionPestania subopcionPestania;
        //Verifica si el usuario copia de un rol existente
        if (elemento.getRolSecundarioDTO() == null) {
            //Obtiene la lista completa de subopciones
            List<Subopcion> subopciones = subopcionDAO.findAll();
            //Obtiene la lista de pestanias
            List<Pestania> pestanias = pestaniaDAO.findAll();
            //Recorre la lista de subopciones
            for (Subopcion subopcion : subopciones) {
                //Crea una instancia de RolSubopcion
                rolSubopcion = new RolSubopcion();
                rolSubopcion.setRol(rol);
                rolSubopcion.setSubopcion(subopcion);
                rolSubopcion.setMostrar(false);
                rolSubopcionDAO.saveAndFlush(rolSubopcion);
                if (subopcion.getEsABM()) {
                    for (Pestania pestania : pestanias) {
                        //Crea una instancias de SubopcionPestania
                        subopcionPestania = new SubopcionPestania();
                        subopcionPestania.setRol(rol);
                        subopcionPestania.setSubopcion(subopcion);
                        subopcionPestania.setPestania(pestania);
                        subopcionPestania.setMostrar(false);
                        subopcionPestaniaDAO.saveAndFlush(subopcionPestania);
                    }
                }
            }
        } else {
            //Obtiene el rol secundario
            Rol rolSecundario = elementoDAO.findById(elemento.getRolSecundarioDTO().getId()).get();
            //Obtiene la lista de subopiones del rol secundario elegido por el usuario
            List<RolSubopcion> rolSubopciones = rolSubopcionDAO.findByRol(rolSecundario);
            //Obtiene la lista de pestanias del rol secundario elegido por el usuario
            List<SubopcionPestania> subopcionPestanias = subopcionPestaniaDAO.findByRol(rolSecundario);
            //Recorre las subopciones del rol secundario
            for (RolSubopcion rs : rolSubopciones) {
                //Crea el nuevo rolsubopcion
                rolSubopcion = new RolSubopcion();
                rolSubopcion.setRol(rol);
                rolSubopcion.setSubopcion(rs.getSubopcion());
                rolSubopcion.setMostrar(rs.getMostrar());
                //Agrega el rol subopion
                rolSubopcionDAO.saveAndFlush(rolSubopcion);
            }
            //Recorre las pestanias del rol secundario
            for (SubopcionPestania sp : subopcionPestanias) {
                //Crea la nueva subopcionpestania
                subopcionPestania = new SubopcionPestania();
                subopcionPestania.setRol(rol);
                subopcionPestania.setSubopcion(sp.getSubopcion());
                subopcionPestania.setPestania(sp.getPestania());
                subopcionPestania.setMostrar(sp.getMostrar());
                //Agrega la subopcion pestania
                subopcionPestaniaDAO.saveAndFlush(subopcionPestania);
            }
        }
        //Obtiene la lista de opciones
        List<Opcion> opciones = opcionDAO.findAll();
        //Define un rolOpcion
        RolOpcion rolOpcion;
        //Recorre la lista y agrega a rolOpcion
        for (Opcion opcion : opciones) {
            //Crea un rolopcion
            rolOpcion = new RolOpcion();
            rolOpcion.setRol(rol);
            rolOpcion.setOpcion(opcion);
            rolOpcion.setMostrar(true);
            rolOpcionDAO.saveAndFlush(rolOpcion);
        }
        return rol;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RolDTO elemento) {
        elemento = formatearStrings(elemento);
        Rol rol = new Rol();
        rol.setId(elemento.getId());
        rol.setVersion(elemento.getVersion());
        rol.setNombre(elemento.getNombre());
        rol.setEsDesarrollador(false);
        elementoDAO.save(rol);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public boolean eliminar(int id) throws Exception {
        //Obtiene el rol por id
        Rol rol = elementoDAO.findById(id).get();
        //Obtiene la lista de usuario por rol primario
        List<Usuario> usuarios = usuarioDAO.findByRol(rol);
        //Obtiene la lista de usuarios por rol secundario
        List<Usuario> usuariosSecundarios = usuarioDAO.findByRolSecundario(rol);
        //Verifica que ningun usuario tenga el rol asignado
        if (usuarios.isEmpty() && usuariosSecundarios.isEmpty()) {
            /*
         * Obtiene una lista de RolSubopcion por idRol
         * y elimina los registros correspondientes
             */
            List<RolSubopcion> rolesSubopcion = rolSubopcionDAO.findByRol(rol);
            rolesSubopcion.forEach((rolSubopcion) -> {
                rolSubopcionDAO.delete(rolSubopcion);
            });
            /*
         * Obtiene una lista de RolOpcion por idRol
         * y elimina los registros correspondientes
             */
            List<RolOpcion> rolesOpcion = rolOpcionDAO.findByRol(rol);
            rolesOpcion.forEach((rolOpcion) -> {
                rolOpcionDAO.delete(rolOpcion);
            });
            /*
         * Obtiene una lista de SubopcionPestania por idRol
         * y elimina los registros correspondientes
             */
            List<SubopcionPestania> subopcionPestanias = subopcionPestaniaDAO.findByRol(rol);
            subopcionPestanias.forEach((subopcionPestania) -> {
                subopcionPestaniaDAO.delete(subopcionPestania);
            });
            elementoDAO.delete(rol);
            return true;
        } else {
            return false;
        }
    }

    //Formatea los strings
    private RolDTO formatearStrings(RolDTO elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }

}
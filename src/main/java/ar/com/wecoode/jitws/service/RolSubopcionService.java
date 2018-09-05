package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IModuloDAO;
import ar.com.wecoode.jitws.dao.IRolDAO;
import ar.com.wecoode.jitws.dao.IRolSubopcionDAO;
import ar.com.wecoode.jitws.dao.ISubmoduloDAO;
import ar.com.wecoode.jitws.dao.ISubopcionDAO;
import ar.com.wecoode.jitws.dto.RolSubopcionDTO;
import ar.com.wecoode.jitws.model.Modulo;
import ar.com.wecoode.jitws.model.Rol;
import ar.com.wecoode.jitws.model.RolSubopcion;
import ar.com.wecoode.jitws.model.Submodulo;
import ar.com.wecoode.jitws.model.Subopcion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RolSubopcion
 * @author blas
 */

@Service
public class RolSubopcionService {

    //Define la referencia al dao
    @Autowired
    IRolSubopcionDAO elementoDAO;
    
    //Define la referencia al dao rol
    @Autowired
    IRolDAO rolDAO;
    
    //Define la referencia al dao modulo
    @Autowired
    IModuloDAO moduloDAO;
    
    //Define la referencia al dao submodulo
    @Autowired
    ISubmoduloDAO submoduloDAO;
    
    //Define la referencia al dao subopcion
    @Autowired
    ISubopcionDAO subopcionDAO;
    
    //Obtiene la lista completa
    public List<RolSubopcion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene un listado por rol y modulo
    public List<Submodulo> listarPorRolModulo(int idRol, int idModulo) {
        
        //Define una lista dto vacia
        List<Submodulo> submodulos = null;
        
        //Obtiene el rol por id
        Optional<Rol> rol = rolDAO.findById(idRol);
        
        //Obtiene el rol por id
        Optional<Modulo> modulo = moduloDAO.findById(idModulo);
        
        //Obtiene la lista
        List<RolSubopcion> rolesSubopcion = elementoDAO.findByRolAndSubopcion_Submodulo_Modulo(rol, modulo);
        
        //Verifica si la lista obtenida esta vacia
        if(rolesSubopcion.isEmpty()) {
            return submodulos;
        }
        
        //Define una lista de nombre de submodulos
        List<String> nombresSubmodulos = new ArrayList<>();
        
        //Crea una lista de submoduloDTO
        submodulos = new ArrayList<>();
        //Define un submodulo vacio
        Submodulo submodulo;
        //Recorre la lista y asigna los valores obtenidos
        for(RolSubopcion rolSubopcion : rolesSubopcion) {
            //Verifica si el submodulo ya se encuentra en la lista
            if(!nombresSubmodulos.contains(rolSubopcion.getSubopcion().getSubmodulo().getNombre())) {
                //Agregar a la lista
                nombresSubmodulos.add(rolSubopcion.getSubopcion().getSubmodulo().getNombre());
                //Crea una instancia de submoduloDTO
                submodulo = new Submodulo();
                submodulo.setId(rolSubopcion.getSubopcion().getSubmodulo().getId());
                submodulo.setNombre(rolSubopcion.getSubopcion().getSubmodulo().getNombre());
                submodulos.add(submodulo);
            }
        }
        
        return submodulos;
        
    }
    
    //Obtiene un listado por rol y submodulo
    public List<Subopcion> listarPorRolSubmodulo(int idRol, int idSubmodulo) {
        
        //Define una lista dto vacia
        List<Subopcion> subopciones = null;
        
        //Obtiene un rol por id
        Optional<Rol> rol = rolDAO.findById(idRol);
        
        //Obtiene un submodulo por id
        Optional<Submodulo> submodulo = submoduloDAO.findById(idSubmodulo);
        
        //Obtiene la lista
        List<RolSubopcion> rolesSubopcion = elementoDAO.findByRolAndSubopcion_Submodulo(rol, submodulo);
        
        //Verifica si la lista obtenida esta vacia
        if(rolesSubopcion.isEmpty()) {
            return subopciones;
        }
        
        //Define un subopcionDTO
        Subopcion subopcion;
        //Crea una lista de subopcionDTO
        subopciones = new ArrayList<>();
        //Recorre la lista y asigna los valores obtenidos
        for(RolSubopcion rolSubopcion : rolesSubopcion) {
            /*
             * Verifica si la subopcion debe mostrarse
             * 1 == Mostrar ; 0 = No mostrar
             */
            if(rolSubopcion.getMostrar() == true) {
                //Crea una instancia de subopcionDTO
                subopcion = new Subopcion();
                subopcion.setId(rolSubopcion.getSubopcion().getId());
                subopcion.setNombre(rolSubopcion.getSubopcion().getNombre());
                subopcion.setEsABM(rolSubopcion.getSubopcion().getEsABM());
                subopciones.add(subopcion);
            }
        }
        
        return subopciones;
        
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(RolSubopcionDTO elemento) {
        
        //Obtiene el rol por id
        Optional<Rol> rol = rolDAO.findById(elemento.getIdRol());
        
        //Obtiene el submodulo por id
        Optional<Submodulo> submodulo = submoduloDAO.findById(elemento.getIdSubmodulo());
        
        //Obtiene la lista de subopciones por rol y submodulo
        List<RolSubopcion> rolesSubopcion = elementoDAO.findByRolAndSubopcion_Submodulo(rol, submodulo);
        
        //Define un idSubopcion
        int idSubopcion;
        //Recorre la lista
        for(RolSubopcion rolSubopcion : rolesSubopcion) {
            //Establece el valor de idSubopcion
            idSubopcion = rolSubopcion.getSubopcion().getId();
            /*
             * Si el idSubopcion no esta en la lista de rolSubopcionDTO.idSubopciones
             * establece "mostrar" en 0
             */
            if(elemento.getIdSubopciones().contains(idSubopcion)) {
                //Establece "mostrar" en 1
                rolSubopcion.setMostrar(true);
            } else {
                //Establece "mostrar" en 0
                rolSubopcion.setMostrar(false);
            }
            //Actualiza los datos
            elementoDAO.save(rolSubopcion);
        }
        
    }
    
    //Agrega una subopcion a todos los roles
    @Transactional(rollbackFor = Exception.class)
    public void agregarSubopcionARoles(Subopcion subopcion) {
        
        //Agrega la subopcion
        subopcionDAO.saveAndFlush(subopcion);
        
        //Obtiene la lista completa de roles
        List<Rol> roles = rolDAO.findAll();
        
        //Define un rolSubopcion
        RolSubopcion rolSubopcion;
        
        //Recorre la lista de roles
        for(Rol rol : roles) {
            rolSubopcion = new RolSubopcion();
            rolSubopcion.setRol(rol);
            rolSubopcion.setSubopcion(subopcion);
            rolSubopcion.setMostrar((rol.getId() < 3));
            elementoDAO.saveAndFlush(rolSubopcion);
        }
        
    }
    
    //Elimina una subopcion por id de todos los roles
    @Transactional(rollbackFor = Exception.class)
    public void eliminarSubopcionDeRoles(int idSubopcion) {
        
        //Obtiene la lista completa de roles
        List<Rol> roles = rolDAO.findAll();
        
        //Obtiene la subopcion por id
        Optional<Subopcion> subopcion = subopcionDAO.findById(idSubopcion);
        
        //Define un rolSubopcion
        RolSubopcion rolSubopcion;
        
        //Recorre la lista de roles
        for(Rol rol : roles) {
            //Obtiene el rol por id
            Optional<Rol> r = rolDAO.findById(rol.getId());
            rolSubopcion = elementoDAO.findByRolAndSubopcion(r, subopcion);
            elementoDAO.delete(rolSubopcion);
        }
        
        //Elimina la subopcion
        subopcionDAO.deleteById(idSubopcion);
        
    }
    
    /*
     * Asigna todas las subopciones a cada uno de los roles, eliminando todo los
     * datos y reestableciendo desde cero
     */
    @Transactional(rollbackFor = Exception.class)
    public void reestablecerTablaDesdeCero() {
        
        //Elimina todos los datos de la tabla
        elementoDAO.eliminarTodo();
        
        //Reestablece el autoincremental
        elementoDAO.reestablecerAutoincremental();
        
        //Obtiene la lista completa de roles
        List<Rol> roles = rolDAO.findAll();
        
        //Obtiene la lista completa de subopciones
        List<Subopcion> subopciones = subopcionDAO.findAll();
        
        //Define un RolSubopcion
        RolSubopcion rolSubopcion;
        for (Rol rol : roles) {
            //Recorre la lista de submodulos
            for (Subopcion subopcion : subopciones) {
                //Crea una instancia de RolSubopcion
                rolSubopcion = new RolSubopcion();
                rolSubopcion.setRol(rol);
                rolSubopcion.setSubopcion(subopcion);
                switch (rol.getId()) {
                    case 1:
                        rolSubopcion.setMostrar(true);
                        break;
                    case 2:
                        rolSubopcion.setMostrar((rolSubopcion.getSubopcion().getSubmodulo().getModulo().getId() != 1));
                        break;
                    default:
                        rolSubopcion.setMostrar(false);
                        break;
                }
                elementoDAO.saveAndFlush(rolSubopcion);
            }
        }
        
    }
    
}

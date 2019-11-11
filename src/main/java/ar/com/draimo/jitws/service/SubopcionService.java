//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPestaniaDAO;
import ar.com.draimo.jitws.dao.IRolDAO;
import ar.com.draimo.jitws.dao.IRolSubopcionDAO;
import ar.com.draimo.jitws.dao.ISubmoduloDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.dao.ISubopcionPestaniaDAO;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.RolSubopcion;
import ar.com.draimo.jitws.model.Subopcion;
import ar.com.draimo.jitws.model.SubopcionPestania;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Subopcion
 *
 * @author blas
 */
@Service
public class SubopcionService {

    //Define la referencia al dao
    @Autowired
    ISubopcionDAO elementoDAO;

    //Define la referencia al dao submodulo
    @Autowired
    ISubmoduloDAO submoduloDAO;

    //Define la referencia al dao subopcion
    @Autowired
    ISubopcionDAO subopcionDAO;

    //Define la referencia al dao pestania
    @Autowired
    IPestaniaDAO pestaniaDAO;

    //Define la referencia al dao rolSubopcion
    @Autowired
    IRolSubopcionDAO rolSubopcionDAO;

    //Define la referencia al dao subopcionPestania
    @Autowired
    ISubopcionPestaniaDAO subopcionPestaniaDAO;

    //Define la referencia al dao rol
    @Autowired
    IRolDAO rolDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Subopcion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Subopcion> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<Subopcion> listarPorNombre(String nombre) {
        return nombre.equals("***") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene una lista por submodulo
    public List<Subopcion> listarPorSubmodulo(int idSubmodulo) {
        return elementoDAO.findBySubmodulo(submoduloDAO.findById(idSubmodulo));
    }

    //Agrega una subopcion a todos los roles y agrega pestanias a subopcion
    @Transactional(rollbackFor = Exception.class)
    public Subopcion agregarSubopcionARolesYPestaniasASubopcion(Subopcion subopcion) {

        //Agrega la subopcion
        Subopcion s = subopcionDAO.saveAndFlush(subopcion);

        //Obtiene la lista completa de roles
        List<Rol> roles = rolDAO.findAll();

        //Obtiene la lista completa de pestanias
        List<Pestania> pestanias = pestaniaDAO.findAll();

        //Define un rolSubopcion
        RolSubopcion rolSubopcion;

        //Define una subopcionPestania
        SubopcionPestania subopcionPestania;

        //Recorre la lista de roles
        for (Rol rol : roles) {
            rolSubopcion = new RolSubopcion();
            rolSubopcion.setRol(rol);
            rolSubopcion.setSubopcion(s);
            rolSubopcion.setMostrar((rol.getId() < 3));
            rolSubopcionDAO.saveAndFlush(rolSubopcion);
            //Verifica si la subopcion es ABM
            if (subopcion.getEsABM()) {
                //Recorre la lista de pestanias
                for (Pestania pestania : pestanias) {
                    subopcionPestania = new SubopcionPestania();
                    subopcionPestania.setRol(rol);
                    subopcionPestania.setSubopcion(s);
                    subopcionPestania.setPestania(pestania);
                    subopcionPestania.setMostrar((rol.getId() < 3));
                    subopcionPestaniaDAO.saveAndFlush(subopcionPestania);
                }
            }
        }
        return s;
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
        for (Rol rol : roles) {
            //Obtiene el rol por id
            Optional<Rol> r = rolDAO.findById(rol.getId());
            rolSubopcion = rolSubopcionDAO.findByRolAndSubopcion(r, subopcion);
            rolSubopcionDAO.delete(rolSubopcion);
        }
        //Elimina la subopcion
        subopcionDAO.deleteById(idSubopcion);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Subopcion agregar(Subopcion elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Subopcion elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Subopcion formatearStrings(Subopcion elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
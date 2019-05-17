package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPestaniaDAO;
import ar.com.draimo.jitws.dao.IRolDAO;
import ar.com.draimo.jitws.dao.IRolSubopcionDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.dao.ISubopcionPestaniaDAO;
import ar.com.draimo.jitws.dto.RolDTO;
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

    //Define la referencia al dao rolsubopcion
    @Autowired
    IRolSubopcionDAO rolSubopcionDAO;

    //Define la referencia al dao subopcionpestania
    @Autowired
    ISubopcionPestaniaDAO subopcionPestaniaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Rol elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Rol> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<Rol> listarPorNombre(String nombre) {
        if (nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Rol agregar(RolDTO elemento) {
        elemento = formatearStrings(elemento);
        //Crea el nuevo rol
        Rol r = new Rol();
        r.setNombre(elemento.getNombre());
        Rol rol = elementoDAO.saveAndFlush(r);
        //Verifica si el usuario copia de un rol existente
        if (elemento.getIdRolSecundario() == 0) {
            //Obtiene la lista completa de subopciones
            List<Subopcion> subopciones = subopcionDAO.findAll();
            //Obtiene la lista de pestanias
            List<Pestania> pestanias = pestaniaDAO.findAll();
            //Define un RolSubopcion
            RolSubopcion rolSubopcion;
            //Define una subopcion pestania
            SubopcionPestania subopcionPestania;
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
            Rol rolSecundario = elementoDAO.findById(elemento.getIdRolSecundario()).get();
            //Obtiene la lista de subopiones del rol secundario elegido por el usuario
            List<RolSubopcion> rolSubopciones = rolSubopcionDAO.findByRol(rolSecundario);
            //Obtiene la lista de pestanias del rol secundario elegido por el usuario
            List<SubopcionPestania> subopcionPestanias = subopcionPestaniaDAO.findByRol(rolSecundario);
            //Define una rolSubopcion
            RolSubopcion rs;
            //Recorre las subopciones del rol secundario
            for(RolSubopcion rolSubopcion : rolSubopciones) {
                //Crea el nuevo rolsubopcion
                rs = new RolSubopcion();
                rs.setRol(rol);
                rs.setSubopcion(rolSubopcion.getSubopcion());
                rs.setMostrar(rolSubopcion.getMostrar());
                //Agrega el rol subopion
                rolSubopcionDAO.saveAndFlush(rs);
            }
            //Define una subopcionPestania
            SubopcionPestania sp;
            //Recorre las pestanias del rol secundario
            for(SubopcionPestania subopcionPestania : subopcionPestanias) {
                //Crea la nueva subopcionpestania
                sp = new SubopcionPestania();
                sp.setRol(rol);
                sp.setSubopcion(subopcionPestania.getSubopcion());
                sp.setPestania(subopcionPestania.getPestania());
                sp.setMostrar(subopcionPestania.getMostrar());
                //Agrega la subopcion pestania
                subopcionPestaniaDAO.saveAndFlush(sp);
            }
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
        elementoDAO.save(rol);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Rol elemento) {
        Rol rol = elementoDAO.findById(elemento.getId()).get();
        //Obtiene una lista de RolSubopcion por idRol
        List<RolSubopcion> rolesSubopcion = rolSubopcionDAO.findByRol(rol);
        rolesSubopcion.forEach((rolSubopcion) -> {
            rolSubopcionDAO.delete(rolSubopcion);
        });
        elementoDAO.delete(elemento);
    }

    //Formatea los strings
    private RolDTO formatearStrings(RolDTO elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

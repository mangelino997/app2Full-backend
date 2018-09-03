package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IPestaniaDAO;
import ar.com.wecoode.jitws.dao.IRolDAO;
import ar.com.wecoode.jitws.dao.ISubopcionDAO;
import ar.com.wecoode.jitws.dao.ISubopcionPestaniaDAO;
import ar.com.wecoode.jitws.dto.SubopcionPestaniaDTO;
import ar.com.wecoode.jitws.model.Pestania;
import ar.com.wecoode.jitws.model.Rol;
import ar.com.wecoode.jitws.model.Subopcion;
import ar.com.wecoode.jitws.model.SubopcionPestania;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio SubopcionPestania
 * @author blas
 */

@Service
public class SubopcionPestaniaService {

    //Define la referencia al dao
    @Autowired
    ISubopcionPestaniaDAO elementoDAO;
    
    //Define la referencia al dao rol
    @Autowired
    IRolDAO rolDAO;
    
    //Define la referencia al dao subopcion
    @Autowired
    ISubopcionDAO subopcionDAO;
    
    //Define la referencia al dao pestania
    @Autowired
    IPestaniaDAO pestaniaDAO;
    
    //Obtiene la lista completa
    public List<SubopcionPestania> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por rol y subopcion
    public List<Pestania> listarPorRolSubopcion(int idRol, int idSubopcion) {
        
        //Define una lista vacia
        List<Pestania> pestanias = null;
        
        //Obtiene el rol por id
        Optional<Rol> rol = rolDAO.findById(idRol);
        
        //Obtiene la subopcion por id
        Optional<Subopcion> subopcion = subopcionDAO.findById(idSubopcion);
        
        //Obtiene la lista
        List<SubopcionPestania> subopcionesPestania = elementoDAO.findByRolAndSubopcion(rol, subopcion);
        
        //Verifica si la lista obtenida esta vacia
        if(subopcionesPestania.isEmpty()) {
            return pestanias;
        }
        
        //Crea una lista de pestanias
        pestanias = new ArrayList<>();
        //Define una pestania vacio
        Pestania pestania;
        //Recorre la lista y asigna los valores obtenidos
        for(SubopcionPestania subopcionPestania : subopcionesPestania) {
            /*
             * Verifica si la pestania debe mostrarse
             * 1 == Mostrar ; 0 = No mostrar
             */
            if(subopcionPestania.getMostrar() == true) {
                //Crea una instanica de pestaniaDTO
                pestania = new Pestania();
                //Establece los valores
                pestania.setId(subopcionPestania.getPestania().getId());
                pestania.setNombre(subopcionPestania.getPestania().getNombre());
                pestanias.add(pestania);
            }
        }
        
        return pestanias;
        
    }
    
    //Actualiza
    public void actualizar(SubopcionPestaniaDTO subopcionPestaniaDTO) {
        
        //Obtiene el rol por id
        Optional<Rol> rol = rolDAO.findById(subopcionPestaniaDTO.getIdRol());
        
        //Obtiene la subopcion por id
        Optional<Subopcion> subopcion = subopcionDAO.findById(subopcionPestaniaDTO.getIdSubopcion());
        
        //Obtiene la lista de pestañas de la subopcion
        List<SubopcionPestania> subopcionPestaniaLista = elementoDAO.findByRolAndSubopcion(rol, subopcion);
        
        //Define un idPestania
        int idPestania;
        //Recorre la lista
        for (SubopcionPestania subopcionPestania : subopcionPestaniaLista) {
            //Establece el valor de idPestania
            idPestania = subopcionPestania.getPestania().getId();
            /*
             * Si el idPestania no esta en la lista de subopcionPestaniaDTO.idPestanias
             * establece "mostrar" en 0
             */
            if(subopcionPestaniaDTO.getIdPestanias().contains(idPestania)) {
                //Establece "mostrar" en 1
                subopcionPestania.setMostrar(true);
            } else {
                //Establece "mostrar" en 0
                subopcionPestania.setMostrar(false);
            }
            elementoDAO.save(subopcionPestania);
        }
        
    }
    
    //Asigna todas las pestañas a cada una de las subopciones
    public void asignarPestaniasASubopciones() {
        
        //Obtiene la lista completa de roles
        List<Rol> roles = rolDAO.findAll();
        
        //Obtiene la lista completa de subopciones
        List<Subopcion> subopciones = subopcionDAO.findAll();
        
        //Obtiene la lista de pestañas
        List<Pestania> pestanias = pestaniaDAO.findAll();
        
        //Define una SubopcionPestania
        SubopcionPestania subopcionPestania;
        //Recorre la lista de roles
        for(Rol rol : roles) {
            //Recorre la lista de subopciones
            for (Subopcion subopcion : subopciones) {
                /*
                 * Verifica si la subopcion es ABM
                 * 1 = Es ABM ; 0 = No es ABM
                 */
                if (subopcion.getEsABM() == true) {
                    //Recorre la lista de pestañas
                    for (Pestania pestania : pestanias) {
                        //Crea una instancia de SubopcionPestania
                        subopcionPestania = new SubopcionPestania();
                        //Establece los valores
                        subopcionPestania.setRol(rol);
                        subopcionPestania.setSubopcion(subopcion);
                        subopcionPestania.setPestania(pestania);
                        if(rol.getId() != 1) {
                            subopcionPestania.setMostrar(false);
                        } else {
                            subopcionPestania.setMostrar(true);
                        }
                        //Agrega los datos
                        elementoDAO.save(subopcionPestania);
                    }
                }
            }
        }
        
    }
    
    /*
     * Asigna todas las pestañas a cada una de las subopciones, eliminando todo los
     * datos y reestableciendo desde cero
     */
    public void reestablecerTablaDesdeCero() {
        
        //Elimina todos los datos de la tabla
        elementoDAO.eliminarTodo();
        
        //Reestablece el autoincremental
        elementoDAO.reestablecerAutoincremental();
        
        //Obtiene la lista completa de roles
        List<Rol> roles = rolDAO.findAll();
        
        //Obtiene la lista completa de subopciones
        List<Subopcion> subopciones = subopcionDAO.findAll();
        
        //Obtiene la lista de pestañas
        List<Pestania> pestanias = pestaniaDAO.findAll();
        
        //Define una SubopcionPestania
        SubopcionPestania subopcionPestania;
        //Recorre la lista de roles
        for(Rol rol : roles) {
            //Recorre la lista de subopciones
            for (Subopcion subopcion : subopciones) {
                /*
                 * Verifica si la subopcion es ABM
                 * 1 = Es ABM ; 0 = No es ABM
                 */
                if (subopcion.getEsABM() == true) {
                    //Recorre la lista de pestañas
                    for (Pestania pestania : pestanias) {
                        //Crea una instancia de SubopcionPestania
                        subopcionPestania = new SubopcionPestania();
                        //Establece los valores
                        subopcionPestania.setRol(rol);
                        subopcionPestania.setSubopcion(subopcion);
                        subopcionPestania.setPestania(pestania);
                        if(rol.getId() != 1) {
                            subopcionPestania.setMostrar(false);
                        } else {
                            subopcionPestania.setMostrar(true);
                        }
                        //Agrega los datos
                        elementoDAO.save(subopcionPestania);
                    }
                }
            }
        }
        
    }
    
}

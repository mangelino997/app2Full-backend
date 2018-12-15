package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPestaniaDAO;
import ar.com.draimo.jitws.dao.IRolDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.dao.ISubopcionPestaniaDAO;
import ar.com.draimo.jitws.dto.PestaniaDTO;
import ar.com.draimo.jitws.dto.SubopcionPestaniaDTO;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.Subopcion;
import ar.com.draimo.jitws.model.SubopcionPestania;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    //Obtiene la lista de pestanias de una subopcion para actualizar estado mostrar
    public SubopcionPestaniaDTO obtenerPestaniasPorRolYSubopcion(int idRol, int idSubopcion) {
        
        //Obtiene el rol por id
        Optional<Rol> rol = rolDAO.findById(idRol);
        
        //Obtiene la subopcion por id
        Optional<Subopcion> subopcion = subopcionDAO.findById(idSubopcion);
        
        //Obtiene la lista de subopcion pestania
        List<SubopcionPestania> subopcionPestanias = elementoDAO.findByRolAndSubopcion(rol, subopcion);
        
        //Define una subopcion pestania dto
        SubopcionPestaniaDTO subopcionPestaniaDTO = new SubopcionPestaniaDTO();
        
        //Define una lista de pestanias dto
        List<PestaniaDTO> pestaniaDTOs = new ArrayList<>();
        
        //Defina una pestania dto
        PestaniaDTO pestaniaDTO;
        
        //Recorre la lista de subopcion pestania
        for(SubopcionPestania subopcionPestania : subopcionPestanias) {
            pestaniaDTO = new PestaniaDTO();
            pestaniaDTO.setId(subopcionPestania.getPestania().getId());
            pestaniaDTO.setVersion(subopcionPestania.getPestania().getVersion());
            pestaniaDTO.setNombre(subopcionPestania.getPestania().getNombre());
            pestaniaDTO.setMostrar(subopcionPestania.getMostrar());
            pestaniaDTOs.add(pestaniaDTO);
        }
        
        //Establece la lista de pestanias a la subopcion pestania dto
        subopcionPestaniaDTO.setPestanias(pestaniaDTOs);
        
        //Retorna los datos
        return subopcionPestaniaDTO;
        
    }
    
    //Actualiza
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SubopcionPestaniaDTO subopcionPestaniaDTO) {
        
        //Obtiene el rol
        Optional<Rol> rol = rolDAO.findById(subopcionPestaniaDTO.getRol().getId());
        
        //Obtiene la subopcion
        Optional<Subopcion> subopcion = subopcionDAO.findById(subopcionPestaniaDTO.getSubopcion().getId());
        
        //Define una subopcion pestania
        SubopcionPestania subopcionPestania;
        
        //Recorre la lista de pestanias
        for(PestaniaDTO pestaniaDTO : subopcionPestaniaDTO.getPestanias()) {
            subopcionPestania = elementoDAO.findByRolAndSubopcionAndPestania(rol, subopcion, 
                    pestaniaDAO.findById(pestaniaDTO.getId()));
            //Establece el estado de la pestania
            subopcionPestania.setMostrar(pestaniaDTO.getMostrar());
            //Actualiza el registro
            elementoDAO.save(subopcionPestania);
        }
        
    }
    
    //Asigna todas las pestañas a cada una de las subopciones
    @Transactional(rollbackFor = Exception.class)
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

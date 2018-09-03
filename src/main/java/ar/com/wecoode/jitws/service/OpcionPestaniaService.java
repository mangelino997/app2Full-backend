package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IOpcionPestaniaDAO;
import ar.com.wecoode.jitws.dao.IOpcionDAO;
import ar.com.wecoode.jitws.dao.IPestaniaDAO;
import ar.com.wecoode.jitws.dao.IRolDAO;
import ar.com.wecoode.jitws.dto.OpcionPestaniaDTO;
import ar.com.wecoode.jitws.model.Opcion;
import ar.com.wecoode.jitws.model.OpcionPestania;
import ar.com.wecoode.jitws.model.Pestania;
import ar.com.wecoode.jitws.model.Rol;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Moneda
 * @author blas
 */

@Service
public class OpcionPestaniaService {
    
    //Define la referencia al dao
    @Autowired
    IOpcionPestaniaDAO elementoDAO;
    
    //Define la referencia al dao rol
    @Autowired
    IRolDAO rolDAO;
    
    //Define la referencia al dao pestania
    @Autowired
    IPestaniaDAO pestaniaDAO;
    
    //Define la referencia al dao opcion
    @Autowired
    IOpcionDAO opcionDAO;
    
    //Obtiene una lista por rol y opcion
    public List<Pestania> listarPorRolOpcion(int idRol, int idOpcion) {
        
        //Define una lista dto vacia
        List<Pestania> pestanias = null;
        
        //Obtiene el rol por id
        Optional<Rol> rol = rolDAO.findById(idRol);
        
        //Obtiene la opcion por id
        Optional<Opcion> opcion = opcionDAO.findById(idOpcion);
        
        //Obtiene la lista
        List<OpcionPestania> opcionesPestania = elementoDAO.findByRolAndOpcion(rol, opcion);
        
        //Verifica si la lista obtenida esta vacia
        if(opcionesPestania.isEmpty()) {
            return pestanias;
        }
        
        //Crea una lista de pestaniaDTO
        pestanias = new ArrayList<>();
        //Define un DTO pestania vacio
        Pestania pestania;
        //Recorre la lista y asigna los valores obtenidos
        for(OpcionPestania opcionPestania : opcionesPestania) {
            /*
             * Verifica si la pestania debe mostrarse
             * 1 == Mostrar ; 0 = No mostrar
             */
            if(opcionPestania.getMostrar() == true) {
                //Crea una instanica de pestaniaDTO
                pestania = new Pestania();
                //Establece los valores
                pestania.setId(opcionPestania.getPestania().getId());
                pestania.setNombre(opcionPestania.getPestania().getNombre());
                pestanias.add(pestania);
            }
        }
        
        return pestanias;
        
    }
    
    //Actualiza un registro
    public void actualizar(OpcionPestaniaDTO opcionPestaniaDTO) {
        
        //Obtiene el rol por id
        Optional<Rol> rol = rolDAO.findById(opcionPestaniaDTO.getIdRol());
        
        //Obtiene la opcion por id
        Optional<Opcion> opcion = opcionDAO.findById(opcionPestaniaDTO.getIdOpcion());
        
        //Obtiene la lista de pestañas de la opcion
        List<OpcionPestania> opcionesPestania = elementoDAO.findByRolAndOpcion(rol, opcion);
        
        //Define un idPestania
        int idPestania;
        //Recorre la lista
        for (OpcionPestania opcionPestania : opcionesPestania) {
            //Establece el valor de idPestania
            idPestania = opcionPestania.getPestania().getId();
            /*
             * Si el idPestania no esta en la lista de opcionPestaniaDTO.idPestanias
             * establece "mostrar" en 0
             */
            if(opcionPestaniaDTO.getIdPestanias().contains(idPestania)) {
                //Establece "mostrar" en 1
                opcionPestania.setMostrar(true);
            } else {
                //Establece "mostrar" en 0
                opcionPestania.setMostrar(false);
            }
            elementoDAO.save(opcionPestania);
        }
        
    }
    
    //Asigna todas las pestañas a cada una de las opciones
    public void asignarPestaniasAOpciones() {
        
        //Obtiene la lista completa de roles
        List<Rol> roles = rolDAO.findAll();
        
        //Obtiene la lista completa de opciones
        List<Opcion> opciones = opcionDAO.findAll();
        
        //Obtiene la lista de pestañas
        List<Pestania> pestanias = pestaniaDAO.findAll();
        
        //Define una OpcionPestania
        OpcionPestania opcionPestania;
        //Recorre la lista de roles
        for(Rol rol : roles) {
            //Recorre la lista de opciones
            for (Opcion opcion : opciones) {
                /*
                 * Verifica si la opcion es ABM
                 * 1 = Es ABM ; 0 = No es ABM
                 */
                if (opcion.getEsABM() == true) {
                    //Recorre la lista de pestañas
                    for (Pestania pestania : pestanias) {
                        //Crea una instancia de OpcionPestania
                        opcionPestania = new OpcionPestania();
                        //Establece los valores
                        opcionPestania.setRol(rol);
                        opcionPestania.setOpcion(opcion);
                        opcionPestania.setPestania(pestania);
                        if(rol.getId() != 1) {
                            opcionPestania.setMostrar(false);
                        } else {
                            opcionPestania.setMostrar(true);
                        }
                        //Agrega los datos
                        elementoDAO.save(opcionPestania);
                    }
                }
            }
        }
        
    }
    
}

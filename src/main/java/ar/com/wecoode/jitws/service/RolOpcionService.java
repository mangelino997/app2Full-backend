package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IOpcionDAO;
import ar.com.wecoode.jitws.dao.IRolOpcionDAO;
import ar.com.wecoode.jitws.dao.IRolDAO;
import ar.com.wecoode.jitws.dao.ISubopcionDAO;
import ar.com.wecoode.jitws.model.Opcion;
import ar.com.wecoode.jitws.model.Rol;
import ar.com.wecoode.jitws.model.RolOpcion;
import ar.com.wecoode.jitws.model.Subopcion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Pais
 * @author blas
 */

@Service
public class RolOpcionService {

    //Define la referencia al dao
    @Autowired
    IRolOpcionDAO elementoDAO;
    
    //Define la referencia al dao rol
    @Autowired
    IRolDAO rolDAO;
    
    //Define la referencia al dao opcion
    @Autowired
    IOpcionDAO opcionDAO;
    
    //Define la referencia al dao subopcion
    @Autowired
    ISubopcionDAO subopcionDAO;
    
    //Obtiene la lista completa
    public List<RolOpcion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por rol y subopcion
    public List<Opcion> listarPorRolSubopcion(int idRol, int idSubopcion) {
        
        //Define una lista dto vacia
        List<Opcion> opciones = null;
        
        //Obtiene el rol por id
        Optional<Rol> rol = rolDAO.findById(idRol);
        
        //Obtiene la subopcion por id
        Optional<Subopcion> subopcion = subopcionDAO.findById(idSubopcion);
        
        //Obtiene la lista
        List<RolOpcion> rolOpciones = elementoDAO.findByRolAndOpcion_Subopcion(rol, subopcion);
        
        //Verifica si la lista obtenida esta vacia
        if(rolOpciones.isEmpty()) {
            return opciones;
        }
        
        //Define un opcionDTO
        Opcion opcion;
        //Crea una lista de opcionDTO
        opciones = new ArrayList<>();
        //Recorre la lista y asigna los valores obtenidos
        for(RolOpcion rolOpcion : rolOpciones) {
            /*
             * Verifica si la subopcion debe mostrarse
             * 1 == Mostrar ; 0 = No mostrar
             */
            if(rolOpcion.getMostrar() == true) {
                //Crea una instancia de subopcionDTO
                opcion = new Opcion();
                opcion.setId(rolOpcion.getOpcion().getId());
                opcion.setNombre(rolOpcion.getOpcion().getNombre());
                opcion.setEsABM(rolOpcion.getOpcion().getEsABM());
                opciones.add(opcion);
            }
        }
        
        return opciones;
        
    }

}

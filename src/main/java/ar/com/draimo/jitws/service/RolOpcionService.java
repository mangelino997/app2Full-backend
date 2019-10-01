//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOpcionDAO;
import ar.com.draimo.jitws.dao.IRolOpcionDAO;
import ar.com.draimo.jitws.dao.IRolDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.model.Opcion;
import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.RolOpcion;
import ar.com.draimo.jitws.model.Subopcion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio RolOpcion
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
    public List<Opcion> listarPorRolYSubopcion(int idRol, int idSubopcion) {
        
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
        List<Opcion> opciones = opcionDAO.findAll();
        
        //Define un RolSubopcion
        RolOpcion rolOpcion;
        for (Rol rol : roles) {
            //Recorre la lista de submodulos
            for (Opcion opcion : opciones) {
                //Crea una instancia de RolSubopcion
                rolOpcion = new RolOpcion();
                rolOpcion.setRol(rol);
                rolOpcion.setOpcion(opcion);
                switch (rol.getId()) {
                    case 1:
                        rolOpcion.setMostrar(true);
                        break;
                    case 2:
                        rolOpcion.setMostrar(true);
                        break;
                    default:
                        rolOpcion.setMostrar(true);
                        break;
                }
                elementoDAO.saveAndFlush(rolOpcion);
            }
        }
    }

}
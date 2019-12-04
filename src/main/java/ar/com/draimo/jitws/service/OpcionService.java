//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOpcionDAO;
import ar.com.draimo.jitws.dao.IOpcionPestaniaDAO;
import ar.com.draimo.jitws.dao.IPestaniaDAO;
import ar.com.draimo.jitws.dao.IRolDAO;
import ar.com.draimo.jitws.dao.IRolOpcionDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.model.Opcion;
import ar.com.draimo.jitws.model.OpcionPestania;
import ar.com.draimo.jitws.model.Pestania;
import ar.com.draimo.jitws.model.Rol;
import ar.com.draimo.jitws.model.RolOpcion;
import ar.com.draimo.jitws.model.Subopcion;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Moneda
 * @author blas
 */

@Service
public class OpcionService {
    
    //Define la referencia al dao
    @Autowired
    IOpcionDAO elementoDAO;
    
    //Define la referencia al dao subopcion
    @Autowired
    ISubopcionDAO subopcionDAO;
    
    //Define la referencia al dao rol
    @Autowired
    IRolDAO rolDAO;
    
    //Define la referencia al dao pestania
    @Autowired
    IPestaniaDAO pestaniaDAO;
    
    //Define la referencia al dao rolopcion
    @Autowired
    IRolOpcionDAO rolOpcionDAO;
    
    //Define la referencia al dao opcionpestania
    @Autowired
    IOpcionPestaniaDAO opcionPestaniaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Opcion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Opcion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Opcion> listarPorNombre(String nombre) {
        return nombre.equals("*")? elementoDAO.findAll():
            elementoDAO.findByNombreContaining(nombre);
    }
    
    //Obtiene una lista por subopcion
    public List<Opcion> listarPorSubopcion(int idSubopcion) {
        //Obtiene la subopcion por id
        Optional<Subopcion> subopcion = subopcionDAO.findById(idSubopcion);
        return elementoDAO.findBySubopcion(subopcion);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Opcion agregar(Opcion elemento) {
        //Formatea los strings
        elemento = formatearStrings(elemento);
        //Agrega el registro
        Opcion opcion = elementoDAO.saveAndFlush(elemento);
        //Obtiene la lista de roles
        List<Rol> roles = rolDAO.findAll();
        //Obtiene la lista de pestanias
        List<Pestania> pestanias = pestaniaDAO.findAll();
        //Define un RolOpcion
        RolOpcion rolOpcion;
        //Define un OpcionPestania
        OpcionPestania opcionPestania;
        //Recorre la lista de roles y guarda cada opcion con rol en RolOpcion
        for(Rol rol : roles) {
            rolOpcion = new RolOpcion();
            rolOpcion.setRol(rol);
            rolOpcion.setOpcion(opcion);
            rolOpcion.setMostrar((rol.getId() < 3));
            rolOpcionDAO.saveAndFlush(rolOpcion);
            //Verifica si la opcion agrega es ABM
            if(opcion.getEsABM()) {
                //Recorre la lista de pestanias
                for (Pestania pestania : pestanias) {
                    //Crea una instancia de opcionpestania
                    opcionPestania = new OpcionPestania();
                    opcionPestania.setRol(rol);
                    opcionPestania.setOpcion(opcion);
                    opcionPestania.setPestania(pestania);
                    opcionPestania.setMostrar((rol.getId() < 3));
                    opcionPestaniaDAO.saveAndFlush(opcionPestania);
                }
            }
        }
        return opcion;
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Opcion elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private Opcion formatearStrings(Opcion elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
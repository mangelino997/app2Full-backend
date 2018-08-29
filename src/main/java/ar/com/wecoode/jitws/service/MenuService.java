package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IRolDAO;
import ar.com.wecoode.jitws.dao.IRolSubopcionDAO;
import ar.com.wecoode.jitws.model.Menu;
import ar.com.wecoode.jitws.model.ModuloMenu;
import ar.com.wecoode.jitws.model.Rol;
import ar.com.wecoode.jitws.model.RolSubopcion;
import ar.com.wecoode.jitws.model.SubmoduloMenu;
import ar.com.wecoode.jitws.model.SubopcionMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Menu
 * @author blas
 */

@Service
public class MenuService {
    
    //Define la referencia al dao rolsubopcion
    @Autowired
    IRolSubopcionDAO rolSubopcionDAO;
    
    //Define la referencia al dao rol
    @Autowired
    IRolDAO rolDAO;
    
    //Obtiene la lista completa
    public Menu listar(int idRol) {
        
        //Define un dto vacio
        Menu menu = null;
        
        //Obtiene el rol por id
        Optional<Rol> rol = rolDAO.findById(idRol);
        
        //Obtiene una lista de RolSubopcion por idRol
        List<RolSubopcion> rolSubopcionLista = rolSubopcionDAO.findByRol(rol);
        
        if(rolSubopcionLista.isEmpty()) {
            return menu;
        }
        
        //Crea una instancia de menuDTO
        menu = new Menu();
        
        //Define una lista de nombres de modulos
        List<String> modulosLista = new ArrayList<>();
        
        //Define un modulo dto vacio
        ModuloMenu moduloMenu;
        
        //Define un submodulo dto vacio
        SubmoduloMenu submoduloMenu;
        
        //Define una lista de modulos vacia
        List<ModuloMenu> modulos = new ArrayList<>();
        
        //Define una lista de submodulos vacia
        List<SubmoduloMenu> submodulos;
        
        //Define una lista de subopciones vacia
        List<SubopcionMenu> subopciones;
        
        //Define una SubopcionMenuDTO
        SubopcionMenu subopcionMenuDTO;
        
        //Recorre la lista de rolSubopcion
        for(RolSubopcion rolSubopcion : rolSubopcionLista) {
            //Verifica si la subopcion debe mostrarse
            if(rolSubopcion.getMostrar() == true) {
                //Define el nombre del modulo
                String modulo = rolSubopcion.getSubopcion().getSubmodulo().getModulo().getNombre();
                //Define el nombre del submodulo
                String submodulo = rolSubopcion.getSubopcion().getSubmodulo().getNombre();
                //Define el id de la subopcion
                int subopcionId = rolSubopcion.getSubopcion().getId();
                //Define el nombre de la subopcion
                String subopcionNombre = rolSubopcion.getSubopcion().getNombre();
                //Verifica si el supermodulo existe dentro de la lista supermodulos
                if (!modulosLista.contains(modulo)) {
                    //Agregar el nombre del modulo a la lista modulos
                    modulosLista.add(modulo);
                    //Crea una instancia de modulo dto
                    moduloMenu = new ModuloMenu();
                    //Establece el nombre del modulo
                    moduloMenu.setModulo(modulo);
                    //Crea una instancia de submodulo dto
                    submoduloMenu = new SubmoduloMenu();
                    //Establece el nombre del submodulo
                    submoduloMenu.setSubmodulo(submodulo);
                    //Crea una lista de subopciones
                    subopciones = new ArrayList<>();
                    //Crea una instancia de SubopcionMenuDTO
                    subopcionMenuDTO = new SubopcionMenu();
                    //Establece los valores de la subopcion
                    subopcionMenuDTO.setId(subopcionId);
                    subopcionMenuDTO.setSubopcion(subopcionNombre);
                    //Agrega el dto subopcionMenuDTO a la lista
                    subopciones.add(subopcionMenuDTO);
                    //Establece la lista de subopciones al submodulo
                    submoduloMenu.setSubopciones(subopciones);
                    //Crea una instancia de lista de submodulos
                    submodulos = new ArrayList<>();
                    //Agrega el submoduloDTO a la lista de submodulos
                    submodulos.add(submoduloMenu);
                    //Establece la lista de submodulos
                    moduloMenu.setSubmodulos(submodulos);
                    //Agrega el moduloDTO a la lista de modulos
                    modulos.add(moduloMenu);
                    //Establece la lista de modulos
                    menu.setModulos(modulos);
                } else {
                    /* 
                     * Si el modulo existe dentro de la lista modulos, recorre la
                     * lista de modulos
                     */
                    for (ModuloMenu modulosDTO : menu.getModulos()) {
                        //Verifica que modulo se esta tratando
                        if (modulosDTO.getModulo().equals(modulo)) {
                            /* 
                         * Define una variable auxiliar que determina si el submodulo
                         * existe dentro de la lista
                             */
                            int aux = 0;
                            //Recorre la lista de submodulos
                            for (SubmoduloMenu submoduloDTO2 : modulosDTO.getSubmodulos()) {
                                //Verifica si el submodulo se encuentra en la lista de submodulos
                                if (submoduloDTO2.getSubmodulo().equals(submodulo)) {
                                    /*
                                     * Establece la variable auxiliar en 1, el 
                                     * submodulo existe dentro de la lista
                                     */
                                    aux = 1;
                                    //Crea una instancia de subopcionMenuDTO
                                    subopcionMenuDTO = new SubopcionMenu();
                                    //Establece los valores de subopcionMenuDTO
                                    subopcionMenuDTO.setId(subopcionId);
                                    subopcionMenuDTO.setSubopcion(subopcionNombre);
                                    //Agregue la subopcion al submodulo 
                                    submoduloDTO2.getSubopciones().add(subopcionMenuDTO);
                                }
                            }
                            //Si el submodulo no existe, lo agrega al modulo
                            if (aux == 0) {
                                //Crea una instancia de submodulo dto
                                submoduloMenu = new SubmoduloMenu();
                                //Establece el nombre del submodulo
                                submoduloMenu.setSubmodulo(submodulo);
                                //Crea una lista de subopciones
                                subopciones = new ArrayList<>();
                                //Crea una instancia de subopcionMenuDTO
                                subopcionMenuDTO = new SubopcionMenu();
                                //Establece los valores de subopcionMenuDTO
                                subopcionMenuDTO.setId(subopcionId);
                                subopcionMenuDTO.setSubopcion(subopcionNombre);
                                //Establece la subopcion
                                subopciones.add(subopcionMenuDTO);
                                //Establece la lista de subopciones al submodulo
                                submoduloMenu.setSubopciones(subopciones);
                                //Agrega el submoduloDTO a la lista de submodulos
                                modulosDTO.getSubmodulos().add(submoduloMenu);
                            }
                        }
                    }
                }
            }
        }
        
        //Retorna los datos
        return menu;
    }
    
}

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.model.Personal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Personal
 * @author blas
 */

@Service
public class PersonalService {

    //Define la referencia al dao
    @Autowired
    IPersonalDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Personal elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Personal> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por alias
    public List<Personal> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }
    
    //Obtiene un chofer por alias
    public List<Personal> listarChoferPorAlias(String alias) {
        return elementoDAO.findByAliasContainingAndEsChofer(alias, 1);
    }
    
    //Obtiene un listado de choferes ordenados por nombre de corta distancia
    public List<Personal> listarChoferesCortaDistanciaOrdenadoPorNombre(String alias) {
        return elementoDAO.listarChoferesCortaDistanciaPorAliasOrdenadoPorNombre(alias);
    }
    
    //Obtiene un listado de acompañantes ordenados por nombre
    public List<Personal> listarAcompaniantesOrdenadoPorNombre(String alias) {
        return elementoDAO.listarAcompaniantesPorAliasOrdenadoPorNombre(alias);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Personal agregar(Personal elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.saveAndFlush(elemento);
        elemento.setAlias(elemento.getId() + " - " + elemento.getNombreCompleto() 
                + " - " + elemento.getNumeroDocumento());
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Personal elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Personal elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Personal formatearStrings(Personal elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setApellido(elemento.getApellido().trim());
        elemento.setNombreCompleto(elemento.getApellido() + " " + elemento.getNombre());
        elemento.setNumeroDocumento(elemento.getNumeroDocumento().trim());
        elemento.setCuil(elemento.getCuil().trim());
        if(elemento.getTelefonoFijo() != null) {
            elemento.setTelefonoFijo(elemento.getTelefonoFijo().trim());
        }
        if(elemento.getTelefonoMovil() != null) {
            elemento.setTelefonoMovil(elemento.getTelefonoMovil().trim());
        }
        if(elemento.getCorreoelectronico() != null) {
            elemento.setCorreoelectronico(elemento.getCorreoelectronico().trim().toLowerCase());
        }
        elemento.setDomicilio(elemento.getDomicilio().trim());
        if(elemento.getTalleCamisa() != null) {
            elemento.setTalleCamisa(elemento.getTalleCamisa().trim());
        }
        if(elemento.getTallePantalon() != null) {
            elemento.setTallePantalon(elemento.getTallePantalon().trim());
        }
        if(elemento.getTalleCalzado() != null) {
            elemento.setTalleCalzado(elemento.getTalleCalzado().trim());
        }
        if(elemento.getTelefonoMovilEmpresa() != null) {
            elemento.setTelefonoMovilEmpresa(elemento.getTelefonoMovilEmpresa().trim());
        }
        if(elemento.getTelefonoMovilObservacion() != null) {
            elemento.setTelefonoMovilObservacion(elemento.getTelefonoMovilObservacion().trim());
        }
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        return elemento;
    }

}

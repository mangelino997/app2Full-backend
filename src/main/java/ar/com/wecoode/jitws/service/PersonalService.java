package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IPersonalDAO;
import ar.com.wecoode.jitws.model.Personal;
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

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Personal agregar(Personal elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.saveAndFlush(elemento);
        elemento.setAlias(elemento.getId() + " - " + elemento.getNombreCompleto() + " - " + elemento.getNumeroDocumento());
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
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        elemento.setApellido(Funcion.convertirATitulo(elemento.getApellido().trim()));
        elemento.setNombreCompleto(elemento.getApellido() + " " + elemento.getNombre());
        elemento.setNumeroDocumento(elemento.getNumeroDocumento().trim());
        elemento.setCuil(elemento.getCuil().trim());
        elemento.setTelefonoFijo(elemento.getTelefonoFijo().trim());
        elemento.setTelefonoMovil(elemento.getTelefonoMovil().trim());
        elemento.setCorreoelectronico(elemento.getCorreoelectronico().trim().toLowerCase());
        elemento.setDomicilio(Funcion.primerLetraAMayuscula(elemento.getDomicilio().trim()));
        elemento.setTalleCamisa(elemento.getTalleCamisa().trim());
        elemento.setTallePantalon(elemento.getTallePantalon().trim());
        elemento.setTalleCalzado(elemento.getTalleCalzado().trim());
        elemento.setTelefonoMovilEmpresa(elemento.getTelefonoMovilEmpresa().trim());
        elemento.setTelefonoMovilObservacion(elemento.getTelefonoMovilObservacion().trim());
        elemento.setObservaciones(Funcion.primerLetraAMayuscula(elemento.getObservaciones().trim()));
        return elemento;
    }

}

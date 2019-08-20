package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipDeduccionGeneralDAO;
import ar.com.draimo.jitws.dao.IAfipDeduccionGeneralTopeDAO;
import ar.com.draimo.jitws.model.AfipDeduccionGeneralTope;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipDeduccionGeneralTope
 * @author blas
 */

@Service
public class AfipDeduccionGeneralTopeService {
    
    //Define la referencia al dao
    @Autowired
    IAfipDeduccionGeneralTopeDAO elementoDAO;
    
    //Define la referencia al dao de afipDeduccionGeneral
    @Autowired
    IAfipDeduccionGeneralDAO afipDeduccionGeneralDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipDeduccionGeneralTope elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipDeduccionGeneralTope> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Descripcion
    public List<AfipDeduccionGeneralTope> listarPorDescripcion(String descripcion) {
        if(descripcion.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByDescripcionContaining(descripcion);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipDeduccionGeneralTope agregar(AfipDeduccionGeneralTope elemento) throws Exception {
        elemento = formatearStrings(elemento);
        String anio = String.valueOf(elemento.getAnio());
        //Obtiene longitud de anio, si supera 4 retorna error
        if (anio.length()>4 || anio.length()<4) {
            throw new DataIntegrityViolationException("Cantidad caracteres incorrecta en AÑO");
        } 
        if(elementoDAO.findByAnioAndAfipDeduccionGeneral(elemento.getAnio(), elemento.getAfipDeduccionGeneral()).isEmpty()){
            return elementoDAO.saveAndFlush(elemento);
        } else {
            throw new DataIntegrityViolationException("Deducción General existente para el año fiscal.");
        }
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipDeduccionGeneralTope elemento) throws Exception {
        elemento = formatearStrings(elemento);
        String anio = String.valueOf(elemento.getAnio());
        //Obtiene longitud de anio, si supera 4 retorna error
        if (anio.length()>4 || anio.length()<4) {
            throw new DataIntegrityViolationException("Cantidad caracteres incorrecta en AÑO");
        } 
        if(elementoDAO.findByAnioAndAfipDeduccionGeneral(elemento.getAnio(), elemento.getAfipDeduccionGeneral()).isEmpty()){
            elementoDAO.save(elemento);
        } else {
            throw new DataIntegrityViolationException("Deducción General existente para el año fiscal.");
        }
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private AfipDeduccionGeneralTope formatearStrings(AfipDeduccionGeneralTope elemento) {
        elemento.setDescripcion(elemento.getDescripcion().trim());
        return elemento;
    }
    
}

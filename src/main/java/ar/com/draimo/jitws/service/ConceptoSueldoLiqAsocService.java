/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IConceptoSueldoDAO;
import ar.com.draimo.jitws.dao.IConceptoSueldoLiqAsocDAO;
import ar.com.draimo.jitws.dao.ITipoLiquidacionSueldoDAO;
import ar.com.draimo.jitws.model.ConceptoSueldoLiqAsoc;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *Servicio ConceptoSueldoLiqAsoc
 * @author blas
 */
@Service
public class ConceptoSueldoLiqAsocService {
    
    //Define la referencia al DAO
    @Autowired
    IConceptoSueldoLiqAsocDAO elementoDAO;
    
    //Referencia al DAO ConceptoSueldo
    @Autowired
    IConceptoSueldoDAO conceptoSueldoDAO;
    
    //Referencia al DAO TipoLiquidacionSueldo
    @Autowired
    ITipoLiquidacionSueldoDAO tipoLiquidacionSueldoDAO;
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ConceptoSueldoLiqAsoc agregar (ConceptoSueldoLiqAsoc elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ConceptoSueldoLiqAsoc elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Obtiene Lista COmpleta
    public List<ConceptoSueldoLiqAsoc> listar() {
        return elementoDAO.findAll();
    }
}

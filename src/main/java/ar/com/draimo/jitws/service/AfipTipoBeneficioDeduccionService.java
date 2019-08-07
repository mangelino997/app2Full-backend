package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipDeduccionPersonalDAO;
import ar.com.draimo.jitws.dao.IAfipTipoBeneficioDAO;
import ar.com.draimo.jitws.dao.IAfipTipoBeneficioDeduccionDAO;
import ar.com.draimo.jitws.model.AfipTipoBeneficioDeduccion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipTipoBeneficioDeduccion
 * @author blas
 */

@Service
public class AfipTipoBeneficioDeduccionService {
    
    //Define la referencia al dao
    @Autowired
    IAfipTipoBeneficioDeduccionDAO elementoDAO;
    
    //Define la referencia al dao de afipTipoBeneficio
    @Autowired
    IAfipTipoBeneficioDAO beneficioDAO;
    
    //Define la referencia al dao de afipDeduccionPersonal
    @Autowired
    IAfipDeduccionPersonalDAO deduccionDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipTipoBeneficioDeduccion elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipTipoBeneficioDeduccion> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por idTipoBeneficio
    public List<AfipTipoBeneficioDeduccion> listarPorBeneficio(int idBeneficio) {
        return elementoDAO.findByAfipTipoBeneficio(beneficioDAO.findById(idBeneficio).get());
    }
    
    //Obtiene una lista por idDedudccionPersonal
    public List<AfipTipoBeneficioDeduccion> listarPorDeduccionPersonal(int idDedudccion) {
        return elementoDAO.findByAfipDeduccionPersonal(deduccionDAO.findById(idDedudccion).get());
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipTipoBeneficioDeduccion agregar(AfipTipoBeneficioDeduccion elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipTipoBeneficioDeduccion elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}

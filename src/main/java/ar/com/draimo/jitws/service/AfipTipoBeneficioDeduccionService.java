package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipDeduccionPersonalDAO;
import ar.com.draimo.jitws.dao.IAfipTipoBeneficioDAO;
import ar.com.draimo.jitws.dao.IAfipTipoBeneficioDeduccionDAO;
import ar.com.draimo.jitws.model.AfipTipoBeneficioDeduccion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    
    //Obtiene una lista por anio y idTipoBeneficio
    public List<AfipTipoBeneficioDeduccion> listarPorAnioYBeneficio(short anio, int idBeneficio) {
        return elementoDAO.findByAnioAndAfipTipoBeneficio(anio, beneficioDAO.findById(idBeneficio).get());
    }
    
    //Obtiene una lista por filtros
    public List<AfipTipoBeneficioDeduccion> listarPorFiltros(short anio, int idBeneficio, int idMes) throws Exception {
        if(idMes> 12){
            throw new Exception("Mes inexistente");
        }else {
        return elementoDAO.listarPorFiltros(anio, idBeneficio, idMes);
        }
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
    public AfipTipoBeneficioDeduccion agregar(AfipTipoBeneficioDeduccion elemento) throws Exception {
        String anio = String.valueOf(elemento.getAnio());
        //Obtiene longitud de anio, si supera 4 retorna error
        if (anio.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO");
        }
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipTipoBeneficioDeduccion elemento) throws Exception {
        String anio = String.valueOf(elemento.getAnio());
        //Obtiene longitud de anio, si supera 4 retorna error
        if (anio.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO");
        }
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}
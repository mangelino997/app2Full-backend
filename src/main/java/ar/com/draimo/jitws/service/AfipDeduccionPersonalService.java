//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Fecha;
import ar.com.draimo.jitws.dao.IAfipDeduccionPersonalDAO;
import ar.com.draimo.jitws.dao.IAfipTipoBeneficioDAO;
import ar.com.draimo.jitws.dao.IMesDAO;
import ar.com.draimo.jitws.dto.InitDeduccionPersonalTablaDTO;
import ar.com.draimo.jitws.model.AfipDeduccionPersonal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipDeduccionPersonal
 * @author blas
 */

@Service
public class AfipDeduccionPersonalService {
    
    //Define la referencia al dao
    @Autowired
    IAfipDeduccionPersonalDAO elementoDAO;
    
    //Define la referencia al dao
    @Autowired
    IAfipTipoBeneficioDAO tipoBeneficioDAO;
    
    //Define la referencia al dao
    @Autowired
    IMesDAO mesDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitDeduccionPersonalTablaDTO inicializar(int idRol, int idSubopcion) {
        InitDeduccionPersonalTablaDTO elemento = new InitDeduccionPersonalTablaDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setTipoBeneficios(tipoBeneficioDAO.findAll());
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitDeduccionPersonalTablaDTO inicializarTabla(int idRol, int idSubopcion) {
        InitDeduccionPersonalTablaDTO elemento = new InitDeduccionPersonalTablaDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setAfipDeduccionPersonales(elementoDAO.findAll());
        elemento.setTipoBeneficios(tipoBeneficioDAO.findAll());
        elemento.setAnios(Fecha.listarAnioFiscal());
        elemento.setMeses(mesDAO.findAll());
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipDeduccionPersonal elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<AfipDeduccionPersonal> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Descripcion
    public List<AfipDeduccionPersonal> listarPorDescripcion(String descripcion) {
        return descripcion.equals("*") ?elementoDAO.findAll()
                : elementoDAO.findByDescripcionContaining(descripcion);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipDeduccionPersonal agregar(AfipDeduccionPersonal elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipDeduccionPersonal elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private AfipDeduccionPersonal formatearStrings(AfipDeduccionPersonal elemento) {
        elemento.setDescripcion(elemento.getDescripcion().trim());
        return elemento;
    }
    
}
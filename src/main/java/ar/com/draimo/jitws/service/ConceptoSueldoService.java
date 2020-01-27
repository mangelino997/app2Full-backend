//Paquete al que pertenece el servicio

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IConceptoSueldoDAO;
import ar.com.draimo.jitws.dao.ITipoConceptoSueldoDAO;
import ar.com.draimo.jitws.dto.InitConceptoSueldoDTO;
import ar.com.draimo.jitws.model.ConceptoSueldo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *Servicio de ConceptoSueldo
 * @author marina
 */
@Service
public class ConceptoSueldoService {
    
    //Define la referencia al dao
    @Autowired
    IConceptoSueldoDAO elementoDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //referencia al DAO TipoConceptoSueldo
    @Autowired
    ITipoConceptoSueldoDAO tipoConceptoSueldoDAO;
    
    //Referencia al Service TipoConceptoSueldoService
    @Autowired
    TipoConceptoSueldoService tipoConceptoSueldoService;
    
    //referencia al servicio UnidadMedidaSueldoService
    @Autowired
    UnidadMedidaSueldoService unidadMedidaSueldoService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitConceptoSueldoDTO inicializar(int idRol, int idsubopcion) {
        InitConceptoSueldoDTO elemento = new InitConceptoSueldoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idsubopcion));
        elemento.setTiposConceptosSueldos(tipoConceptoSueldoService.listar());
        elemento.setUnidadesMedidasSueldos(unidadMedidaSueldoService.listar());
        return elemento;
    }
    
    public int obtenerSiguienteId() {
        ConceptoSueldo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null? elemento.getId()+1 : 1;
    }
    
     //Obtiene la lista completa
    public List<ConceptoSueldo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Tipo Concepto
    //public List<ConceptoSueldo> listarPorTipoConcepto(int idTipoConceptoSueldo) {
    //    return idTipoConceptoSueldo != 0 ?
    //            elementoDAO.findByTipoConceptoSueldoOrderByNombreAsc(tipoConceptoSueldoDAO.findById(idTipoConceptoSueldo).get()) :
    //            elementoDAO.findAllByOrderByNombreAsc();
    //}
    
    //Obtiene la lista por el Nombre (Descripción)
    public List<ConceptoSueldo> listarPorNombre(String nombre){
       return nombre.equals("*") ? elementoDAO.findAll():
         elementoDAO.findByNombreContaining(nombre);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ConceptoSueldo agregar(ConceptoSueldo elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ConceptoSueldo elemento) {
        elementoDAO.save(elemento);
    }
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    //Obtiene el último Código Empleador
    public String obtenerUltimoCodigoEmpleador() {
        return "OK";
    }
}

//Paquete al que pertenece el servicio

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IConceptoSueldoDAO;
import ar.com.draimo.jitws.model.ConceptoSueldo;
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
    
    @Autowired
    IConceptoSueldoDAO elementoDAO;
    
    public int obtenerSiguienteId() {
        ConceptoSueldo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null? elemento.getId()+1 : 1;
    }
    
     //Obtiene la lista completa
    public List<ConceptoSueldo> listar() {
        return elementoDAO.findAll();
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
}

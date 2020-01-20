//Paquete al que pertenece el servicio

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IUnidadMedidaSueldoDAO;
import ar.com.draimo.jitws.model.UnidadMedidaSueldo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *Servicio de UnidadMedidaSueldo
 * @author marina
 */
@Service
public class UnidadMedidaSueldoService {
    
    @Autowired
    IUnidadMedidaSueldoDAO elementoDAO;
    
    public int obtenerSiguienteId() {
        UnidadMedidaSueldo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null? elemento.getId()+1 : 1;
    }
    
     //Obtiene la lista completa
    public List<UnidadMedidaSueldo> listar() {
        return elementoDAO.findAll();
    }
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public UnidadMedidaSueldo agregar(UnidadMedidaSueldo elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(UnidadMedidaSueldo elemento) {
        elementoDAO.save(elemento);
    }
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
}
